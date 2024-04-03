package conn.ra.Controller.Admin;

import conn.ra.Model.Entity.Book;
import conn.ra.Model.Entity.Categories;
import conn.ra.Service.BookService.BookService;
import conn.ra.Service.CategoriesService.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BookController {
    @Value("${path-upload}")
    private String pathUpload;
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/admin-books.html")
    public String book(Model model) {
        List<Book> books = bookService.getAll ();
        model.addAttribute ( "book", books );
        return "admin/book/admin-books";
    }

    @GetMapping("/admin-add-book.html")
    public String add(Model model) {
        List<Categories> categories = categoriesService.getAll ();
        model.addAttribute ( "categories", categories );
        Book book = new Book ();
        model.addAttribute ( "book", book );
        return "admin/book/admin-add-book";
    }

    @PostMapping("/admin-add-book.html")
    public String create(@ModelAttribute("book") Book book, @RequestParam("imgBook") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes (),new File (pathUpload+fileName));
            // lưu tên file vào database
            book.setImages (fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bookService.add ( book );
        return "redirect:/admin/admin-books.html";
    }

    @GetMapping("/admin-edit-book.html/{id}")
    public String edit(@PathVariable Long id, Model model) {
        List<Categories> categories = categoriesService.getAll ();
        model.addAttribute ( "categories", categories );
        Book book = bookService.findById ( id );
        model.addAttribute ( "book", book );
        return "admin/book/admin-edit-book";
    }

    @PostMapping("/admin-edit-book.html/{id}")
    public String update(@ModelAttribute("book") Book book, @RequestParam("imgBook") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes (),new File (pathUpload+fileName));
            // lưu tên file vào database
            book.setImages (fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bookService.edit ( book );
        return "redirect:/admin/admin-books.html";
    }

    @GetMapping("/delete-book/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete ( id );
        return "redirect:/admin/admin-books.html";
    }
}
