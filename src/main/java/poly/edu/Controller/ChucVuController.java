package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poly.edu.DAO.ChucVuDAO;

import poly.edu.Entity.ChucVu;


import javax.validation.Valid;
import java.util.List;

@Controller
public class ChucVuController {
    @Autowired
    private ChucVuDAO chucVuDAO;


    @GetMapping("/admin/chucvu/index")
    public String listcv(Model model) {
        List<ChucVu> listcv = chucVuDAO.findAll();
        model.addAttribute("listcv", listcv);
        return ("admin/chucvu/index");
    }

    @GetMapping("/admin/chucvu/create")
    public String create(@ModelAttribute("chucvu") ChucVu chucVu, Model model) {
        List<ChucVu> listcv = chucVuDAO.findAll();
        model.addAttribute("listcv", listcv);
        model.addAttribute("savecv", "/savecv");

        return "admin/chucvu/save";
    }
    RedirectAttributes redirectAttributes;
    @GetMapping("/admin/chucvu/edit/{macv}")
    public String edit(@PathVariable(name = "macv") int macv, Model model) {
        model.addAttribute("macv", macv);
        ChucVu cv = chucVuDAO.getById(macv);
        model.addAttribute("chucvu", cv);
        model.addAttribute("savecv", "/savecv");
        redirectAttributes.addAttribute("message","Cập nhật thành công");
        return "admin/chucvu/save";
    }

    @GetMapping("/admin/chucvu/delete/{macv}")
    public String delete(@PathVariable(name = "macv") int macv) {
        chucVuDAO.deleteById(macv);
        redirectAttributes.addAttribute("message","Xóa thành công");
        return "redirect:/admin/chucvu/index";
    }

    @PostMapping("/savecv")
    public String savecv(@Valid @ModelAttribute("chucvu")   ChucVu chucVu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/chucvu/save";
        }

        chucVuDAO.save(chucVu);
        return "redirect:/admin/chucvu/index";
    }

}
