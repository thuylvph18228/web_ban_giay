package poly.edu.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.edu.DAO.ChucVuDAO;
import poly.edu.DAO.KhachHangDAO;
import poly.edu.DAO.NhanVienDAO;
import poly.edu.Entity.Cart;

import java.util.HashMap;

@Controller


public class SecurityController {



    @Autowired
    KhachHangDAO khachHangDAO;

    @Autowired
    NhanVienDAO nhanVienDAO;

    @Autowired
    ChucVuDAO chucVuDAO;

    @Autowired
    HttpSession session;

    String roles;
    @RequestMapping("/security/login/form")
    public String loginForm(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập!");
        return "security/login";
    }

    @RequestMapping("/security/login/formadmin")
    public String loginFormAdmin(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập!");
        return "security/loginnv";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model) {
      String  roles= (String) session.getAttribute("roles");
        if (roles.equals("Customer")){
            model.addAttribute("message", "Đăng nhập thành công!");
            return "redirect:/giay/product";
        }
        if (roles.equals("Employee")){
            model.addAttribute("message", "Đăng nhập thành công!");
            return "redirect:/admin/hoadon/findtrangthaicxn";
        }if (roles.equals("Admin")){
            model.addAttribute("message", "Đăng nhập thành công!");
            return "redirect:/rest/admin/thongkengay";
        }
        return "security/login";

    }

    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập!");
        return "security/login";
    }

    @RequestMapping("/security/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message", "Không có quyền truy xuất!");
        return "security/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất!");
        return "security/login";
    }

    @CrossOrigin("*")
    @ResponseBody
    @RequestMapping("/rest/security/authentication")
    public Object getAuthentication(HttpSession session) {
        return session.getAttribute("authentication");
    }
}
