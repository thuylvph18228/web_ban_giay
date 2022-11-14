package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import poly.edu.DAO.ChiTietGiayDAO;
import poly.edu.DAO.ChiTietHoaDonDAO;
import poly.edu.DAO.GiayDAO;
import poly.edu.Entity.ChiTietGiay;
import poly.edu.Entity.ChiTietHoaDon;
import poly.edu.Entity.Giay;

import java.util.List;

@Controller
public class ThongKeController {

    @Autowired
    GiayDAO giayDAO;

    @Autowired
    ChiTietGiayDAO chiTietGiayDAO;

    @Autowired
    ChiTietHoaDonDAO chiTietHoaDonDAO;

    @GetMapping("/admin/thongke")
    public String thongke(Model model){
        List<Giay> giayList = giayDAO.findBySelling();
        model.addAttribute("giayList", giayList);
        List<ChiTietGiay> chiTietGiayList = chiTietGiayDAO.findAll();
        model.addAttribute("chiTietGiayList", chiTietGiayList);
        List<ChiTietHoaDon> chiTietHoaDonList = chiTietHoaDonDAO.findAll();
        model.addAttribute("chiTietHoaDonList", chiTietHoaDonList);
        return "admin/thongke/index";
    }
}
