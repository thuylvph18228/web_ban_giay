package poly.edu.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping({"/", "/layout", "/home"})
    public String index() {
        return "giay/product";
    }
}
