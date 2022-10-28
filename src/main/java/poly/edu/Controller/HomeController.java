package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import poly.edu.DAO.GiayDAO;
import poly.edu.DAO.NsxDAO;
import poly.edu.Entity.Giay;
import poly.edu.Entity.Nsx;

import java.util.List;

public class HomeController {
    @Autowired
    GiayDAO giaydao;
    @Autowired
    NsxDAO nsxDAO;

    @GetMapping("/layout")
    public String index(Model model){
        List<Giay> listgl = giaydao.findAll();
        model.addAttribute("listgl", listgl);
        List<Nsx> listnsx = nsxDAO.findAll();
        model.addAttribute("listnsx", listnsx);

        return "layout/index";
    }
    @GetMapping("/layout")
    public String category(Model model){
        List<Nsx> listnsx = nsxDAO.findAll();
        model.addAttribute("listnsx", listnsx);

        return "layout/_category";
    }

}
