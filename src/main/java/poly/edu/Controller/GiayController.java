package poly.edu.Controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import poly.edu.DAO.*;
import poly.edu.Entity.Giay;

import poly.edu.Entity.Nsx;
import poly.edu.Entity.ThongBao;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class GiayController {
    @Autowired
    HttpSession httpSession;


    @Autowired
    ThongBaoDAO thongBaoDAO;

    @Autowired
    KhachHangDAO khachHangDao;

    @Autowired
    NsxDAO nsxdao;
    @Autowired
    GiayDAO giaydao;

    @Autowired
    GioHangDAO gioHangDao;

    @GetMapping("/admin/giay/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Giay> p = this.giaydao.findAll(pageable);

        int totalPages = p.getTotalPages()-1;
        int end = p.getTotalPages()-1;
        int begin = 0;
        int index = p.getNumber();
        int pre = p.getNumber()-1;
        int next = p.getNumber()+1;
        String baseUrl = "/admin/giay/index?page=";

        List<ThongBao> listtb = thongBaoDAO.findByThongBaoChuaXem();
//        model.addAttribute("size", size);
        model.addAttribute("listtb", listtb);
        httpSession.setAttribute("sizeth",listtb.size());
//        httpSession.setAttribute("listb",listtb);

        model.addAttribute("listg", p);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("giayfindnamelike", "/giayfindnamelike");
        model.addAttribute("giayfindnsx", "/giayfindnsx");
        model.addAttribute("upload", "/admin/giay/upload");
        return "admin/giay/index";
    }

    @GetMapping("/giay/product")
    public String product(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "8") int size) {
        List<Nsx> listnsx = nsxdao.findAll();
        Pageable pageable = PageRequest.of(page, size);
        Page<Giay> p = this.giaydao.findAll(pageable);
        List<Giay> pn = this.giaydao.findByTop5New();
        List<Giay> ps = this.giaydao.findBySellingTop5();

        int totalPages = p.getTotalPages()-1;
        int end = p.getTotalPages()-1;
        int begin = 0;
        int index = p.getNumber();
        int pre = p.getNumber()-1;
        int next = p.getNumber()+1;
        String baseUrl = "/giay/product?page=";

        model.addAttribute("listg", p);
        model.addAttribute("listgn", pn);
        model.addAttribute("listgs", ps);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

        model.addAttribute("listnsx", listnsx);
        model.addAttribute("giayfindnamelike", "/giayfindnamelike");
        model.addAttribute("giayfindnsx", "/giayfindnsx");
        return "user/giay/product";
    }


    @GetMapping("/giayfindnamelike")
    public String findbyNameLike(Model model, @RequestParam("name") String tengiay,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Giay> p = this.giaydao.findByNameLike(tengiay, pageable);

        int totalPages = p.getTotalPages()-1;
        int end = p.getTotalPages()-1;
        int begin = 0;
        int index = p.getNumber();
        int pre = p.getNumber()-1;
        int next = p.getNumber()+1;
        String baseUrl = "/giay/product?page=";

        model.addAttribute("listg", p);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);
        return "user/giay/product";
    }

    @GetMapping("/giayfindnsx/{mansx}")
    public String findbyNSX(@PathVariable("mansx") int mansx, Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "8") int size) {
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);
        Pageable pageable = PageRequest.of(page, size);
        Page<Giay> p = this.giaydao.findByNsx(mansx, pageable);

        int totalPages = p.getTotalPages()-1;
        int end = p.getTotalPages()-1;
        int begin = 0;
        int index = p.getNumber();
        int pre = p.getNumber()-1;
        int next = p.getNumber()+1;
        String baseUrl = "/giay/product?page=";

        model.addAttribute("listg", p);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);
        return "user/giay/product";
    }

    @GetMapping("/admin/giay/create")
    public String create(@ModelAttribute("giay") Giay giay, Model model) {
        model.addAttribute("saveg", "/saveg");
        return "admin/giay/save";
    }

    @GetMapping("/admin/giay/edit/{mag}")
    public String edit(@PathVariable(name = "mag") int mag, Model model) {
        model.addAttribute("mag", mag);
        Giay g = giaydao.getById(mag);
        model.addAttribute("giay", g);
        model.addAttribute("saveg", "/saveg");
        return "admin/giay/save";
    }

    @GetMapping("/admin/giay/delete/{mag}")
    public String delete(@PathVariable(name = "mag") int mag) {
        giaydao.deleteById(mag);
        return "redirect:/admin/giay/index";
    }

    @PostMapping("/saveg")
    public String saveg(@Valid @ModelAttribute("giay") Giay giay, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:admin/giay/create";
        } else {
            giaydao.save(giay);
            return "redirect:/admin/giay/index";
        }
    }

}
