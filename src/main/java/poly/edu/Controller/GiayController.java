package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.GiayDAO;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.DAO.GioHangDAO;
import poly.edu.Entity.Giay;
import poly.edu.Entity.GioHang;
import poly.edu.Entity.KhachHang;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class GiayController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    KhachHangDAO khachHangDao;

    @Autowired
    GiayDAO giaydao;

    @Autowired
    GioHangDAO gioHangDao;

    @GetMapping("/giay/index")
    public String index(Model model){
        List<Giay> listg = giaydao.findAll();
        model.addAttribute("listg", listg);
        return "giay/index";
    }

    @GetMapping("/giay/product")
    public String product(Model model){
        List<Giay> listg = giaydao.findAll();
        model.addAttribute("listg", listg);
        model.addAttribute("giayfindname", "/giayfindname");
        model.addAttribute("giayfindnsx", "/giayfindnsx");
        return "giay/product";
    }
    @GetMapping("/giayfindname")
    public String findbyName( Model model,@RequestParam("tengiay") String tengiay){
        List<Giay> listg = giaydao.findByName(tengiay);
        model.addAttribute("listg", listg);
        return "giay/product";
    }

    @GetMapping("/giayfindnsx")
    public String findbyNSX( Model model,@RequestParam("mansx") int mansx){
        List<Giay> listg = giaydao.findByNsx(mansx);
        model.addAttribute("listg", listg);
        return "giay/product";
    }
    @GetMapping("/giay/create")
    public String create(@ModelAttribute("giay")Giay giay, Model model){
        model.addAttribute("saveg", "/saveg");
        return "giay/save";
    }

    @GetMapping("/giay/edit/{mag}")
    public String edit(@PathVariable(name="mag") int mag, Model model){
        model.addAttribute("mag", mag);
        Giay g = giaydao.getById(mag);
        model.addAttribute("giay", g);
        model.addAttribute("saveg", "/saveg");
        return "giay/save";
    }

    @GetMapping("/giay/delete/{mag}")
    public String delete(@PathVariable(name="mag") int mag){
        giaydao.deleteById(mag);
        return "redirect:/giay/index";
    }
//    @GetMapping("/giay/buy/{mag}")
//    public String buy(@PathVariable(name="mag") int mag, Model model){
//        model.addAttribute("mag", mag);
//        String email = (String) httpSession.getAttribute("email");
//        KhachHang khachHang =  khachHangDao.findByEmailEquals(email);
//        System.out.println(khachHang);
//        model.addAttribute("khachHang", khachHang);
//        Giay g = giaydao.getById(mag);
//        httpSession.setAttribute(String.valueOf(mag),"mag");
//        model.addAttribute("giay", g);
//        model.addAttribute("savegiohang", "/savegiohang");
//        return "giay/buy";
//    }
//
//    @PostMapping("/savegiohang")
//    public String savegh( @RequestParam("soluong") int soluong,@RequestParam("mag") int mag,
//                          @RequestParam("diachi") String diachi,@RequestParam("sdt") String sdt,
//                           GioHang gioHang, Model model){
//        String email = (String) httpSession.getAttribute("email");
//        KhachHang khachHang =  khachHangDao.findByEmailEquals(email);
//        gioHang.setMakh(khachHang.getMakh());
//        String date = String.valueOf(java.time.LocalDate.now());
//        gioHang.setMag(mag);
//        gioHang.setSoluong(soluong);
//        gioHang.setDiachi(diachi);
//        gioHang.setSdt(sdt);
//        gioHang.setNgaytao(String.valueOf(date));
//        gioHang.setTrangthai(Integer.parseInt("0"));
//        gioHangDao.save(gioHang);
//        return "redirect:/giohang/index";
//    }


    @PostMapping("/saveg")
    public String saveg(@Valid @ModelAttribute("giay") Giay giay, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "giay/save";
        }else {
            giaydao.save(giay);
            return "redirect:/giay/index";
        }
    }

}
