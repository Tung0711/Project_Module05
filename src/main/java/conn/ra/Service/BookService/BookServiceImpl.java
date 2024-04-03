package conn.ra.Service.BookService;

import conn.ra.Model.Dto.Request.BookRequest;
import conn.ra.Model.Entity.Book;
import conn.ra.Model.Entity.Categories;
import conn.ra.Repository.BookRepository;
import conn.ra.Service.CategoriesService.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll ();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById ( id ).orElse ( null );
    }

    @Override
    public Boolean add(Book book) {
        try {
            bookRepository.save ( book );
            return true;
        }catch (Exception e){
            e.printStackTrace ();
        }
        return false;
    }

    @Override
    public Boolean edit(Book book) {
        try {
            bookRepository.save ( book );
            return true;
        }catch (Exception e){
            e.printStackTrace ();
        }
        return false;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById ( id );
    }
}
