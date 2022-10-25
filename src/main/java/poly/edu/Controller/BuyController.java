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
import java.util.Optional;

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

        Giay giay = (Giay) giaydao.getById(gh.getMactg());
        model.addAttribute("listg", giay);
        String email = (String) httpSession.getAttribute("email");
        KhachHang khachHang =  khachHangDao.findByEmailEquals(email);
        model.addAttribute("listkh", khachHang);
        model.addAttribute("giohang", gh);
        model.addAttribute("savegh", "/saveghk");
        return "giohang/savekh";
    }

    @GetMapping("/giay/buy/{mag}")
    public String buy(@ModelAttribute("chitietgiay") ChiTietGiay chiTietGiay, @PathVariable(name="mag") int mag, Model model){

        List<ChiTietGiay> listctg = chiTietGiayDAO.findByMag(mag);
       
        Giay giay = giaydao.getById(mag);
        System.out.println(giay);
        List<Size> listsize = sdao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        List<LoaiGiay> listlg = lgdao.findAll();
        List<Giay> listg = giaydao.findAll();


        model.addAttribute("giay", giay);
        model.addAttribute("listctg", listctg);
        model.addAttribute("listsize", listsize);
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listlg", listlg);

        model.addAttribute("listg", listg);
        return "giay/buy";
    }

    @PostMapping("/savegiohang")
    public String savegh( @Valid @RequestParam("soluong") int soluong, @RequestParam("mag") int mag,
                         @RequestParam("diachi") String diachi, @RequestParam("sdt") String sdt,
                         GioHang gioHang, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "giay/buy";
        }
        String email = (String) httpSession.getAttribute("email");
        KhachHang khachHang =  khachHangDao.findByEmailEquals(email);
        String date = String.valueOf(java.time.LocalDate.now());
        gioHang.setSoluong(soluong);
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
        gioHangDao.save(gioHang);
        return "redirect:/giohang/ghk";
    }
}
