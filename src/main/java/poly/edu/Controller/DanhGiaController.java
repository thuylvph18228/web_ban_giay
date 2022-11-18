package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.DanhGiaDAO;
import poly.edu.DAO.GiayDAO;
import poly.edu.DAO.HoaDonDAO;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.Entity.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DanhGiaController {
    @Autowired
    DanhGiaDAO danhGiaDAO;

    @Autowired
    KhachHangDAO khachHangDAO;

    @Autowired
    HttpSession session;
    @Autowired
    GiayDAO giayDAO;
    @Autowired
    HoaDonDAO hoaDonDAO;
    @GetMapping("/user/danhgia/create/{mahd}")
    public String create(@PathVariable("mahd") int mahd, @ModelAttribute("danhgia") DanhGia danhGia, Model model) {
        List<Giay> listg = giayDAO.findByMahd(mahd);
       // System.out.println(listg);
        HoaDon hoadon = hoaDonDAO.getById(mahd);
        model.addAttribute("hoadon", hoadon);
        model.addAttribute("listg", listg);
        model.addAttribute("savedg", "/user/danhgia/save");
        return "user/danhgia/save";
    }
    @PostMapping("/user/danhgia/save")
    public String save(@RequestParam("mahd") int mahd ,@RequestParam("mota") String danhgia , @ModelAttribute("danhgia") DanhGia danhGia, Model model) {
        List<Giay> listg = giayDAO.findByMahd(mahd);

        HoaDon hoadon = hoaDonDAO.getById(mahd);
        String email = (String) session.getAttribute("email");
        for (Giay giay :  listg){
            System.out.println(giay);
            KhachHang kh = khachHangDAO.findByEmail(email);
            danhGia.setMakh(kh.getMakh());
            danhGia.setMag(giay.getMag());
            System.out.println(danhGia.getMota());
            danhGia.setMota(danhGia.getMota());
            danhGiaDAO.save(danhGia);
        }
        return "redirect:/user/cart/delivered";
    }
}
