package Supplement_store.Suplement_store.controllers;

import Supplement_store.Suplement_store.entities.User;
import Supplement_store.Suplement_store.services.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = loginService.validateUser(username, password);
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole().toString());
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
