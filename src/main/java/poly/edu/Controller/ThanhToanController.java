package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import poly.edu.DAO.ThanhToanDAO;
import poly.edu.Entity.ThanhToan;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ThanhToanController {
    @Autowired
    public ThanhToanDAO thanhToanDAO;
    @GetMapping("/thanhtoan/index")
    public String listTT(Model model) {
        List<ThanhToan> listtt = thanhToanDAO.findAll();
        model.addAttribute("listtt", listtt);
        return ("thanhtoan/index");
    }

    @GetMapping("/thanhtoan/create")
    public String create(@ModelAttribute("thanhtoan") ThanhToan thanhToan, Model model) {
        List<ThanhToan> listt = thanhToanDAO.findAll();
        model.addAttribute("listt", listt);
        model.addAttribute("savett", "/savett");

        return "thanhtoan/save";
    }

    @GetMapping("/thanhtoan/edit/{mahttt}")
    public String edit(@PathVariable(name = "mahttt") int mahttt, Model model) {
        model.addAttribute("mahttt", mahttt);
        ThanhToan  thanhtoan = thanhToanDAO.getById(mahttt);
        model.addAttribute("thanhtoan", thanhtoan);
        model.addAttribute("savett", "/savett");
        return "thanhtoan/save";
    }

    @GetMapping("/savett/delete/{mahttt}")
    public String delete(@PathVariable(name = "mahttt") int mahttt) {
        thanhToanDAO.deleteById(mahttt);
        return "redirect:/thanhtoan/index";
    }

    @PostMapping("/savett")
    public String saves(@Valid @ModelAttribute("thanhtoan")   ThanhToan thanhToan, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "thanhtoan/save";
        }

        thanhToanDAO.save(thanhToan);
        return "redirect:/thanhtoan/index";
    }

}
