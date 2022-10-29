package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.GiayDAO;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.DAO.GioHangDAO;
import poly.edu.DAO.NsxDAO;
import poly.edu.Entity.Giay;

import poly.edu.Entity.Nsx;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import java.util.List;

@Controller
public class GiayController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    KhachHangDAO khachHangDao;

    @Autowired
    NsxDAO nsxdao;
    @Autowired
    GiayDAO giaydao;

    @Autowired
    GioHangDAO gioHangDao;

    @GetMapping("/giay/index")
    public String index(Model model){
        List<Giay> listg = giaydao.findAll();
        model.addAttribute("listg", listg);
        return "giay/index";
    }

    @GetMapping("/giay/product")
    public String product(Model model){
        List<Giay> listg = giaydao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listg", listg);
        model.addAttribute("giayfindname", "/giayfindname");

        return "giay/product";
    }
    @GetMapping("/giayfindname")
    public String findbyName( Model model,@RequestParam("tengiay") String tengiay){
        List<Giay> listg = giaydao.findByName(tengiay);
        model.addAttribute("listg", listg);
        return "giay/product";
    }

    @GetMapping("/giayfindnsx/{mansx}")
    public String findbyNSX( @PathVariable("mansx") int mansx,Model model){
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);
        List<Giay> listg = giaydao.findByNsx(mansx);
        model.addAttribute("listg", listg);
        return "giay/product";
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
