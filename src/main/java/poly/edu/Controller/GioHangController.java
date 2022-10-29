package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.*;
import poly.edu.Entity.*;
import poly.edu.Entity.Respon.TToan;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class GioHangController {

    HttpSession httpSession;
    @Autowired
    public GiayDAO giayDAO;

    @Autowired
    public SizeDAO sizeDAO;

    @Autowired
    public KhachHangDAO khachHangDAO;

    @Autowired
    public GioHangDAO gioHangDAO;

    @Autowired
    ThanhToanDAO thanhToanDAO;

    @Autowired
    HoaDonDAO hoaDonDAO;

    @Autowired
    ChiTietGiayDAO chiTietGiayDAO;

    @Autowired
    ChiTietHoaDonDAO chiTietHoaDonDAO;

    @GetMapping("/giohang/index")
    public String listkh(Model model) {
        List<GioHang> listgh = gioHangDAO.findAll();
        model.addAttribute("listgh", listgh);
        List<ChiTietGiay> listctg = chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);
        return ("giohang/index");
    }

    @GetMapping("/giohang/create")
    public String create(@ModelAttribute("giohang") GioHang gioHang, Model model) {
        List<ChiTietGiay> listctg = chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);
        model.addAttribute("savegh", "/savegh");
        return "giohang/save";
    }

    @GetMapping("/giohang/edit/{magh}")
    public String edit(@PathVariable(name = "magh") int magh, Model model) {
        model.addAttribute("magh", magh);
        GioHang gh = gioHangDAO.getById(magh);
        List<ChiTietGiay> listctg = chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);
        model.addAttribute("giohang", gh);

        return "giohang/save";
    }


    @GetMapping("/giohang/delete/{magh}")
    public String delete(@PathVariable(name = "magh") int magh) {
        gioHangDAO.deleteById(magh);
        return "redirect:/giohang/index";
    }

    @PostMapping("/savegh")
    public String save(@Valid @ModelAttribute("giohang") GioHang gioHang, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ChiTietGiay> listctg = chiTietGiayDAO.findAll();
            model.addAttribute("listctg", listctg);
            return "giohang/save";
        }
        gioHangDAO.save(gioHang);
        return "redirect:/giohang/index";
    }


    @GetMapping("/giohang/thanhtoan")
    public String thanhtoan(@ModelAttribute("hoaDon") HoaDon hoaDon, HttpSession session, Model model) {
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

//    @GetMapping("/hoadonfindsdt")
//    public String findBySdt( Model model,@RequestParam("sdt") int sdt){
//        List<HoaDon> lishd = hoaDonDAO.findBySdt(sdt);
//        model.addAttribute("listhd", lishd);
//        return "hoadon/hdkh";
//    }

    @PostMapping("/savetthd")
    public String savett(ModelMap mm, HttpSession session, @Valid @ModelAttribute("hoaDon") HoaDon hoaDon,
                         BindingResult bindingResult, Model model,
                         @RequestParam("soluong") Integer soluong,
                         @RequestParam("tennguoinhan") String ten,
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
            List<HoaDon> listhd = hoaDonDAO.findAll();
            model.addAttribute("listhd", listhd);
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
            for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()) {
                HoaDon hoadon = new HoaDon();
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                String date = String.valueOf(java.time.LocalDate.now());

                if (httt == 1) {
                    hoadon.setManv(1);
                    hoadon.setMakh(1);
                    hoadon.setMahttt(httt);
                    hoadon.setNgaytao(date);
                    hoadon.setNgaythanhtoan(date);
                    hoadon.setDiachi(diachi);
                    hoadon.setSdt(sdt);
                    hoadon.setTennguoinhan(ten);
                    hoaDonDAO.save(hoadon);

                    chiTietHoaDon.setMactg(entry.getValue().getChiTietGiay().getMactg());
                    chiTietHoaDon.setMahd(hoadon.getMahd());
                    chiTietHoaDon.setSoluong(soluong);
                    chiTietHoaDon.setTongtien(tongtien);
                    chiTietHoaDonDAO.save(chiTietHoaDon);
                } else {
                    hoadon.setManv(1);
                    hoadon.setMakh(1);
                    hoadon.setMahttt(httt);

                    hoadon.setNgaytao(date);
                    hoadon.setDiachi(diachi);
                    hoadon.setSdt(sdt);
                    hoadon.setTennguoinhan(ten);
                    hoaDonDAO.save(hoadon);

                    chiTietHoaDon.setMactg(entry.getValue().getChiTietGiay().getMactg());
                    chiTietHoaDon.setMahd(hoadon.getMahd());
                    chiTietHoaDon.setSoluong(soluong);
                    chiTietHoaDon.setTongtien(tongtien);
                    chiTietHoaDonDAO.save(chiTietHoaDon);
                }

            }
            List<HoaDon> listhd = hoaDonDAO.findBySdt(sdt);
            model.addAttribute("listhd", listhd);
            listhd = hoaDonDAO.findByMa(hoaDon.getMahd());
            model.addAttribute("listhdkh", listhd);
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


