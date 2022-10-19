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
public class ChiTietGiayController {

    @Autowired
    ChiTietGiayDAO ctgdao;

    @Autowired
    SizeDAO sdao;

    @Autowired
    NsxDAO nsxdao;

    @Autowired
    MauSacDAO msdao;

    @Autowired
    LoaiGiayDAO lgdao;

    @Autowired
    GiayDAO gdao;

    @GetMapping("/chitietgiay/index")
    public String index(Model model){
        List<ChiTietGiay> listctg = ctgdao.findAll();
        List<Size> listsize = sdao.findAll();
        List<NSX> listnsx = nsxdao.findAll();
        List<MauSac> listms = msdao.findAll();
        List<LoaiGiay> listlg = lgdao.findAll();
        List<Giay> listg = gdao.findAll();

        model.addAttribute("listctg", listctg);
        model.addAttribute("listsize", listsize);
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listms", listms);
        model.addAttribute("listlg", listlg);
        model.addAttribute("listg", listg);
        return "chitietgiay/index";
    }

    @GetMapping("/chitietgiay/create")
    public String create(@ModelAttribute("chitietgiay")ChiTietGiay chitietgiay, Model model){
        List<Size> listsize = sdao.findAll();
        List<NSX> listnsx = nsxdao.findAll();
        List<MauSac> listms = msdao.findAll();
        List<LoaiGiay> listlg = lgdao.findAll();
        List<Giay> listg = gdao.findAll();

        model.addAttribute("listsize", listsize);
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listms", listms);
        model.addAttribute("listlg", listlg);
        model.addAttribute("listg", listg);
        model.addAttribute("savectg", "/savectg");
        return "chitietgiay/save";
    }

    @GetMapping("/chitietgiay/edit/{mactg}")
    public String edit(@PathVariable(name="mactg") int mactg, Model model){
        model.addAttribute("mactg", mactg);
        ChiTietGiay ctg = ctgdao.getById(mactg);
        List<Size> listsize = sdao.findAll();
        List<NSX> listnsx = nsxdao.findAll();
        List<MauSac> listms = msdao.findAll();
        List<LoaiGiay> listlg = lgdao.findAll();
        List<Giay> listg = gdao.findAll();

        model.addAttribute("listsize", listsize);
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listms", listms);
        model.addAttribute("listlg", listlg);
        model.addAttribute("listg", listg);
        model.addAttribute("chitietgiay", ctg);
        model.addAttribute("savectg", "/savectg");
        return "chitietgiay/save";
    }

    @GetMapping("/chitietgiay/delete/{mactg}")
    public String delete(@PathVariable(name="mactg") int mactg){
        ctgdao.deleteById(mactg);
        return "redirect:/chitietgiay/index";
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
