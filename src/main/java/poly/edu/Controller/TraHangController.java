package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.*;
import poly.edu.Entity.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TraHangController {

    @Autowired
    public HoaDonDAO hoaDonDAO;

    @Autowired
    public GioHangDAO gioHangDAO;

    @Autowired
    public ChiTietHoaDonDAO chiTietHoaDonDAO;

    @Autowired
    public NhanVienDAO nhanVienDAO;

    @Autowired
    public KhachHangDAO khachHangDAO;

    @Autowired
    private ThanhToanDAO thanhToanDAO;

    @Autowired
    private GiayDAO giayDAO;

    @Autowired
    private LyDoDAO lyDoDAO;

    @Autowired
    private HttpSession session;

    @Autowired
    private TraHangDAO traHangDAO;

    @Autowired
    HttpSession httpSession;
    @Autowired
    private ChiTietGiayDAO chiTietGiayDAO;
    @Autowired
    private SizeDAO sizeDAO;

    @GetMapping("/user/trahang/listtrahang")
    public String listtrahang(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        String email = (String) session.getAttribute("email");
        KhachHang kh = khachHangDAO.findByEmail(email);
        Page<HoaDon> listhd = hoaDonDAO.findHDsmaller7(kh.getMakh(),pageable);
        model.addAttribute("listhd", listhd);


        List<ChiTietHoaDon> listcthd = chiTietHoaDonDAO.findAll();
        model.addAttribute("listcthd", listcthd);
        int firstPage = 0;
        int totalPages = listhd.getTotalPages()-1;
        int end = listhd.getTotalPages()-1;
        int begin = 0;
        int index = listhd.getNumber();
        int pre = listhd.getNumber()-1;
        int next = listhd.getNumber()+1;
        String baseUrl = "/user/trahang/listtrahang?page=";

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

        model.addAttribute("listhd", listhd);
        List<NhanVien> listnv = nhanVienDAO.findAll();
        model.addAttribute("listnv", listnv);
        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        List<ThanhToan> listtt = thanhToanDAO.findAll();
        model.addAttribute("listtt", listtt);
        List<Giay> listg = giayDAO.findAll();
        model.addAttribute("listg", listg);
        List<ChiTietGiay> listctg = chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);
        List<Size> lists = sizeDAO.findAll();
        model.addAttribute("lists", lists);

        return ("user/trahang/hoantra");
    }

    @GetMapping("/user/trahang/trahang/{mahd}")
    public String trahang(@PathVariable("mahd") int mahd, @ModelAttribute("trahang") TraHang traHang, Model model) {

        List<LyDoTraHang> listld = lyDoDAO.findAll();
        HoaDon hoaDon = hoaDonDAO.getById(mahd);
        model.addAttribute("listld", listld);
        model.addAttribute("saveth", "/saveth");
        model.addAttribute("hoaDon", hoaDon);

        return ("user/trahang/save");
    }

    @GetMapping("/user/trahang/lichsutrahang")
    public String lichsutrahang(@ModelAttribute("trahang") TraHang traHang, Model model) {
        String email = (String) session.getAttribute("email");
        KhachHang kh = khachHangDAO.findByEmail(email);
        List<TraHang> listth = traHangDAO.lichsuTraHang(kh.getMakh());
        List<LyDoTraHang> listld = lyDoDAO.findAll();
        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        model.addAttribute("listld", listld);
        model.addAttribute("saveth", "/saveth");
        model.addAttribute("listth", listth);

        return ("user/trahang/index");
    }

    @PostMapping("/saveth")
    public String saves(@Valid @ModelAttribute("trahang") TraHang traHang, @RequestParam("mahd") int mahd, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/trahang/save";
        }
        HoaDon hoaDon = hoaDonDAO.getById(mahd);
        String email = (String) session.getAttribute("email");
        KhachHang kh = khachHangDAO.findByEmail(email);
        traHang.setMakh(kh.getMakh());
        traHang.setTrangthai(0);
        traHang.setManv(1);
        hoaDon.setTrahang(1);
        traHang.setMahd(hoaDon.getMahd());
        hoaDonDAO.save(hoaDon);
        traHangDAO.save(traHang);

        return "redirect:/user/trahang/listtrahang";
    }

    @GetMapping("/admin/trahang/canxuly")
    public String xulytrahang(@ModelAttribute("trahang") TraHang traHang, Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TraHang> listth = traHangDAO.trahangcanxuly(pageable);
        int firstPage = 0;
        int totalPages = listth.getTotalPages()-1;
        int end = listth.getTotalPages()-1;
        int begin = 0;
        int index = listth.getNumber();
        int pre = listth.getNumber()-1;
        int next = listth.getNumber()+1;
        String baseUrl = "/admin/trahang/canxuly?page=";

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

        List<LyDoTraHang> listld = lyDoDAO.findAll();
        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        model.addAttribute("listld", listld);
        model.addAttribute("saveth", "/saveth");
        model.addAttribute("listth", listth);

        return ("admin/trahang/index");
    }

    @GetMapping("/admin/trahang/daxuly")
    public String daxulytrahang(@ModelAttribute("trahang") TraHang traHang, Model model,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TraHang> listth = traHangDAO.trahangdaxuly(pageable);
        int firstPage = 0;
        int totalPages = listth.getTotalPages()-1;
        int end = listth.getTotalPages()-1;
        int begin = 0;
        int index = listth.getNumber();
        int pre = listth.getNumber()-1;
        int next = listth.getNumber()+1;
        String baseUrl = "/admin/trahang/canxuly?page=";

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

        List<LyDoTraHang> listld = lyDoDAO.findAll();
        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        model.addAttribute("listld", listld);
        model.addAttribute("saveth", "/saveth");
        model.addAttribute("listth", listth);
        return ("admin/trahang/daxuly");
    }

    @GetMapping("/admin/trahang/updatecn/{math}")
    public String updatecn(@PathVariable(name = "math") int math) {
        TraHang traHang = traHangDAO.getById(math);
        List<ChiTietHoaDon> chiTietHoaDonList = chiTietHoaDonDAO.findByMahdtrahang(traHang.getMahd());
        traHang.setTrangthai(1);
        traHang.setXacnhan(1);

        for (ChiTietHoaDon x: chiTietHoaDonList) {
            ChiTietGiay chiTietGiay =chiTietGiayDAO.getById(x.getMactg());
            System.out.println(x);
            chiTietGiay.setSoluong(chiTietGiay.getSoluong()+x.getSoluong());
            chiTietGiayDAO.save(chiTietGiay);
        }
        traHangDAO.save(traHang);
        return "redirect:/admin/trahang/daxuly";
    }

    @GetMapping("/admin/trahang/updatetc/{math}")
    public String updatetc(@PathVariable(name = "math") int math) {
        TraHang traHang = traHangDAO.getById(math);

        traHang.setTrangthai(1);
        traHang.setXacnhan(2);
        traHangDAO.save(traHang);
        return "redirect:/admin/trahang/daxuly";
    }


}
