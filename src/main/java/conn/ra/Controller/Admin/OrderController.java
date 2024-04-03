package conn.ra.Controller.Admin;

import conn.ra.Model.Entity.Orders;
import conn.ra.Service.Order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/admin-orders.html")
    public String ordersPage(Model model) {
        List<Orders> orders = orderService.findAll ();
        model.addAttribute ( "orders", orders );
        return "/admin/admin-orders";
    }

    @GetMapping("/orders_details")
    public String ordersDetailsPage() {
        return "/admin/admin-orderDetail";
    }
}