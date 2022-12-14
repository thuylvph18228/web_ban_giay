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
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HoaDonController {

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
    ThongBaoDAO thongBaoDAO;
    @Autowired
    HttpSession httpSession;
    @Autowired
    private ChiTietGiayDAO chiTietGiayDAO;
    @Autowired
    private SizeDAO sizeDAO;
    @GetMapping("/admin/hoadon/index")
    public String listhd(Model model) {

        List<ChiTietHoaDon> listcthd = chiTietHoaDonDAO.findAll();
        model.addAttribute("listcthd", listcthd);

        List<HoaDon> listhd = hoaDonDAO.findAll();
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

        return ("admin/hoadon/index");
    }

    @GetMapping("/admin/hoadon/findtrangthaicxn")
    public String findtrangthaicxn( Model model,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "10") int size){

        Pageable pageable = PageRequest.of(page, size);


        Page<HoaDon> listhd = hoaDonDAO.findByTrangthaicxn(pageable);
        int firstPage = 0;
        int totalPages = listhd.getTotalPages()-1;
        int end = listhd.getTotalPages()-1;
        int begin = 0;
        int index = listhd.getNumber();
        int pre = listhd.getNumber()-1;
        int next = listhd.getNumber()+1;
        String baseUrl = "/admin/hoadon/findtrangthaicxn?page=";
        List<HoaDon> listhd0 = hoaDonDAO.findByTrangthaicxnSize();
        httpSession.setAttribute("sizehdcxn",listhd0.size());
        List<HoaDon> listhd1 = hoaDonDAO.findByTrangthaidvc();
        httpSession.setAttribute("sizehddvc",listhd1.size());
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
        List<ThongBao> listtb = thongBaoDAO.findByThongBaoChuaXem();
        httpSession.setAttribute("listtb", listtb);
        httpSession.setAttribute("sizeth", listtb.size());
        return "admin/hoadon/findhdkhach";
    }

    @GetMapping("/admin/hoadon/findtrangthaicxn/{mahd}")
    public String findtrangthaicxnbymahd(@PathVariable("mahd") int mahd, Model model) {
        List<HoaDon> listhd = hoaDonDAO.findByTrangthaicxnbymahd(mahd);

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
        List<ThongBao> listtb = thongBaoDAO.findByThongBaoChuaXem();
        model.addAttribute("listtb", listtb);
        httpSession.setAttribute("sizeth",listtb.size());
        return "admin/hoadon/findhdkhach";
    }

    @GetMapping("/admin/hoadon/findtrangthaidvc")
    public String findtrangthaidvc( Model model,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "10") int size){
        List<HoaDon> listh = hoaDonDAO.findByTrangthaidvc();
        httpSession.setAttribute("sizehddvc",listh.size());
        Pageable pageable = PageRequest.of(page, size);
        Page<HoaDon> listhd = hoaDonDAO.findByTrangthaidvcPage(pageable);
        int firstPage = 0;
        int totalPages = listhd.getTotalPages()-1;
        int end = listhd.getTotalPages()-1;
        int begin = 0;
        int index = listhd.getNumber();
        int pre = listhd.getNumber()-1;
        int next = listhd.getNumber()+1;
        String baseUrl = "/admin/hoadon/findtrangthaidvc?page=";

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

        return "admin/hoadon/findhdkhach";
    }

    @GetMapping("/cart/updatetrangthaidh/{mahd}")
    public String updatetrangthaihd(@PathVariable("mahd") int mahd, Model model) {
        HoaDon hoaDon = hoaDonDAO.getById(mahd);
        if (hoaDon.getTrangthaidh() == 0) {
            hoaDon.setTrangthaidh(hoaDon.getTrangthaidh() + 1);
            String date = String.valueOf(java.time.LocalDate.now());
            hoaDon.setNgayship(date);
            ThongBao thongBao = thongBaoDAO.findThongBaoByMahd(hoaDon.getMahd());
            thongBao.setMatb(thongBao.getMatb());
            thongBao.setTrangthai(1);

            LocalDateTime myDateObj = LocalDateTime.now();
            thongBao.setNgayxem(String.valueOf(myDateObj));
            thongBaoDAO.save(thongBao);
            List<ThongBao> listtb = thongBaoDAO.findByThongBaoChuaXem();
            httpSession.setAttribute("sizeth", listtb.size());
            model.addAttribute("listb", listtb);
            hoaDonDAO.save(hoaDon);
            return "redirect:/admin/hoadon/findtrangthaicxn";
        } else if (hoaDon.getTrangthaidh() == 1 && hoaDon.getTrangthaihd() == 0) {
            hoaDon.setTrangthaidh(hoaDon.getTrangthaidh() + 1);
            String date = String.valueOf(java.time.LocalDate.now());
            hoaDon.setNgaynhan(date);
            hoaDon.setNgaythanhtoan(date);
            hoaDon.setTrangthaihd(1);
            hoaDonDAO.save(hoaDon);
            ThongBao thongBao = thongBaoDAO.findThongBaoByMahd(hoaDon.getMahd());
            thongBao.setMatb(thongBao.getMatb());
            thongBao.setTrangthai(1);
            thongBaoDAO.save(thongBao);
            List<ThongBao> listtb = thongBaoDAO.findByThongBaoChuaXem();
            httpSession.setAttribute("sizeth",listtb.size());
            httpSession.setAttribute("listb",listtb);

            return "redirect:/admin/hoadon/findtrangthaidvc";
        } else if (hoaDon.getTrangthaidh() == 1 && hoaDon.getTrangthaihd() == 1) {
            hoaDon.setTrangthaidh(hoaDon.getTrangthaidh() + 1);
            String date = String.valueOf(java.time.LocalDate.now());
            hoaDon.setNgaynhan(date);
            hoaDonDAO.save(hoaDon);
            return "redirect:/admin/hoadon/findtrangthaidvc";
        }

        return "redirect:/admin/hoadon/findtrangthai";
    }


    @GetMapping("/admin/hoadon/create")
    public String create(@ModelAttribute("hoadon") HoaDon hoaDon, Model model) {

        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);

        List<NhanVien> listnv = nhanVienDAO.findAll();
        model.addAttribute("listnv", listnv);

        List<ThanhToan> listtt = thanhToanDAO.findAll();
        model.addAttribute("listtt", listtt);

        model.addAttribute("savehd", "/savehd");

        return "admin/hoadon/save";
    }

    @GetMapping("/admin/hoadon/edit/{mahd}")
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
        return "admin/hoadon/save";
    }

    @GetMapping("/admin/hoadon/delete/{mahd}")
    public String delete(@PathVariable(name = "mahd") int mahd) {
        hoaDonDAO.deleteById(mahd);
        return "redirect:/admin/hoadon/index";
    }

    @PostMapping("/savehd")
    public String save(@Valid @ModelAttribute("hoadon") HoaDon hoaDon, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<KhachHang> listkh = khachHangDAO.findAll();
            model.addAttribute("listkh", listkh);

            List<NhanVien> listnv = nhanVienDAO.findAll();
            model.addAttribute("listnv", listnv);

            List<ThanhToan> listtt = thanhToanDAO.findAll();
            model.addAttribute("listtt", listtt);
            return "admin/hoadon/save";
        }
        hoaDonDAO.save(hoaDon);
        return "redirect:/admin/hoadon/index";
    }

    @GetMapping("/user/cart/processing")
    public String processing(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "10") int size) {

        String email = (String) httpSession.getAttribute("email");
        KhachHang kh = khachHangDAO.findByEmail(email);

     //   List<HoaDon> hoaDonList = hoaDonDAO.findByMakhprocessing();
        List<KhachHang> khachHangList = khachHangDAO.findAll();
        model.addAttribute("khachHangList", khachHangList);

        Pageable pageable = PageRequest.of(page, size);
        Page<HoaDon> listhd = hoaDonDAO.findByMakhprocessing(kh.getMakh(), pageable);
        int firstPage = 0;
        int totalPages = listhd.getTotalPages()-1;
        int end = listhd.getTotalPages()-1;
        int begin = 0;
        int index = listhd.getNumber();
        int pre = listhd.getNumber()-1;
        int next = listhd.getNumber()+1;
        String baseUrl = "/user/cart/processing?page=";

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

        model.addAttribute("hoaDonList", listhd);
        return "user/hoadon/tinhtrangdh";
    }

    @GetMapping("/user/cart/shipping")
    public String shipping(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "10") int size) {

        String email = (String) httpSession.getAttribute("email");
        KhachHang kh = khachHangDAO.findByEmail(email);
        //List<HoaDon> hoaDonList = (List<HoaDon>) hoaDonDAO.findByMakhshipping(kh.getMakh());
       // List<KhachHang> khachHangList = khachHangDAO.findAll();

        Pageable pageable = PageRequest.of(page, size);
        Page<HoaDon> listhd = hoaDonDAO.findByMakhshipping(kh.getMakh(), pageable);
        model.addAttribute("listhd", listhd);
        int firstPage = 0;
        int totalPages = listhd.getTotalPages()-1;
        int end = listhd.getTotalPages()-1;
        int begin = 0;
        int index = listhd.getNumber();
        int pre = listhd.getNumber()-1;
        int next = listhd.getNumber()+1;
        String baseUrl = "/user/cart/shipping?page=";

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

//        List<HoaDon> hoaDonList=hoaDonDAO.findByMakhshipping(kh.getMakh());
        List<KhachHang> khachHangList =khachHangDAO.findAll();
        model.addAttribute("khachHangList", khachHangList);
        model.addAttribute("hoaDonList", listhd);
        return "user/hoadon/tinhtrangdh";
    }
    @GetMapping("/user/cart/delivered")
    public String delivered(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "10") int size) {

        String email = (String) httpSession.getAttribute("email");
        KhachHang kh = khachHangDAO.findByEmail(email);
//        List<HoaDon> hoaDonList = hoaDonDAO.findByMakhdelivered(kh.getMakh(),Pageable.unpaged());
      // List<KhachHang> khachHangList = khachHangDAO.findAll();
      //  System.out.println(hoaDonList);

        Pageable pageable = PageRequest.of(page, size);
        Page<HoaDon> listhd = hoaDonDAO.findByMakhdelivered(kh.getMakh(), pageable);
        int firstPage = 0;
        int totalPages = listhd.getTotalPages()-1;
        int end = listhd.getTotalPages()-1;
        int begin = 0;
        int index = listhd.getNumber();
        int pre = listhd.getNumber()-1;
        int next = listhd.getNumber()+1;
        String baseUrl = "/user/cart/delivered?page=";

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

        List<KhachHang> khachHangList =khachHangDAO.findAll();
        model.addAttribute("khachHangList", khachHangList);
        model.addAttribute("hoaDonList", listhd);
        return "user/hoadon/tinhtrangdh";
    }

}
