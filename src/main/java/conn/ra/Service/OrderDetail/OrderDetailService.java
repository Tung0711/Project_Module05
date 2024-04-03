package conn.ra.Service.OrderDetail;

import conn.ra.Model.Entity.Book;
import conn.ra.Model.Entity.OrderDetail;
import conn.ra.Model.Entity.Orders;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getByOrderId(Long orderId);
    OrderDetail add(Book book, Orders orders, int orderQuantity);
}
