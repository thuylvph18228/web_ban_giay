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
public class ChucVuController {
    @Autowired
    private ChucVuDAO chucVuDAO;


    @GetMapping("/chucvu/index")
    public String listcv(Model model) {
        List<ChucVu> listcv = chucVuDAO.findAll();
        model.addAttribute("listcv", listcv);
        return ("chucvu/index");
    }

    @GetMapping("/chucvu/create")
    public String create(@ModelAttribute("chucvu") ChucVu chucVu, Model model) {
        List<ChucVu> listcv = chucVuDAO.findAll();
        model.addAttribute("listcv", listcv);
        model.addAttribute("savecv", "/savecv");

        return "chucvu/save";
    }

    @GetMapping("/chucvu/edit/{macv}")
    public String edit(@PathVariable(name = "macv") int macv, Model model) {
        model.addAttribute("macv", macv);
        ChucVu cv = chucVuDAO.getById(macv);
        model.addAttribute("chucvu", cv);
        model.addAttribute("savecv", "/savecv");
        return "chucvu/save";
    }

    @GetMapping("/chucvu/delete/{macv}")
    public String delete(@PathVariable(name = "macv") int macv) {
        chucVuDAO.deleteById(macv);
        return "redirect:/chucvu/index";
    }

    @PostMapping("/savecv")
    public String savecv(@Valid @ModelAttribute("chucvu")   ChucVu chucVu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "chucvu/save";
        }

        chucVuDAO.save(chucVu);
        return "redirect:/chucvu/index";
    }
}
