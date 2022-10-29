package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    ChiTietGiayDAO chiTietGiayDAO;

    @Autowired
    ThanhToanDAO thanhToanDAO;

    @Autowired
    KhachHangDAO khachHangDAO;

    @Autowired
    HoaDonDAO hoaDonDAO;

    @Autowired
    ChiTietHoaDonDAO chiTietHoaDonDAO;


    @GetMapping("/giohang/thanhtoan")
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

        session.setAttribute("myCartToTal", totalPrice(cartItems));
        model.addAttribute("savetthd", "/savetthd");
        return "giohang/thanhtoan";
    }

    @PostMapping("/savetthd")
    public String savett(ModelMap mm, HttpSession session, @Valid @ModelAttribute("khachHang") KhachHang khachHang,
                         BindingResult bindingResult, Model model,
                         @RequestParam("soluong") Integer soluong,
                         @RequestParam("ten") String ten,
                         @RequestParam("sdt") String sdt,
                         @RequestParam("diachi") String diachi,
                         @RequestParam("httt") Integer httt,
                         @RequestParam("tongtien") Integer tongtien
    ) {
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        if (bindingResult.hasErrors()) {
            List<Giay> listg = giayDAO.findAll();
            model.addAttribute("listg", listg);
            List<Size> listsize = sizeDAO.findAll();
            model.addAttribute("listsize", listsize);
            List<ThanhToan> listtt = thanhToanDAO.findAll();
            model.addAttribute("listtt", listtt);
            List<KhachHang> listkh = khachHangDAO.findAll();
            model.addAttribute("listkh", listkh);
            return "giohang/thanhtoan";
        } else {
            HoaDon hoadon = new HoaDon();
            String date = String.valueOf(java.time.LocalDate.now());
            KhachHang kh = khachHangDAO.findBySdt(sdt);

            if (kh != null) {
                if (httt == 1) {
                    hoadon.setManv(1);
                    hoadon.setMakh(kh.getMakh());
                    hoadon.setMahttt(httt);
                    hoadon.setNgaytao(date);
                    hoadon.setNgaythanhtoan(date);
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
                    }
                } else {
                    hoadon.setManv(1);
                    hoadon.setMakh(kh.getMakh());
                    hoadon.setMahttt(httt);
                    hoadon.setNgaytao(date);
                    hoadon.setTrangthaihd(0);
                    hoadon.setTrangthaidh(0);
                    hoadon.setTongtien(tongtien);
                    hoaDonDAO.save(hoadon);

                    for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()) {
                        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                        chiTietHoaDon.setMactg(entry.getValue().getChiTietGiay().getMactg());
                        chiTietHoaDon.setMahd(hoadon.getMahd());
                        chiTietHoaDon.setSoluong(entry.getValue().getSoluong());
                        chiTietHoaDonDAO.save(chiTietHoaDon);
                    }
                }
            } else {
                khachHang.setTen(ten);
                khachHang.setDiachi(diachi);
                khachHang.setSdt(sdt);
                khachHangDAO.save(khachHang);
                KhachHang khsdt = khachHangDAO.findBySdt(sdt);
                if (httt == 1) {
                    hoadon.setManv(1);
                    hoadon.setMakh(khsdt.getMakh());
                    hoadon.setMahttt(httt);
                    hoadon.setNgaytao(date);
                    hoadon.setNgaythanhtoan(date);
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
                    }
                } else {
                    hoadon.setManv(1);
                    hoadon.setMakh(khsdt.getMakh());
                    hoadon.setMahttt(httt);
                    hoadon.setNgaytao(date);
                    hoadon.setTrangthaihd(0);
                    hoadon.setTrangthaidh(0);
                    hoadon.setTongtien(tongtien);
                    hoaDonDAO.save(hoadon);

                    for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()) {
                        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                        chiTietHoaDon.setMactg(entry.getValue().getChiTietGiay().getMactg());
                        chiTietHoaDon.setMahd(hoadon.getMahd());
                        chiTietHoaDon.setSoluong(entry.getValue().getSoluong());
                        chiTietHoaDonDAO.save(chiTietHoaDon);
                    }
                }
            }

            KhachHang khbysdt = khachHangDAO.findBySdt(sdt);
            List<HoaDon> listhd = hoaDonDAO.findByMakh(khbysdt.getMakh());
            model.addAttribute("listhd", listhd);
            listhd = hoaDonDAO.findMaxHDByMa(hoadon.getMahd());
            model.addAttribute("listhdkh", listhd);

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


            cartItems.clear();
            return "hoadon/hdkh";
        }

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
