package cs3220.aiapplication.Controller;

import cs3220.aiapplication.model.Ingredient;
import cs3220.aiapplication.model.User;
import cs3220.aiapplication.model.UserBean;
import cs3220.aiapplication.model.DataStore;
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
    public String inventoryPage(Model model) {
        if(!userBean.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("ingredients", dataStore.getIngredient(userBean.getUser().getId()));
        return "inventoryPage";
    }

    @GetMapping("/results")
    public String resultsPage(){
        if(!userBean.isLoggedIn()){
            return "redirect:/login";
        }

        return "resultsPage";
    }

    @GetMapping("/favorites")
    public String favoritesPage(){
        if(!userBean.isLoggedIn()){
            return "redirect:/login";
        }
        return "favoritePage";
    }

    @GetMapping("/profile")
    public String profilePage(Model model){
        if(!userBean.isLoggedIn()) {
            return "redirect:/login";
        } else {
            User user = userBean.getUser();
            model.addAttribute("user", user);
            model.addAttribute("exchangeHistory", userBean.getExchangeHistory());
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
    public String changeUsername(@RequestParam("username") String username){
        if(!userBean.isLoggedIn()) {
            return "redirect:/login";
        }else{
            User user = userBean.getUser();
            user.setUsername(username);

            return "redirect:/profile";
        }
    }




}




