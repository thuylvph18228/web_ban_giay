package poly.edu.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

<<<<<<< HEAD
    @GetMapping({"/", "/layout", "/home"})
    public String index(){
        return "giay/product";
=======
    @GetMapping("/layout")
    public String index(Model model){
        List<Giay> listgl = giaydao.findAll();
        model.addAttribute("listgl", listgl);

        return "layout/index";
>>>>>>> dc6fa86a7bb0fb921ef5fd89aba2bf39c02f3767
    }
}
