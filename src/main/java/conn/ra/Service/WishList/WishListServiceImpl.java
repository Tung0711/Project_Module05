package conn.ra.Service.WishList;

import conn.ra.Model.Dto.Request.WishlistRequest;
import conn.ra.Model.Entity.Book;
import conn.ra.Model.Entity.User;
import conn.ra.Model.Entity.WishList;
import conn.ra.Repository.WishListRepository;
import conn.ra.Service.AuthService.UserService;
import conn.ra.Service.BookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImpl implements WishListService{
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public WishList add(Long userId, WishlistRequest wishListRequest) {
        User user = userService.findById(userId);

        Book book = bookService.findById(wishListRequest.getBookId ());

        if (book == null) {
            throw new RuntimeException("không tồn tại sách");
        }

        WishList wishList = WishList.builder()
                .users (user)
                .book (book)
                .build();
        return wishListRepository.save(wishList);
    }

    @Override
    public List<WishList> getAll(Long userId) {
        return wishListRepository.getAllByUserId(userId);
    }

    @Override
    public void delete(Long wishListId, Long userId) {
        wishListRepository.deleteByIdAndUserId(wishListId, userId);
    }
    @Override
    public WishList findByUserAndBook(Long userId, Long bookId) {
        return wishListRepository.findByUserAndBook (userId, bookId);
    }
}
