package cs3220.aiapplication.Controller;

import cs3220.aiapplication.model.DataStore;
import cs3220.aiapplication.model.Exchange;
import cs3220.aiapplication.model.Recipe;
import cs3220.aiapplication.model.UserBean;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AiController {
    private final ChatClient chatClient;
    public final DataStore dataStore;
    public final UserBean userBean;
    private final List<Recipe> recipeHistory = new ArrayList<>();

    public AiController(DataStore dataStore, UserBean userBean, ChatClient.Builder chatClientBuilder){
        this.dataStore = dataStore;
        this.userBean = userBean;
        this.chatClient = chatClientBuilder.build();
    }

    private String realChat(String message){
       List<Message> messages = new ArrayList<>();

       messages.add(new SystemMessage("You are a helpful assistant. Generate a recipe using the user's ingredients. " + "Output Format: Title, list of main ingredients, and the instructions"));

       for(Recipe recipe: recipeHistory){
           messages.add(new UserMessage(recipe.getPrompt()));
           messages.add(new AssistantMessage(recipe.getContent()));
       }

       messages.add(new UserMessage(message));

       try {
           return chatClient.prompt(new Prompt(messages)).call().content();
       } catch(Exception e){
           e.printStackTrace();
           return "error contacting AI: " + e.getMessage();
       }
    }



    @GetMapping("/home")
    public String showHomePage(Model model){
        if(!userBean.isLoggedIn()) {
            return "redirect:/login";

        } else {
            int userId = userBean.getUser().getId();
            model.addAttribute("recipes", dataStore.getRecipes(userId));
            model.addAttribute("favorites", dataStore.getFavorites(userId));
            return "homePage";
        }
    }

    @PostMapping("/home")
    public String homePrompt(@RequestParam("prompt") String prompt, Model model){
        if(!userBean.isLoggedIn()){
            return "redirect:/login";
        }

        int userId = userBean.getUser().getId();
        var ingredients = dataStore.getIngredient(userId);

        String userPrompt = "Using these ingredients: " + ingredients.toString() +
                ", " +  prompt +
                ". Generate a recipe with a title, a list of main ingredients, and instructions.";

        String aiResponse = realChat(userPrompt);

        String title = aiResponse.lines().findFirst().orElse("Untitled Recipe").replace("Title: ", ""
                ).trim();



        Recipe recipe = new Recipe(
                dataStore.getNextRecipeId(),
                userId,
                title,
                java.time.LocalTime.now(),
                prompt,
                aiResponse,
                false
        );

        recipeHistory.add(recipe);
        dataStore.addRecipe(userId, recipe);

        // Use DataStore recipes for the sidebar
        model.addAttribute("recipes", dataStore.getRecipes(userId));
        model.addAttribute("favorites", dataStore.getFavorites(userId));
        model.addAttribute("aiResponse", aiResponse);

        return "homePage";
    }



}
