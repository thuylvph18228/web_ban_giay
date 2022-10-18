package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import poly.edu.DAO.ChucVuDAO;
import poly.edu.DAO.GiayDAO;
import poly.edu.DAO.NhanVienDAO;
import poly.edu.Entity.ChucVu;
import poly.edu.Entity.Giay;
import poly.edu.Entity.NhanVien;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GiayController {

    @Autowired
    GiayDAO giaydao;

    @GetMapping("/giay/index")
    public String index(Model model){
        List<Giay> listg = giaydao.findAll();
        model.addAttribute("listg", listg);
        return "giay/index";
    }

    @GetMapping("/giay/create")
    public String create(@ModelAttribute("giay")Giay giay, Model model){
        model.addAttribute("saveg", "/saveg");
        return "giay/save";
    }

    @GetMapping("/giay/edit/{mag}")
    public String edit(@PathVariable(name="mag") int mag, Model model){
        model.addAttribute("mag", mag);
        Giay g = giaydao.getById(mag);
        model.addAttribute("giay", g);
        model.addAttribute("saveg", "/saveg");
        return "giay/save";
    }

    @GetMapping("/giay/delete/{mag}")
    public String delete(@PathVariable(name="mag") int mag){
        giaydao.deleteById(mag);
        return "redirect:/giay/index";
    }

    @PostMapping("/saveg")
    public String saveg(@Valid @ModelAttribute("giay") Giay giay, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "giay/save";
        }else {
            giaydao.save(giay);
            return "redirect:/giay/index";
        }
    }
}
