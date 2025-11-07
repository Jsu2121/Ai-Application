package cs3220.aiapplication.Controller;

import cs3220.aiapplication.model.DataStore;
import cs3220.aiapplication.model.Ingredient;
import cs3220.aiapplication.model.User;
import cs3220.aiapplication.model.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IngredientController {
    public final DataStore dataStore;
    public final UserBean userBean;

    public IngredientController(DataStore dataStore, UserBean userBean){
        this.dataStore = dataStore;
        this.userBean = userBean;
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


    @GetMapping("/edit/{id}")
    public String editIngredient(Model model, @PathVariable("id") Integer id){
        if(!userBean.isLoggedIn()) {
            return "redirect:/login";
        }
        User user =  userBean.getUser();
        if(user == null){
            return "redirect:/inventory";
        }
        Ingredient ingredient = dataStore.getIngredient(user.getId())
                .stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
        if(ingredient == null){
            return "redirect:/inventory";
        }
        model.addAttribute("ingredient", ingredient);
        return "editInventory";
    }

    @PostMapping("/edit/{id}")
    public String editIngredientPost(@PathVariable("id") Integer id, @RequestParam("name") String name, @RequestParam("quantity") String quantity) {
        if (!userBean.isLoggedIn()) {
            return "redirect:/login";
        }
        User user = userBean.getUser();

        Ingredient ingredient = dataStore.getIngredient(user.getId()).stream().filter(i -> i.getId() == id).findFirst().orElse(null);
        if (ingredient == null) {
            return "redirect:/inventory";
        } else {
            ingredient.setName(name);
            ingredient.setQuantity(quantity);
            return "redirect:/inventory";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable("id") Integer id){
        if(!userBean.isLoggedIn()){
            return "redirect:/login";
        }
        User user = userBean.getUser();
        dataStore.deleteIngredient(user.getId(), id);
        return "redirect:/inventory";

    }

}
