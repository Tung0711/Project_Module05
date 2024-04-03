package conn.ra.Service.ShoppingCart;

import conn.ra.Model.Dto.Request.ShoppingCartRequest;
import conn.ra.Model.Entity.Book;
import conn.ra.Model.Entity.ShoppingCart;
import conn.ra.Model.Entity.User;
import conn.ra.Repository.ShoppingCartRepository;
import conn.ra.Service.AuthService.UserService;
import conn.ra.Service.BookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @Override
    public List<ShoppingCart> getAll(Long userId) {
        return shoppingCartRepository.findByUsers(userId);
    }

    @Override
    public ShoppingCart add(ShoppingCartRequest shoppingCartRequest, Long userId) {

        Book book = bookService.findById(shoppingCartRequest.getBookId ());
        User user = userService.findById(userId);

        if(book == null) {
            throw new RuntimeException("Không tồn tại sách!");
        }

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .orderQuantity (shoppingCartRequest.getQuantity())
                .book (book)
                .users (user)
                .build();
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart findById(int id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void delete(int id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public ShoppingCart findByBookId(Long userId, Long bookId) {
        return shoppingCartRepository.findByUserAndBook (userId, bookId);
    }
}
