package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.Entity.KhachHang;


import javax.validation.Valid;
import java.util.List;

@Controller
public class KhachHangController {

    @Autowired
    private KhachHangDAO khachHangDAO;


    @GetMapping("/admin/khachhang/index")
    public String listkh(Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<KhachHang> listkh = khachHangDAO.findAll(pageable);
        int firstPage = 0;
        int totalPages = listkh.getTotalPages()-1;
        int end = listkh.getTotalPages()-1;
        int begin = 0;
        int index = listkh.getNumber();
        int pre = listkh.getNumber()-1;
        int next = listkh.getNumber()+1;
        String baseUrl = "/admin/khachhang/index?page=";

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

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


