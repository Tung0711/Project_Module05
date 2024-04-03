package conn.ra.Service.Order;

import conn.ra.Model.Entity.EOrder;
import conn.ra.Model.Entity.Orders;
import conn.ra.Model.Entity.User;

import java.util.List;

public interface OrderService {
    List<Orders> findAll();
    List<Orders> getAll(Long userId);
    Orders add(User users, Double totalPrice);
    Orders getBySerial(Long userId, String serial);
    List<Orders> getByStatus(Long userId, EOrder status);
    Orders save(Orders orders);
    Orders getByIdAndStatus(Long userId, Long orderId, EOrder status);
}
