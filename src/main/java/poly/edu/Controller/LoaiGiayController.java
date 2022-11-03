package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import poly.edu.DAO.LoaiGiayDAO;
import poly.edu.Entity.LoaiGiay;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoaiGiayController {

    @Autowired
    LoaiGiayDAO loaigiaydao;

    @GetMapping("/admin/loaigiay/index")
    public String index(Model model){
        List<LoaiGiay> listlg = loaigiaydao.findAll();
        model.addAttribute("listlg", listlg);
        return "admin/loaigiay/index";
    }

    @GetMapping("/admin/loaigiay/create")
    public String create(@ModelAttribute("loaigiay")LoaiGiay loaigiay, Model model){
        model.addAttribute("savelg", "/savelg");
        return "admin/loaigiay/save";
    }

    @GetMapping("/admin/loaigiay/edit/{malg}")
    public String edit(@PathVariable(name="malg") int malg, Model model){
        model.addAttribute("malg", malg);
        LoaiGiay lg = loaigiaydao.getById(malg);
        model.addAttribute("loaigiay", lg);
        model.addAttribute("savelg", "/savelg");
        return "admin/loaigiay/save";
    }

    @GetMapping("/admin/loaigiay/delete/{malg}")
    public String delete(@PathVariable(name="malg") int malg){
        loaigiaydao.deleteById(malg);
        return "redirect:/admin/loaigiay/index";
    }

    @PostMapping("/savelg")
    public String saveg(@Valid @ModelAttribute("loaigiay") LoaiGiay loaigiay, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/loaigiay/save";
        }else {
            loaigiaydao.save(loaigiay);
            return "redirect:/admin/loaigiay/index";
        }
    }
}
