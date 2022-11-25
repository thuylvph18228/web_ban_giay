package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.edu.DAO.*;
import poly.edu.Entity.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ThanhToanHoaDonController {

    @Autowired
    GiayDAO giayDAO;

    @Autowired
    SizeDAO sizeDAO;

    @Autowired
    NsxDAO nsxdao;

    @Autowired
    ChiTietGiayDAO chiTietGiayDAO;

    @Autowired
    ThanhToanDAO thanhToanDAO;

    @Autowired
    KhachHangDAO khachHangDAO;

    @Autowired
    ThongBaoDAO thongBaoDAO;

    @Autowired
    HoaDonDAO hoaDonDAO;

    @Autowired
    ChiTietHoaDonDAO chiTietHoaDonDAO;

    @Autowired
    HttpSession session;

    @GetMapping("/user/giohang/thanhtoan")
    public String thanhtoan(@ModelAttribute("khachHang") KhachHang khachHang, HttpSession session, Model model) {
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        List<Giay> listg = giayDAO.findAll();
        model.addAttribute("listg", listg);

        List<Size> listsize = sizeDAO.findAll();
        model.addAttribute("listsize", listsize);

        List<ChiTietGiay> listctg = chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);

        List<ThanhToan> listtt = thanhToanDAO.findAll();
        model.addAttribute("listtt", listtt);

        String email = (String) session.getAttribute("email");
        KhachHang kh = khachHangDAO.findByEmail(email);
        model.addAttribute("kh", kh);
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);
        session.setAttribute("myCartToTal", totalPrice(cartItems));
        model.addAttribute("savetthd", "/savetthd");
        return "user/giohang/thanhtoan";
    }

    @GetMapping("/user/hdkh")
    public String hodonkh(HttpSession session, Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        String email = (String) session.getAttribute("email");
        KhachHang kh = khachHangDAO.findByEmail(email);

        Pageable pageable = PageRequest.of(page, size);
        Page<HoaDon> listhd = hoaDonDAO.findByMakh(kh.getMakh(), pageable);
        model.addAttribute("listhd", listhd);
        int totalPages = listhd.getTotalPages()-1;
        int end = listhd.getTotalPages()-1;
        int begin = 0;
        int index = listhd.getNumber();
        int pre = listhd.getNumber()-1;
        int next = listhd.getNumber()+1;
        String baseUrl = "/user/hdkh?page=";

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);

        List<KhachHang> listkh = khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        List<ThanhToan> listtt = thanhToanDAO.findAll();
        model.addAttribute("listtt", listtt);
        List<Giay> listg = giayDAO.findAll();
        model.addAttribute("listg", listg);

        List<ChiTietHoaDon> listcthd = chiTietHoaDonDAO.findAll();
        model.addAttribute("listcthd", listcthd);
        List<ChiTietGiay> listctg = chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);
        List<Size> lists = sizeDAO.findAll();
        model.addAttribute("lists", lists);
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);

        return "user/hoadon/hdkh";
    }

    @PostMapping("/savetthd")
    public String savett(ModelMap mm, HttpSession session,
                         @RequestParam("httt") Integer httt,
                         @RequestParam("tongtien") Integer tongtien
    ) {
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        HoaDon hoadon = new HoaDon();
        String date = String.valueOf(java.time.LocalDate.now());
        String email = (String) session.getAttribute("email");
        KhachHang kh = khachHangDAO.findByEmail(email);

        if (kh != null) {
            if (httt == 1) {
                hoadon.setManv(1);
                hoadon.setMakh(kh.getMakh());
                hoadon.setMahttt(httt);
                hoadon.setNgaytao(date);
                hoadon.setNgaythanhtoan(date);
                hoadon.setTrangthaihd(0);
                hoadon.setTrahang(0);
                hoadon.setDanhgia(0);
                hoadon.setTrangthaidh(0);
                hoadon.setTongtien(tongtien);
                hoaDonDAO.save(hoadon);
                for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()) {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMactg(entry.getValue().getChiTietGiay().getMactg());
                    chiTietHoaDon.setMahd(hoadon.getMahd());
                    chiTietHoaDon.setSoluong(entry.getValue().getSoluong());
                    chiTietHoaDonDAO.save(chiTietHoaDon);

                    int mactg = entry.getValue().getChiTietGiay().getMactg();
                    ChiTietGiay ctg = chiTietGiayDAO.getById(mactg);
                    ctg.setSoluong(ctg.getSoluong() - entry.getValue().getSoluong());
                    chiTietGiayDAO.save(ctg);
                }
            } else {
                hoadon.setManv(1);
                hoadon.setMakh(kh.getMakh());
                hoadon.setMahttt(httt);
                hoadon.setNgaytao(date);
                hoadon.setTrangthaidh(0);
                hoadon.setTrangthaidh(0);
                hoadon.setTrahang(0);
                hoadon.setDanhgia(0);
                hoadon.setTongtien(tongtien);
                hoaDonDAO.save(hoadon);

                for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()) {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMactg(entry.getValue().getChiTietGiay().getMactg());
                    chiTietHoaDon.setMahd(hoadon.getMahd());
                    chiTietHoaDon.setSoluong(entry.getValue().getSoluong());
                    chiTietHoaDonDAO.save(chiTietHoaDon);

                    int mactg = entry.getValue().getChiTietGiay().getMactg();
                    ChiTietGiay ctg = chiTietGiayDAO.getById(mactg);
                    ctg.setSoluong(ctg.getSoluong() - entry.getValue().getSoluong());
                    chiTietGiayDAO.save(ctg);
                }
            }
        } else {
            if (httt == 1) {
                hoadon.setManv(1);
                hoadon.setMakh(kh.getMakh());
                hoadon.setMahttt(httt);
                hoadon.setNgaytao(date);
                hoadon.setNgaythanhtoan(date);
                hoadon.setTrahang(0);
                hoadon.setDanhgia(0);
                hoadon.setTrangthaihd(1);
                hoadon.setTrangthaidh(0);
                hoadon.setTongtien(tongtien);
                hoaDonDAO.save(hoadon);

                for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()) {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMactg(entry.getValue().getChiTietGiay().getMactg());
                    chiTietHoaDon.setMahd(hoadon.getMahd());
                    chiTietHoaDon.setSoluong(entry.getValue().getSoluong());
                    chiTietHoaDonDAO.save(chiTietHoaDon);

                    int mactg = entry.getValue().getChiTietGiay().getMactg();
                    ChiTietGiay ctg = chiTietGiayDAO.getById(mactg);
                    ctg.setSoluong(ctg.getSoluong() - entry.getValue().getSoluong());
                    chiTietGiayDAO.save(ctg);
                }
            } else {
                hoadon.setManv(1);
                hoadon.setMakh(kh.getMakh());
                hoadon.setMahttt(httt);
                hoadon.setNgaytao(date);
                hoadon.setTrangthaihd(0);
                hoadon.setTrahang(0);
                hoadon.setDanhgia(0);
                hoadon.setTrangthaidh(0);
                hoadon.setTongtien(tongtien);
                hoaDonDAO.save(hoadon);

                for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()) {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMactg(entry.getValue().getChiTietGiay().getMactg());
                    chiTietHoaDon.setMahd(hoadon.getMahd());
                    chiTietHoaDon.setSoluong(entry.getValue().getSoluong());
                    chiTietHoaDonDAO.save(chiTietHoaDon);

                    int mactg = entry.getValue().getChiTietGiay().getMactg();
                    ChiTietGiay ctg = chiTietGiayDAO.getById(mactg);
                    ctg.setSoluong(ctg.getSoluong() - entry.getValue().getSoluong());
                    chiTietGiayDAO.save(ctg);
                }
            }
        }
        ThongBao thongBao = new ThongBao();
        thongBao.setMahd(hoadon.getMahd());
        thongBao.setMakh(hoadon.getMakh());
        KhachHang khachHang = khachHangDAO.getById(hoadon.getMakh());
        thongBao.setTrangthai(0);
        LocalDateTime myDateObj = LocalDateTime.now();
        thongBao.setNgaytao(String.valueOf(myDateObj));
        thongBao.setMota("Khách hàng "+khachHang.getTen()+ " đã đặt hóa đơn ");
        thongBaoDAO.save(thongBao);

        cartItems.clear();
        session.setAttribute("myCartToTal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "redirect:/giay/product";
    }

    public int totalPrice(HashMap<Integer, Cart> cartItems) {
        int count = 0;
        for (Map.Entry<Integer, Cart> list : cartItems.entrySet()) {
            int mag = list.getValue().getChiTietGiay().getMag();
            Giay giay = giayDAO.getById(mag);
            count += giay.getGia() * list.getValue().getSoluong();
        }
        return count;
    }

}
