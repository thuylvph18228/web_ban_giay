package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.Entity.KhachHang;


import javax.validation.Valid;
import java.util.List;

@Controller
public class KhachHangController {

    @Autowired
    private KhachHangDAO khachHangDAO;


    @GetMapping("/khachhang/index")
    public String listkh(Model model) {
        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        return ("khachhang/index");
    }

    @GetMapping("/khachhang/create")
    public String create(@ModelAttribute("khachhang") KhachHang khachHang, Model model) {
        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        model.addAttribute("savekh", "/savekh");

        return "khachhang/save";
    }

    @GetMapping("/khachhang/edit/{makh}")
    public String edit(@PathVariable(name = "makh") int makh, Model model) {
        model.addAttribute("makh", makh);
        KhachHang kh = khachHangDAO.getById(makh);
        model.addAttribute("khachhang", kh);
        model.addAttribute("savekh", "/savekh");
        return "khachhang/save";
    }

    @GetMapping("/khachhang/delete/{makh}")
    public String delete(@PathVariable(name = "makh") int manv) {
        khachHangDAO.deleteById(manv);
        return "redirect:/khachhang/index";
    }

    @PostMapping("/savekh")
    public String savenv(@Valid @ModelAttribute("khachhang")   KhachHang khachHang, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Form ko hợp lệ");
            return ("/khachhang/create");
        } else {
            try {
                khachHangDAO.save(khachHang);
                return "redirect:/khachhang/index";
            } catch (Exception e) {
                System.out.println(e);
            }
            return ("/khachhang/create");
        }

    }

}
