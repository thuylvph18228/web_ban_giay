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
import poly.edu.DAO.SizeDAO;
import poly.edu.Entity.KhachHang;
import poly.edu.Entity.Size;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SizeController {
    @Autowired
    private SizeDAO sizeDAO;


    @GetMapping("/size/index")
    public String listS(Model model) {
        List<Size> lists = sizeDAO.findAll();
        model.addAttribute("lists", lists);
        return ("size/index");
    }

    @GetMapping("/size/create")
    public String create(@ModelAttribute("size") Size size, Model model) {
        List<Size> lists = sizeDAO.findAll();
        model.addAttribute("lists", lists);
        model.addAttribute("saves", "/saves");

        return "size/save";
    }

    @GetMapping("/size/edit/{mas}")
    public String edit(@PathVariable(name = "mas") int mas, Model model) {
        model.addAttribute("mas", mas);
        Size size = sizeDAO.getById(mas);
        model.addAttribute("size", size);
        model.addAttribute("saves", "/saves");
        return "size/save";
    }

    @GetMapping("/size/delete/{mas}")
    public String delete(@PathVariable(name = "mas") int mas) {
        sizeDAO.deleteById(mas);
        return "redirect:/size/index";
    }

    @PostMapping("/saves")
    public String saves(@Valid @ModelAttribute("size")   Size size, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "size/save";
        }

        sizeDAO.save(size);
        return "redirect:/size/index";
    }

}
