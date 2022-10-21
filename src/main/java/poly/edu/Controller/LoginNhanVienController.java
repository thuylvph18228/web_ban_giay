package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.edu.DAO.NhanVienDAO;
import poly.edu.Entity.KhachHang;
import poly.edu.Entity.NhanVien;

import javax.servlet.http.HttpSession;

@Controller
public class LoginNhanVienController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    NhanVienDAO nhanVienDAO;

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/loginnv")
    public String loginnv(@ModelAttribute("nhanvien")NhanVien nhanVien ,
                        @ModelAttribute("khachhang") KhachHang khachHang , Model model) {

        model.addAttribute("comfirm_loginnv", "/comfirm_loginnv");
        return "loginnv";
    }
    @PostMapping("/comfirm_loginnv")
    public String comfirm_login( @RequestParam("email") String email, @RequestParam("matkhau") String pw,
                                 @ModelAttribute("nhanvien") NhanVien nhanVien) {
        System.out.println(email);
        System.out.println(pw);
        nhanVien = nhanVienDAO.findByEmailEquals(email);
        System.out.println(nhanVien);
        if (nhanVien == null) {
           // session.setAttribute("error", "Sai Tai Khoan Hoac Mat Khau");
            System.out.println("Không tìm đc tài  khoản");
            return "redirect:/loginnv";
        } else {
            //boolean check = EncryptUtil.check(pw, acc.getPassword());
            if (email.equals(nhanVien.getEmail())&& pw.equals( nhanVien.getMatkhau())) {
//                int admin  =acc.getAdmin();
//                session.setAttribute("admin",admin);
//                session.setAttribute("user", user);
//                session.setAttribute("message", "Đăng nhập thành công")
//                ;
//                return "redirect:/admin/products/trangchu";
                return "redirect:/giay/index";

            } else {
                return "redirect:/loginnv";
            }
        }

    }
}
