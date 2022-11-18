package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.SizeDAO;
import poly.edu.Entity.Size;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SizeController {
    @Autowired
    private SizeDAO sizeDAO;


    @GetMapping("/admin/size/index")
    public String listS(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Size> lists = sizeDAO.findAll(pageable);
        int firstPage = 0;
        int totalPages = lists.getTotalPages()-1;
        int end = lists.getTotalPages()-1;
        int begin = 0;
        int index = lists.getNumber();
        int pre = lists.getNumber()-1;
        int next = lists.getNumber()+1;
        String baseUrl = "/admin/size/index?page=";

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("lists", lists);
        return ("admin/size/index");
    }

    @GetMapping("/admin/size/create")
    public String create(@ModelAttribute("size") Size size, Model model) {
        List<Size> lists = sizeDAO.findAll();
        model.addAttribute("lists", lists);
        model.addAttribute("saves", "/saves");

        return "admin/size/save";
    }

    @GetMapping("/admin/size/edit/{mas}")
    public String edit(@PathVariable(name = "mas") int mas, Model model) {
        model.addAttribute("mas", mas);
        Size size = sizeDAO.getById(mas);
        model.addAttribute("size", size);
        model.addAttribute("saves", "/saves");
        return "admin/size/save";
    }

    @GetMapping("/admin/size/delete/{mas}")
    public String delete(@PathVariable(name = "mas") int mas) {
        sizeDAO.deleteById(mas);
        return "redirect:/admin/size/index";
    }

    @PostMapping("/saves")
    public String saves(@Valid @ModelAttribute("size")   Size size, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/size/save";
        }

        sizeDAO.save(size);
        return "redirect:/admin/size/index";
    }

}
