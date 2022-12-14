package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.*;
import poly.edu.Entity.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BuyController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    KhachHangDAO khachHangDao;


    @Autowired
    GiayDAO giaydao;


    @Autowired
    SizeDAO sdao;

    @Autowired
    NsxDAO nsxdao;


    @Autowired
    LoaiGiayDAO lgdao;


    @Autowired
    GioHangDAO gioHangDao;

    @Autowired
    ChiTietGiayDAO chiTietGiayDAO;

    @GetMapping("/user/giohang/ghk")
    public String ghk(Model model) {

//        String email = (String) httpSession.getAttribute("email");
//        KhachHang khachHang = khachHangDao.findByEmailEquals(email);
//        List<GioHang> listgh = (List<GioHang>) gioHangDao.findByMakh(khachHang.getMakh());
//        model.addAttribute("listgh", listgh);
        List<Giay> listg = giaydao.findAll();
        model.addAttribute("listg", listg);
        List<KhachHang> listkh = khachHangDao.findAll();
        model.addAttribute("listkh", listkh);
        return ("user/giohang/giohangkhach");
    }

    @GetMapping("/user/giohang/editkh/{magh}")
    public String edit(@PathVariable(name = "magh") int magh, Model model) {
        model.addAttribute("magh", magh);
        GioHang gh = gioHangDao.getById(magh);

        Giay giay = (Giay) giaydao.getById(gh.getMactg());
        model.addAttribute("listg", giay);
//        String email = (String) httpSession.getAttribute("email");
//        KhachHang khachHang = khachHangDao.findByEmailEquals(email);
//        model.addAttribute("listkh", khachHang);
        model.addAttribute("giohang", gh);
        model.addAttribute("savegh", "/saveghk");
        return "user/giohang/savekh";
    }



    @PostMapping("/savegiohang")
    public String savegh(@ModelAttribute("chitietgiay") ChiTietGiay chiTietGiay, GioHang gioHang) {
        gioHang.setMactg(chiTietGiay.getMactg());
        gioHang.setSoluong(1);
        gioHangDao.save(gioHang);
        return "redirect:/giohang/indext";
    }

    @GetMapping("/user/giohang/deleteghk/{magh}")
    public String delete(@PathVariable(name = "magh") int magh) {
        gioHangDao.deleteById(magh);
        return "redirect:/user/giohang/ghk";
    }

    @PostMapping("/saveghk")
    public String save(@Valid @ModelAttribute("giohang") GioHang gioHang, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return "redirect:/user/giohang/ghk";
        }
        gioHangDao.save(gioHang);
        return "redirect:/user/giohang/ghk";
    }
}
