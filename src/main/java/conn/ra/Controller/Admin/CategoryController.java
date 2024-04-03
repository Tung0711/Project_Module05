package conn.ra.Controller.Admin;

import conn.ra.Model.Entity.Categories;
import conn.ra.Service.CategoriesService.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/admin-category.html")
    public String categories(Model model) {
        List<Categories> categories = categoriesService.getAll ();
        model.addAttribute ( "Categories", categories );
        return "admin/category/admin-category";
    }

    @GetMapping("/admin-add-category.html")
    public String save(Model model) {
        Categories categories = new Categories ();
        model.addAttribute ( "Categories", categories );
        return "admin/category/admin-add-category";
    }

    @PostMapping("/admin-add-category.html")
    public String create(@ModelAttribute("Categories") Categories categories) {
        categoriesService.add ( categories );
        return "redirect:/admin/admin-category.html";
    }

    @GetMapping("/category/status/{id}")
    public String updateStatus(@PathVariable("id") Long id) {
        Categories categories = categoriesService.findById ( id );
        categories.setStatus ( !categories.getStatus () );
        categoriesService.edit ( categories );
        return "redirect:/admin/admin-category.html";
    }

    @GetMapping("/admin-edit-category.html/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Categories categories = categoriesService.findById ( id );
        model.addAttribute ( "Categories", categories );
        return "admin/category/admin-edit-category";
    }

    @PostMapping("/admin-edit-category.html/{id}")
    public String update(@ModelAttribute("Categories") Categories categories) {
        categoriesService.edit ( categories );
        return "redirect:/admin/admin-category.html";
    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable Long id) {
        categoriesService.delete ( id );
        return "redirect:/admin/admin-category.html";
    }
}
