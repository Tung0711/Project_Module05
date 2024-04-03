package conn.ra.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping("/index.html")
    public String index() {
        return "home/index";
    }
    @GetMapping("/shop-checkout.html")
    public String shopCheckOut() {
        return "home/shop-checkout";
    }
    @GetMapping("/wishlist.html")
    public String wishlist() {
        return "home/wishlist";
    }
}
