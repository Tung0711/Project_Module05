package conn.ra.Controller.Admin;

import conn.ra.Model.Entity.User;
import conn.ra.Service.AuthService.RoleService;
import conn.ra.Service.AuthService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin-users.html")
    public String users(Model model, Pageable pageable) {
        Page<User> users = userService.getAll (pageable);
        model.addAttribute ( "user",users);
        return "admin/admin-users";
    }

}
