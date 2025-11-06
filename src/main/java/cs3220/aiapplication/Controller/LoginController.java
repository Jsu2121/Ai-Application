package cs3220.aiapplication.Controller;

import cs3220.aiapplication.model.DataStore;
import cs3220.aiapplication.model.User;
import cs3220.aiapplication.model.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final DataStore dataStore;
    private final UserBean userBean;

    public LoginController(DataStore dataStore, UserBean userBean) {
        this.dataStore = dataStore;
        this.userBean = userBean;
    }

    @GetMapping("/login")
    public String login(){
        return "loginPage";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        User user = dataStore.getUserByCredentials(username, password);
        if (user == null) {
            model.addAttribute("error", "Wrong username or password.");
            return "loginPage";
        }

        userBean.login(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        userBean.logout();
        return "redirect:/";
    }



}
