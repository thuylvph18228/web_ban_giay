package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import poly.edu.DAO.HoaDonDAO;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.DAO.NhanVienDAO;
import poly.edu.DAO.ThanhToanDAO;
import poly.edu.Entity.HoaDon;
import poly.edu.Entity.KhachHang;
import poly.edu.Entity.NhanVien;
import poly.edu.Entity.ThanhToan;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HoaDonController {

    @Autowired
    private KhachHangDAO khachHangDAO;

    @Autowired
    private NhanVienDAO nhanVienDAO;

    @Autowired
    private ThanhToanDAO thanhToanDAO;

    @Autowired
    private HoaDonDAO hoaDonDAO;
    @GetMapping("/hoadon/index")
    public String listhd(Model model) {
        List<HoaDon> listhd = hoaDonDAO.findAll();
        model.addAttribute("listhd", listhd);

        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);

        List<NhanVien> listnv = nhanVienDAO.findAll();
        model.addAttribute("listnv", listnv);

        List<ThanhToan> listtt = thanhToanDAO.findAll();
        model.addAttribute("listtt", listtt);
        return ("hoadon/index");
    }
    @GetMapping("/hoadon/create")
    public String create(@ModelAttribute("hoadon") HoaDon hoaDon, Model model) {

        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);

        List<NhanVien> listnv = nhanVienDAO.findAll();
        model.addAttribute("listnv", listnv);

        List<ThanhToan> listtt = thanhToanDAO.findAll();
        model.addAttribute("listtt", listtt);

        model.addAttribute("savehd", "/savehd");

        return "hoadon/save";
    }

    @GetMapping("/hoadon/edit/{mahd}")
    public String edit(@PathVariable(name = "mahd") int mahd, Model model) {
        model.addAttribute("mahd", mahd);
        HoaDon hoaDon = hoaDonDAO.getById(mahd);
        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);

        List<NhanVien> listnv = nhanVienDAO.findAll();
        model.addAttribute("listnv", listnv);

        List<ThanhToan> listtt = thanhToanDAO.findAll();

        model.addAttribute("hoadon", hoaDon);
        model.addAttribute("listtt", listtt);
        model.addAttribute("savehd", "/savehd");
        return "hoadon/save";
    }

    @GetMapping("/hoadon/delete/{mahd}")
    public String delete(@PathVariable(name = "mahd") int mahd) {
        hoaDonDAO.deleteById(mahd);
        return "redirect:/hoadon/index";
    }

    @PostMapping("/savehd")
    public String save(@Valid @ModelAttribute("hoadon")   HoaDon hoaDon, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<KhachHang> listkh = khachHangDAO.findAll();
            model.addAttribute("listkh", listkh);

            List<NhanVien> listnv = nhanVienDAO.findAll();
            model.addAttribute("listnv", listnv);

            List<ThanhToan> listtt = thanhToanDAO.findAll();
            model.addAttribute("listtt", listtt);
            return "hoadon/save";
        }
        hoaDonDAO.save(hoaDon);
        return "redirect:/hoadon/index";
    }

}
