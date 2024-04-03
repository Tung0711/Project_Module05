package conn.ra.Service.OrderDetail;

import conn.ra.Model.Entity.Book;
import conn.ra.Model.Entity.OrderDetail;
import conn.ra.Model.Entity.Orders;
import conn.ra.Repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> getByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
    @Override
    public OrderDetail add(Book book, Orders orders, int orderQuantity) {
        OrderDetail orderDetail = OrderDetail.builder()
                .orders(orders)
                .book ( book )
                .name(book.getBookName ())
                .price(book.getPrice())
                .orderQuantity (orderQuantity)
                .build();
        return orderDetailRepository.save(orderDetail);
    }
}
