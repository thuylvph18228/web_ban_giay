package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.GiayDAO;
import poly.edu.DAO.GioHangDAO;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.Entity.Giay;
import poly.edu.Entity.GioHang;
import poly.edu.Entity.KhachHang;

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
    GioHangDAO gioHangDao;

    @GetMapping("/giohang/ghk")
    public String ghk( Model model) {

        String email = (String) httpSession.getAttribute("email");
        KhachHang khachHang =  khachHangDao.findByEmailEquals(email);
        List<GioHang> listgh = (List<GioHang>) gioHangDao.findByMakh(khachHang.getMakh());
        System.out.println(listgh);
        model.addAttribute("listgh", listgh);
        List<Giay> listg =giaydao.findAll();
        model.addAttribute("listg", listg);
        List<KhachHang> listkh =khachHangDao.findAll();
        model.addAttribute("listkh", listkh);
            return ("giohang/giohangkhach");
    }
    @GetMapping("/giohang/editkh/{magh}")
    public String edit(@PathVariable(name = "magh") int magh, Model model) {
        model.addAttribute("magh", magh);
        GioHang gh = gioHangDao.getById(magh);

        Giay giay = (Giay) giaydao.getById(gh.getMag());
        model.addAttribute("listg", giay);
        String email = (String) httpSession.getAttribute("email");
        KhachHang khachHang =  khachHangDao.findByEmailEquals(email);
        model.addAttribute("listkh", khachHang);
        model.addAttribute("giohang", gh);
        model.addAttribute("savegh", "/saveghk");
        return "giohang/savekh";
    }

    @GetMapping("/giay/buy/{mag}")
    public String buy(@ModelAttribute("giohang") GioHang gioHang, @PathVariable(name="mag") int mag, Model model){
        model.addAttribute("mag", mag);
        String email = (String) httpSession.getAttribute("email");
        KhachHang khachHang =  khachHangDao.findByEmailEquals(email);
        System.out.println(khachHang);
        model.addAttribute("khachHang", khachHang);
        Giay g = giaydao.getById(mag);
        httpSession.setAttribute(String.valueOf(mag),"mag");
        model.addAttribute("giay", g);
        model.addAttribute("savegiohang", "/savegiohang");
        return "giay/buy";
    }

    @PostMapping("/savegiohang")
    public String savegh(@RequestParam("soluong") int soluong, @RequestParam("mag") int mag,
                         @RequestParam("diachi") String diachi, @RequestParam("sdt") String sdt,
                         GioHang gioHang, Model model){
        String email = (String) httpSession.getAttribute("email");
        KhachHang khachHang =  khachHangDao.findByEmailEquals(email);
        gioHang.setMakh(khachHang.getMakh());
        String date = String.valueOf(java.time.LocalDate.now());
        gioHang.setMag(mag);
        gioHang.setSoluong(soluong);
        gioHang.setDiachi(diachi);
        gioHang.setSdt(sdt);
        gioHang.setNgaytao(String.valueOf(date));
        gioHang.setTrangthai(Integer.parseInt("0"));
        gioHangDao.save(gioHang);
        return "redirect:/giohang/ghk";
    }

    @GetMapping("/giohang/deleteghk/{magh}")
    public String delete(@PathVariable(name = "magh") int magh) {
        gioHangDao.deleteById(magh);
        return "redirect:/giohang/ghk";
    }

    @PostMapping("/saveghk")
    public String save(@Valid @ModelAttribute("giohang")   GioHang gioHang, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return "redirect:/giohang/ghk";
        }
        gioHang.setMagh(gioHang.getMagh());
        gioHang.setMakh(gioHang.getMakh());
        gioHang.setDiachi(gioHang.getDiachi());
        gioHang.setSdt(gioHang.getSdt());
        gioHang.setSoluong(gioHang.getSoluong());
        gioHangDao.save(gioHang);
        return "redirect:/giohang/ghk";
    }
}
