package conn.ra.Service.Order;

import conn.ra.Model.Entity.EOrder;
import conn.ra.Model.Entity.Orders;
import conn.ra.Model.Entity.User;
import conn.ra.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll ();
    }

    @Override
    public List<Orders> getAll(Long userId) {
        return orderRepository.findAllByUserId (userId);
    }

    @Override
    public Orders add(User users, Double totalPrice) {
        Orders order = Orders.builder()
                .orderNumber( UUID.randomUUID().toString())
                .users(users)
                .totalPrice (totalPrice)
                .status( EOrder.Waiting)
                .receiveName(users.getFullName())
                .receiveAddress(users.getAddress())
                .receivePhone(users.getPhone())
                .build();
        return orderRepository.save(order);
    }
    @Override
    public Orders getBySerial(Long userId, String serial) {
        return orderRepository.findByUserIdAndSerial(userId, serial);
    }

    @Override
    public List<Orders> getByStatus(Long userId, EOrder status) {
        return orderRepository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public Orders getByIdAndStatus(Long userId, Long orderId, EOrder status) {
        return orderRepository.findByIdAndStatus(userId, orderId, status);
    }
}
