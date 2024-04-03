package conn.ra.Service.WishList;

import conn.ra.Model.Dto.Request.WishlistRequest;
import conn.ra.Model.Entity.WishList;

import java.util.List;

public interface WishListService {
    WishList add(Long userId, WishlistRequest wishListRequest);
    List<WishList> getAll(Long userId);
    void delete(Long wishListId, Long userId);
    WishList findByUserAndBook(Long userId, Long bookId);
}
