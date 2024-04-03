package conn.ra.Controller.User;

import conn.ra.Model.Dto.Request.ShoppingCartRequest;
import conn.ra.Model.Entity.Book;
import conn.ra.Model.Entity.Orders;
import conn.ra.Model.Entity.ShoppingCart;
import conn.ra.Model.Entity.User;
import conn.ra.Security.User_principal.UserPrincipal;
import conn.ra.Service.AuthService.UserService;
import conn.ra.Service.BookService.BookService;
import conn.ra.Service.Order.OrderService;
import conn.ra.Service.OrderDetail.OrderDetailService;
import conn.ra.Service.ShoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class CartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    public static Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser ().getId();
    }

    @GetMapping("/shop-cart.html")
    public String cartShop(Model model) {
        Long userId = getUserId();
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAll(userId);
        model.addAttribute("shoppingCarts", shoppingCarts);
        return "/home/shop-cart";
    }

    @GetMapping("/add-cart.html/{id}")
    public String addCart(@PathVariable("id") Long id) {
        Long userId = getUserId();
        ShoppingCart shoppingCart = shoppingCartService.findByBookId (userId, id);
        if (shoppingCart == null) {
            ShoppingCartRequest shoppingCartRequest = new ShoppingCartRequest();
            shoppingCartRequest.setBookId (id);
            shoppingCartRequest.setQuantity(1);
            shoppingCartService.add(shoppingCartRequest, userId);
        } else {
            shoppingCart.setOrderQuantity (shoppingCart.getOrderQuantity () + 1);
            shoppingCartService.save(shoppingCart);
        }
        return "redirect:/user/shop-cart.html";
    }

    @PostMapping("/add-cart.html/{id}")
    public String createCart(@PathVariable("id") Long id, @ModelAttribute("shoppingCartRequest") ShoppingCartRequest shoppingCartRequest) {
        Long userId = getUserId();
        ShoppingCart shoppingCart = shoppingCartService.findByBookId (userId, id);
        if (shoppingCart == null) {
            shoppingCartRequest.setBookId (id);
            shoppingCartService.add(shoppingCartRequest, userId);
        } else {
            shoppingCart.setOrderQuantity (shoppingCart.getOrderQuantity () + shoppingCartRequest.getQuantity());
            shoppingCartService.save(shoppingCart);
        }
        return "redirect:/user/shop-cart.html";
    }

    @GetMapping("/delete-cart/{id}")
    public String deleteCart(@PathVariable("id") Long id) {
        Long userId = getUserId();
        ShoppingCart shoppingCart = shoppingCartService.findByBookId (userId, id);
        if (shoppingCart != null) {
            shoppingCartService.delete(shoppingCart.getId());
        }
        return "/home/shop-cart";
    }
    @PostMapping("/edit-cart/{id}")
    public String editCart(@PathVariable("id") int id, @RequestParam("quantity") int quantity) {
        Long userId = getUserId();
        ShoppingCart shoppingCart = shoppingCartService.findById(id);
        if(shoppingCart != null) {
            if(shoppingCart.getUsers().getId().equals(userId)) {
                shoppingCart.setOrderQuantity (quantity);
                shoppingCartService.save(shoppingCart);
            }
        }
        return "redirect:/user/shop-cart.html";
    }

    @GetMapping("/shop-checkout.html")
    public String checkOut() {
        Long userId = getUserId();
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAll(userId);

        User user = userService.findById(userId);

        double totalPrice = shoppingCarts.stream()
                .mapToDouble(shoppingCart -> shoppingCart.getBook ().getPrice() * shoppingCart.getOrderQuantity ())
                .sum();

        Orders order = orderService.add(user, totalPrice);

        for (ShoppingCart shoppingCart: shoppingCarts) {
            int orderQuantity = shoppingCart.getOrderQuantity ();
            Book book = shoppingCart.getBook ();
            orderDetailService.add(book, order, orderQuantity);
        }

        shoppingCarts.forEach(shoppingCart -> shoppingCartService.delete(shoppingCart.getId()));

        return "redirect:/user/shop-cart.html";
    }
}
