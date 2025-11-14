package cs3220.aiapplication.Controller;

import cs3220.aiapplication.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    private final DataStore dataStore;
    private final UserBean userBean;

    public IndexController(DataStore dataStore, UserBean userBean) {
        this.dataStore = dataStore;
        this.userBean = userBean;
    }
    @GetMapping("/")
    public String landingPage() {
        return "landingPage";
    }


    @GetMapping("/inventory")
    public String inventoryPage(
            @RequestParam(value = "tab", defaultValue = "history") String tab,
            Model model
    ) {
        if (!userBean.isLoggedIn()) {
            return "redirect:/login";
        }

        int userId = userBean.getUser().getId();

        model.addAttribute("ingredients", dataStore.getIngredient(userId));
        model.addAttribute("recipes", dataStore.getRecipes(userId));
        model.addAttribute("favorites", dataStore.getFavorites(userId));
        model.addAttribute("tab", tab);

        return "inventoryPage";
    }


    @GetMapping("/results")
    public String resultsPage(Model model){
        if(!userBean.isLoggedIn()) {
            return "redirect:/login";

        } else {
            int userId = userBean.getUser().getId();
            model.addAttribute("recipes", dataStore.getRecipes(userId));
            model.addAttribute("favorites", dataStore.getFavorites(userId));
            return "resultsPage";
        }
    }

    @GetMapping("/favorites")
    public String favoritesPage(Model model){
        if(!userBean.isLoggedIn()) {
            return "redirect:/login";

        } else {
            int userId = userBean.getUser().getId();
            model.addAttribute("recipes", dataStore.getRecipes(userId));
            model.addAttribute("favorites", dataStore.getFavorites(userId));
            return "favoritePage";
        }
    }

    @GetMapping("/profile")
    public String profilePage(Model model){
        if(!userBean.isLoggedIn()) {
            return "redirect:/login";
        } else {
            User user = userBean.getUser();
            model.addAttribute("user", user);
            model.addAttribute("recipeHistory", dataStore.getRecipes(user.getId()));
            model.addAttribute("favoriteHistory", dataStore.getFavorites(user.getId()));
            model.addAttribute("ingredientCount", dataStore.getIngredient(user.getId()).size());
            return "userProfile";
        }
    }

    @GetMapping("/changeUsername")
    public String changeUsernamePage(){
        if (!userBean.isLoggedIn()) {
            return "redirect:/login";
        } else {
            return "changeUsernamePage";
        }
    }

    @PostMapping("/changeUsername")
    public String changeUsername(@RequestParam("username") String username) {
        if (!userBean.isLoggedIn()) {
            return "redirect:/login";
        } else {
            User user = userBean.getUser();
            user.setUsername(username);

            return "redirect:/profile";
        }
    }
        @GetMapping("/viewRecipe")
        public String viewRecipe(@RequestParam(value="tab", defaultValue="history") String tab, @RequestParam("id") int id, Model model){
            if(!userBean.isLoggedIn()){
                return "redirect:/login";
            }

            int userId = userBean.getUser().getId();
            Recipe recipe = dataStore.getRecipes(userId).stream().filter(r -> r.getId() ==id).findFirst().orElse(null);

            if(recipe==null){
                return "redirect:/home";
            }

            // Use DataStore recipes for the sidebar
            model.addAttribute("tab", tab);
            model.addAttribute("recipes", dataStore.getRecipes(userId));
            model.addAttribute("favorites", dataStore.getFavorites(userId));
            model.addAttribute("recipeById", recipe);
            return "viewRecipe";

    }

    @GetMapping("/favoriteRecipe")
    public String favoriteRecipe(@RequestParam int id, @RequestParam(required=false, defaultValue="history") String tab){
        int userId = userBean.getUser().getId();
        dataStore.toggleFavorite(userId, id);


        return "redirect:/viewRecipe?id=" + id + "&tab="+tab;
    }

    @GetMapping("/deleteRecipe")
    public String deleteRecipe(@RequestParam int id){
        int userId = userBean.getUser().getId();
        Recipe recipe = dataStore.getRecipeById(userId, id);

        dataStore.deleteRecipe(userId, recipe.getId());
        return "redirect:/home";
    }




}




