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


    @GetMapping("/admin/khachhang/index")
    public String listkh(Model model) {
        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        return ("admin/khachhang/index");
    }

    @GetMapping("/admin/khachhang/create")
    public String create(@ModelAttribute("khachhang") KhachHang khachHang, Model model) {
        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        model.addAttribute("savekh", "/savekh");

        return "admin/khachhang/save";
    }

    @GetMapping("/admin/khachhang/edit/{makh}")
    public String edit(@PathVariable(name = "makh") int makh, Model model) {
        model.addAttribute("makh", makh);
        KhachHang kh = khachHangDAO.getById(makh);
        model.addAttribute("khachhang", kh);
        model.addAttribute("savekh", "/savekh");
        return "admin/khachhang/save";
    }

    @GetMapping("/admin/khachhang/delete/{makh}")
    public String delete(@PathVariable(name = "makh") int makh) {
        khachHangDAO.deleteById(makh);
        return "redirect:/admin/khachhang/index";
    }

    @PostMapping("/savekh")
    public String savenv(@Valid @ModelAttribute("khachhang")   KhachHang khachHang, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/khachhang/save";
        }

        khachHangDAO.save(khachHang);
        return "redirect:/admin/khachhang/index";
    }

}


