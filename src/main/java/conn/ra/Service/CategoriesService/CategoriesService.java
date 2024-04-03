package conn.ra.Service.CategoriesService;

import conn.ra.Model.Dto.Request.CategoriesRequest;
import conn.ra.Model.Entity.Book;
import conn.ra.Model.Entity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoriesService {
    List<Categories> getAll();

    Categories findById(Long id);

    Boolean add(Categories categories);

    Boolean edit(Categories categories);

    void delete(Long id);
}
