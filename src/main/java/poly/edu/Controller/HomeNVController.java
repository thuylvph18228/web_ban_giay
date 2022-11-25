package poly.edu.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeNVController {

    @GetMapping({"/", "/layoutnv", "/homenv"})
    public String index() {
        return "khachhang/index";
    }
}
