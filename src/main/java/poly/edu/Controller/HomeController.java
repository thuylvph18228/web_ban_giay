package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping({"/", "/layout", "/home"})
    public String index() {
        return "giay/product";
    }

}
