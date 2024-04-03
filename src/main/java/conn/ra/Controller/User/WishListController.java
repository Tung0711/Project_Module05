package conn.ra.Controller.User;

import conn.ra.Model.Dto.Request.WishlistRequest;
import conn.ra.Model.Entity.WishList;
import conn.ra.Service.WishList.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static conn.ra.Controller.User.CartController.getUserId;

@Controller
@RequestMapping("/user")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @GetMapping("/add-wishlist.html/{id}")
    public String addWishList(@PathVariable("id") Long id) {
        Long userId = getUserId();
        WishList wishList = wishListService.findByUserAndBook (userId, id);
        if (wishList == null) {
            WishlistRequest wishListRequest = new WishlistRequest ();
            wishListRequest.setBookId (id);
            wishListService.add(userId, wishListRequest);
        }
        return "redirect:/user/wishlist.html";
    }

    @GetMapping("/wishlist.html")
    public String getAll(Model model) {
        Long userId = getUserId();
        List<WishList> wishLists = wishListService.getAll(userId);
        model.addAttribute("wishLists", wishLists);
        return "/home/wishlist";
    }

    @GetMapping("/delete-wishlist/{id}")
    public String delete(@PathVariable("id") Long wishListId) {
        Long userId = getUserId();
        wishListService.delete(wishListId, userId);
        return "redirect:/user/wishlist.html";
    }
}
