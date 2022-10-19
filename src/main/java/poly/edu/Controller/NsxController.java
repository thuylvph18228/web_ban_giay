package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import poly.edu.DAO.NsxDAO;
import poly.edu.DAO.SizeDAO;
import poly.edu.Entity.NSX;
import poly.edu.Entity.Size;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NsxController {
    @Autowired
    private NsxDAO nsxDAO;


    @GetMapping("/nsx/index")
    public String listnsx(Model model) {
        List<NSX> listnsx = nsxDAO.findAll();
        model.addAttribute("listnsx", listnsx);
        return ("nsx/index");
    }

    @GetMapping("/nsx/create")
    public String create(@ModelAttribute("nsx") NSX nsx, Model model) {

        model.addAttribute("savensx", "/savensx");

        return "nsx/save";
    }

    @GetMapping("/nsx/edit/{mansx}")
    public String edit(@PathVariable(name = "mansx") int mansx, Model model) {
        model.addAttribute("mansx", mansx);
        NSX nsx =  nsxDAO.getById(mansx);
        model.addAttribute("nsx", nsx);
        model.addAttribute("savensx", "/savensx");
        return "nsx/save";
    }

    @GetMapping("/nsx/delete/{mansx}")
    public String delete(@PathVariable(name = "mansx") int mansx) {
        nsxDAO.deleteById(mansx);
        return "redirect:/nsx/index";
    }

    @PostMapping("/savensx")
    public String saves(@Valid @ModelAttribute("nsx")   NSX nsx, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "nsx/save";
        }
        nsxDAO.save(nsx);
        return "redirect:/nsx/index";
    }
}
