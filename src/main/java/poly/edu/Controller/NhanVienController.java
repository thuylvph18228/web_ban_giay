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

    @GetMapping("/nhanvien/index")
    public String index(Model model){
        List<ChucVu> listcv = cvdao.findAll();
        List<NhanVien> listnv = nvdao.findAll();
        model.addAttribute("listcv", listcv);
        model.addAttribute("listnv", listnv);
        return "nhanvien/index";
    }

    @GetMapping("/nhanvien/create")
    public String create(@ModelAttribute("nhanvien")NhanVien nhanvien, Model model){
        List<ChucVu> listcv = cvdao.findAll();
        model.addAttribute("listcv", listcv);
        model.addAttribute("savenv", "/savenv");
        return "nhanvien/save";
    }

    @GetMapping("/nhanvien/edit/{manv}")
    public String edit(@PathVariable(name="manv") int manv, Model model){
        model.addAttribute("manv", manv);
        NhanVien nv = nvdao.getById(manv);
        List<ChucVu> listcv = cvdao.findAll();
        model.addAttribute("listcv", listcv);
        model.addAttribute("nhanvien", nv);
        model.addAttribute("savenv", "/savenv");
        return "nhanvien/save";
    }

    @GetMapping("/nhanvien/delete/{manv}")
    public String delete(@PathVariable(name="manv") int manv){
        nvdao.deleteById(manv);
        return "redirect:/nhanvien/index";
    }

    @PostMapping("/savenv")
    public String savenv(@Valid @ModelAttribute("nhanvien") NhanVien nhanvien, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "nhanvien/save";
        }else {
            nvdao.save(nhanvien);
            return "redirect:/nhanvien/index";
        }
    }
}
