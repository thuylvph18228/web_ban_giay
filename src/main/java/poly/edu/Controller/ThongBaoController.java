package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import poly.edu.DAO.ThongBaoDAO;
import poly.edu.Entity.Nsx;
import poly.edu.Entity.ThongBao;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class ThongBaoController {
    @Autowired
    HttpSession session;
    @Autowired
    ThongBaoDAO thongBaoDAO;
    @GetMapping("/admin/thongbao/index")
    public String listtb(Model model) {
        List<ThongBao> listtb = thongBaoDAO.findByThongBaoChuaXem();
      model.addAttribute("listtb", listtb);
        session.setAttribute("sizeth",listtb.size());
        return ("admin/nsx/index");
    }
}
