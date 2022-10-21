package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.Entity.KhachHang;

import javax.servlet.http.HttpSession;

@Controller
public class LoginKhachhangController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    KhachHangDAO khachHangDAO;
    @GetMapping("/loginkh")
    public String login(@ModelAttribute("khachhang") KhachHang khachHang , Model model) {

        model.addAttribute("comfirm_loginkh", "/comfirm_loginkh");
        return "loginkhachhang";
    }
    @PostMapping("/comfirm_loginkh")
    public String comfirm_login(@RequestParam("email") String email, @RequestParam("matkhau") String pw,
                                @ModelAttribute("khachhang") KhachHang khachHang) {
        System.out.println(email);
        System.out.println(pw);
        khachHang = khachHangDAO.findByEmailEquals(email);
        System.out.println(khachHang);
        if (khachHang == null) {
            // session.setAttribute("error", "Sai Tai Khoan Hoac Mat Khau");
            System.out.println("Không tìm đc tài  khoản");
            return "redirect:/loginkh";
        } else {
            //boolean check = EncryptUtil.check(pw, acc.getPassword());
            if (email.equals(khachHang.getEmail())&& pw.equals( khachHang.getMatkhau())) {
//                int admin  =acc.getAdmin();
//                session.setAttribute("admin",admin);
//                session.setAttribute("user", user);
//                session.setAttribute("message", "Đăng nhập thành công")
//                ;
//                return "redirect:/admin/products/trangchu";
                return "redirect:/giay/index";

            } else {
                return "redirect:/loginkh";
            }
        }

    }
}
