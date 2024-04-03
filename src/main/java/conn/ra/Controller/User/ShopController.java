package conn.ra.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ShopController {
    @GetMapping("/books-detail.html")
    public String bookDetail() {
        return "home/books-detail";
    }
    @GetMapping("/books-grid-view.html")
    public String bookGridView() {return "home/books-grid-view";}
    @GetMapping("/books-list.html")
    public String bookList() {
        return "home/books-list";
    }
}
