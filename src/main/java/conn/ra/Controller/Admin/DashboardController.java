package conn.ra.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {
    @RequestMapping("/admin-dashboard.html")
    public String test(){
        return "admin/admin-dashboard";
    }
}
