package conn.ra.Controller.Auth;

import conn.ra.Model.Entity.User;
import conn.ra.Service.AuthService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/sign-in.html")
    public String SignIn() {
        return "home/shop-login";
    }

    @GetMapping("/sign-up.html")
    public String signUp(Model model) {
        User user = new User ();
        model.addAttribute ( "user", user );
        return "home/shop-registration";
    }

    @PostMapping("/sign-up.html")
    public String signUp(@ModelAttribute("user") User user) {
        userService.register ( user );
        return "redirect:/sign-in.html";
    }
}
