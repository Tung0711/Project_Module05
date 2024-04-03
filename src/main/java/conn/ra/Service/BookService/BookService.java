package conn.ra.Service.BookService;

import conn.ra.Model.Dto.Request.BookRequest;
import conn.ra.Model.Entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book findById(Long id);

    Boolean add(Book book);
    Boolean edit(Book book);

    void delete(Long id);
}
