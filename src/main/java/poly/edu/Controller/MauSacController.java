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
import poly.edu.DAO.MauSacDAO;
import poly.edu.Entity.LoaiGiay;
import poly.edu.Entity.MauSac;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MauSacController {

    @Autowired
    MauSacDAO msdao;

    @GetMapping("/mausac/index")
    public String index(Model model){
        List<MauSac> listms = msdao.findAll();
        model.addAttribute("listms", listms);
        return "mausac/index";
    }

    @GetMapping("/mausac/create")
    public String create(@ModelAttribute("mausac")MauSac mausac, Model model){
        model.addAttribute("savems", "/savems");
        return "mausac/save";
    }

    @GetMapping("/mausac/edit/{mams}")
    public String edit(@PathVariable(name="mams") int mams, Model model){
        model.addAttribute("mams", mams);
        MauSac ms = msdao.getById(mams);
        model.addAttribute("mausac", ms);
        model.addAttribute("savems", "/savems");
        return "mausac/save";
    }

    @GetMapping("/mausac/delete/{mams}")
    public String delete(@PathVariable(name="mams") int mams){
        msdao.deleteById(mams);
        return "redirect:/mausac/index";
    }

    @PostMapping("/savems")
    public String saveg(@Valid @ModelAttribute("mausac") MauSac mausac, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "mausac/save";
        }else {
            msdao.save(mausac);
            return "redirect:/mausac/index";
        }
    }
}
