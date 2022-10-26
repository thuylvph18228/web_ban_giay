package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import poly.edu.DAO.GiayDAO;
import poly.edu.Entity.Giay;

import java.util.List;

public class HomeController {
    @Autowired
    GiayDAO giaydao;

    @GetMapping("/layout")
    public String index(Model model){
        List<Giay> listgl = giaydao.findAll();
        model.addAttribute("listgl", listgl);
        return "layout/index";
    }
}
