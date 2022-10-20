package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import poly.edu.DAO.*;
import poly.edu.Entity.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ChiTietHoaDonController {

    @Autowired
    ChiTietGiayDAO ctgdao;

    @Autowired
    ChiTietHoaDonDAO cthddao;

    @Autowired
    HoaDonDAO hddao;

    @Autowired
    GiayDAO gdao;

    @GetMapping("/chitiethoadon/index")
    public String index(Model model){
        List<ChiTietHoaDon> listcthd = cthddao.findAll();
        List<ChiTietGiay> listctg = ctgdao.findAll();
        List<HoaDon> listhd = hddao.findAll();
        List<Giay> listg = gdao.findAll();

        model.addAttribute("listcthd", listcthd);
        model.addAttribute("listctg", listctg);
        model.addAttribute("listhd", listhd);
        model.addAttribute("listg", listg);
        return "chitiethoadon/index";
    }

    @GetMapping("/chitiethoadon/create")
    public String create(@ModelAttribute("chitiethoadon")ChiTietHoaDon chitiethoadon, Model model){
        List<ChiTietGiay> listctg = ctgdao.findAll();
        List<HoaDon> listhd = hddao.findAll();

        model.addAttribute("listctg", listctg);
        model.addAttribute("listhd", listhd);
        model.addAttribute("savecthd", "/savecthd");
        return "chitiethoadon/save";
    }

    @GetMapping("/chitiethoadon/edit/{macthd}")
    public String edit(@PathVariable(name="macthd") int macthd, Model model){
        model.addAttribute("macthd", macthd);
        ChiTietHoaDon cthd = cthddao.getById(macthd);
        List<ChiTietGiay> listctg = ctgdao.findAll();
        List<HoaDon> listhd = hddao.findAll();
        List<Giay> listg = gdao.findAll();

        model.addAttribute("listctg", listctg);
        model.addAttribute("listhd", listhd);
        model.addAttribute("listg", listg);
        model.addAttribute("chitiethoadon", cthd);
        model.addAttribute("savecthd", "/savecthd");
        return "chitiethoadon/save";
    }

    @GetMapping("/chitiethoadon/delete/{macthd}")
    public String delete(@PathVariable(name="macthd") int macthd){
        cthddao.deleteById(macthd);
        return "redirect:/chitiethoadon/index";
    }

    @PostMapping("/savecthd")
    public String save(@Valid @ModelAttribute("chitiethoadon") ChiTietHoaDon chitiethoadon, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "chitiethoadon/save";
        }else {
            cthddao.save(chitiethoadon);
            return "redirect:/chitiethoadon/index";
        }
    }
}
