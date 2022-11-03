package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.*;
import poly.edu.Entity.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ChiTietGiayController {

    @Autowired
    ChiTietGiayDAO ctgdao;

    @Autowired
    SizeDAO sdao;

    @Autowired
    NsxDAO nsxdao;

    @Autowired
    LoaiGiayDAO lgdao;

    @Autowired
    GiayDAO gdao;

    @GetMapping("/admin/chitietgiay/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size){
        List<Size> listsize = sdao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        List<LoaiGiay> listlg = lgdao.findAll();
        List<Giay> listg = gdao.findAll();

        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietGiay> p = this.ctgdao.findAll(pageable);

        int totalPages = p.getTotalPages()-1;
        int end = p.getTotalPages()-1;
        int begin = 0;
        int index = p.getNumber();
        int pre = p.getNumber()-1;
        int next = p.getNumber()+1;
        String baseUrl = "/admin/chitietgiay/index?page=";

        model.addAttribute("listctg", p);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

        model.addAttribute("listsize", listsize);
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listlg", listlg);
        model.addAttribute("listg", listg);
        return "admin/chitietgiay/index";
    }

    @GetMapping("/admin/chitietgiay/create")
    public String create(@ModelAttribute("chitietgiay")ChiTietGiay chitietgiay, Model model){
        List<Size> listsize = sdao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        List<LoaiGiay> listlg = lgdao.findAll();
        List<Giay> listg = gdao.findAll();

        model.addAttribute("listsize", listsize);
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listlg", listlg);
        model.addAttribute("listg", listg);
        model.addAttribute("savectg", "/savectg");
        return "admin/chitietgiay/save";
    }

    @GetMapping("/admin/chitietgiay/edit/{mactg}")
    public String edit(@PathVariable(name="mactg") int mactg, Model model){
        model.addAttribute("mactg", mactg);
        ChiTietGiay ctg = ctgdao.getById(mactg);
        List<Size> listsize = sdao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        List<LoaiGiay> listlg = lgdao.findAll();
        List<Giay> listg = gdao.findAll();

        model.addAttribute("listsize", listsize);
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listlg", listlg);
        model.addAttribute("listg", listg);
        model.addAttribute("chitietgiay", ctg);
        model.addAttribute("savectg", "/savectg");
        return "admin/chitietgiay/save";
    }

    @GetMapping("/admin/chitietgiay/delete/{mactg}")
    public String delete(@PathVariable(name="mactg") int mactg){
        ctgdao.deleteById(mactg);
        return "redirect:/admin/chitietgiay/index";
    }

    @PostMapping("/savectg")
    public String save(@Valid @ModelAttribute("chitietgiay") ChiTietGiay chitietgiay, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "chitietgiay/save";
        }else {
            ctgdao.save(chitietgiay);
            return "redirect:/chitietgiay/index";
        }
    }
}
