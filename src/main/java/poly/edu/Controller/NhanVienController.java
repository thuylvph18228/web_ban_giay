package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.ChucVuDAO;
import poly.edu.DAO.NhanVienDAO;
import poly.edu.Entity.ChucVu;
import poly.edu.Entity.NhanVien;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NhanVienController {

    @Autowired
    ChucVuDAO cvdao;

    @Autowired
    NhanVienDAO nvdao;

    @GetMapping("/rest/admin/nhanvien/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size){
        List<ChucVu> listcv = cvdao.findAll();

        Pageable pageable = PageRequest.of(page, size);
        Page<NhanVien> listnv = nvdao.findAll(pageable);
        int firstPage = 0;
        int totalPages = listnv.getTotalPages()-1;
        int end = listnv.getTotalPages()-1;
        int begin = 0;
        int index = listnv.getNumber();
        int pre = listnv.getNumber()-1;
        int next = listnv.getNumber()+1;
        String baseUrl = "/rest/admin/nhanvien/index?page=";

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

        model.addAttribute("listcv", listcv);
        model.addAttribute("listnv", listnv);
        return "admin/nhanvien/index";
    }

    @GetMapping("/admin/nhanvien/create")
    public String create(@ModelAttribute("nhanvien")NhanVien nhanvien, Model model){
        List<ChucVu> listcv = cvdao.findAll();
        model.addAttribute("listcv", listcv);
        model.addAttribute("savenv", "/savenv");
        return "admin/nhanvien/save";
    }

    @GetMapping("/admin/nhanvien/edit/{manv}")
    public String edit(@PathVariable(name="manv") int manv, Model model){
        model.addAttribute("manv", manv);
        NhanVien nv = nvdao.getById(manv);
        List<ChucVu> listcv = cvdao.findAll();
        model.addAttribute("listcv", listcv);
        model.addAttribute("nhanvien", nv);
        model.addAttribute("savenv", "/savenv");
        return "admin/nhanvien/save";
    }

    @GetMapping("/admin/nhanvien/delete/{manv}")
    public String delete(@PathVariable(name="manv") int manv){
        nvdao.deleteById(manv);
        return "redirect:/admin/nhanvien/index";
    }

    @PostMapping("/savenv")
    public String savenv(@Valid @ModelAttribute("nhanvien") NhanVien nhanvien ,Model  model , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ChucVu> listcv = cvdao.findAll();
            model.addAttribute("listcv", listcv);
            return "admin/nhanvien/save";
        }else {
            nvdao.save(nhanvien);
            return "redirect:/admin/nhanvien/index";
        }
    }
}
