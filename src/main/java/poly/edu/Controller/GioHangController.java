package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.*;
import poly.edu.Entity.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class GioHangController {

    HttpSession httpSession;
    @Autowired
    public GiayDAO giayDAO;

    @Autowired
    public SizeDAO sizeDAO;

    @Autowired
    public KhachHangDAO khachHangDAO;

    @Autowired
    public GioHangDAO gioHangDAO;

    @Autowired
    ThanhToanDAO thanhToanDAO;

    @Autowired
    HoaDonDAO hoaDonDAO;

    @Autowired
    ChiTietGiayDAO chiTietGiayDAO;

    @Autowired
    ChiTietHoaDonDAO chiTietHoaDonDAO;

    @GetMapping("/user/giohang/index")
    public String listkh(Model model) {
        List<GioHang> listgh =gioHangDAO.findAll();
        model.addAttribute("listgh", listgh);
        List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);
        return ("user/giohang/index");
    }

    @GetMapping("/user/giohang/create")
    public String create(@ModelAttribute("giohang") GioHang gioHang, Model model) {
        List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);
        model.addAttribute("savegh", "/savegh");
        return "user/giohang/save";
    }

    @GetMapping("/user/giohang/edit/{magh}")
    public String edit(@PathVariable(name = "magh") int magh, Model model) {
        model.addAttribute("magh", magh);
        GioHang gh = gioHangDAO.getById(magh);
        List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);
        model.addAttribute("giohang", gh);

        return "user/giohang/save";
    }



    @GetMapping("/user/giohang/delete/{magh}")
    public String delete(@PathVariable(name = "magh") int magh) {
        gioHangDAO.deleteById(magh);
        return "redirect:/user/giohang/index";
    }

    @PostMapping("/savegh")
    public String save(@Valid @ModelAttribute("giohang")   GioHang gioHang, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
            model.addAttribute("listctg", listctg);
            return "user/giohang/save";
        }
        gioHangDAO.save(gioHang);
        return "redirect:/user/giohang/index";
    }



}


