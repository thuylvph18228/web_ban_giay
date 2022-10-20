package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import poly.edu.DAO.GiayDAO;
import poly.edu.DAO.GioHangDAO;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.Entity.Giay;
import poly.edu.Entity.GioHang;
import poly.edu.Entity.KhachHang;

import javax.validation.Valid;
import java.util.List;


@Controller
public class GioHangController {

    @Autowired
    public GiayDAO giayDAO;

    @Autowired
    public KhachHangDAO khachHangDAO;

    @Autowired
    public GioHangDAO gioHangDAO;

    @GetMapping("/giohang/index")
    public String listkh(Model model) {
        List<GioHang> listgh =gioHangDAO.findAll();
        model.addAttribute("listgh", listgh);
        List<Giay> listg =giayDAO.findAll();
        model.addAttribute("listg", listg);
        List<KhachHang> listkh =khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        return ("giohang/index");
    }

    @GetMapping("/giohang/create")
    public String create(@ModelAttribute("giohang") GioHang gioHang, Model model) {


        List<Giay> listg =giayDAO.findAll();
        model.addAttribute("listg", listg);
        List<KhachHang> listkh =khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        model.addAttribute("savegh", "/savegh");
        return "giohang/save";
    }

    @GetMapping("/giohang/edit/{magh}")
    public String edit(@PathVariable(name = "magh") int magh, Model model) {
        model.addAttribute("magh", magh);
        GioHang gh = gioHangDAO.getById(magh);
        List<Giay> listg =giayDAO.findAll();
        model.addAttribute("listg", listg);
        List<KhachHang> listkh =khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        model.addAttribute("giohang", gh);
        model.addAttribute("savegh", "/savegh");
        return "giohang/save";
    }

    @GetMapping("/giohang/delete/{magh}")
    public String delete(@PathVariable(name = "magh") int magh) {
        gioHangDAO.deleteById(magh);
        return "redirect:/giohang/index";
    }

    @PostMapping("/savegh")
    public String save(@Valid @ModelAttribute("giohang")   GioHang gioHang, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            List<Giay> listg =giayDAO.findAll();
            model.addAttribute("listg", listg);
            List<KhachHang> listkh =khachHangDAO.findAll();
            model.addAttribute("listkh", listkh);
            return "giohang/save";
        }
        gioHangDAO.save(gioHang);
        return "redirect:/giohang/index";
    }

}


