package cs3220.aiapplication.Controller;

import cs3220.aiapplication.model.Ingredient;
import cs3220.aiapplication.model.User;
import cs3220.aiapplication.model.UserBean;
import cs3220.aiapplication.model.DataStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/home")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/inventory")
    public String inventoryPage(Model model) {
        if(!userBean.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("ingredients", dataStore.getIngredient(userBean.getUser().getId()));
        return "inventoryPage";
    }

    @GetMapping("/addIngredientPage")
    public String addIngredientPage() {
        if(!userBean.isLoggedIn()) {
            return "redirect:/login";
        }
        return "addIngredient";
    }

    @PostMapping("/addIngredientPage")
    public String addIngredient(@RequestParam("name") String name, @RequestParam("quantity") String quantity) {
        User currUser = userBean.getUser();
        if (currUser == null) {
            return "redirect:/login";
        } else {
            dataStore.addIngredient(currUser.getId(), new Ingredient(name, quantity));
        }

        return "redirect:/inventory";
    }



}
