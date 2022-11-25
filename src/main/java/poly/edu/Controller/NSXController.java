package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.NsxDAO;
import poly.edu.Entity.Nsx;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NSXController {
    @Autowired
    private NsxDAO nsxDAO;


    @GetMapping("/admin/nsx/index")
    public String listnsx(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Nsx> listnsx = nsxDAO.findAll(pageable);
        int firstPage = 0;
        int totalPages = listnsx.getTotalPages()-1;
        int end = listnsx.getTotalPages()-1;
        int begin = 0;
        int index = listnsx.getNumber();
        int pre = listnsx.getNumber()-1;
        int next = listnsx.getNumber()+1;
        String baseUrl = "/admin/nsx/index?page=";

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("listnsx", listnsx);
        return ("admin/nsx/index");
    }

    @GetMapping("/admin/nsx/create")
    public String create(@ModelAttribute("nsx") Nsx nsx, Model model) {

        model.addAttribute("savensx", "/savensx");

        return "admin/nsx/save";
    }

    @GetMapping("/admin/nsx/edit/{mansx}")
    public String edit(@PathVariable(name = "mansx") int mansx, Model model) {
        model.addAttribute("mansx", mansx);
        Nsx nsx =  nsxDAO.getById(mansx);
        model.addAttribute("nsx", nsx);
        model.addAttribute("savensx", "/savensx");
        return "admin/nsx/save";
    }

    @GetMapping("/admin/nsx/delete/{mansx}")
    public String delete(@PathVariable(name = "mansx") int mansx) {
        nsxDAO.deleteById(mansx);
        return "redirect:/admin/nsx/index";
    }

    @PostMapping("/savensx")
    public String saves(@Valid @ModelAttribute("nsx")   Nsx nsx, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/nsx/save";
        }
        nsxDAO.save(nsx);
        return "redirect:/admin/nsx/index";
    }
}
