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
    public String thanhtoan(HttpSession session, Model model) {
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");

        List<Giay> listg =giayDAO.findAll();
        model.addAttribute("listg", listg);

        List<Size> listsize =sizeDAO.findAll();
        model.addAttribute("listsize", listsize);

        List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);

        List<ThanhToan> listtt =thanhToanDAO.findAll();
        model.addAttribute("listtt", listtt);

        session.setAttribute("myCartToTal",totalPrice(cartItems));
        model.addAttribute("savetthd", "/savetthd");
        return "giohang/thanhtoan";
    }

    @PostMapping("/savetthd")
    public String savett(ModelMap mm, HttpSession session, @Valid @ModelAttribute("giohang")   GioHang gioHang,
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
            List<Giay> listg =giayDAO.findAll();
            model.addAttribute("listg", listg);
            List<KhachHang> listkh =khachHangDAO.findAll();
            model.addAttribute("listkh", listkh);
            return "giohang/thanhtoan";
        }else {
            for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()){
                HoaDon hoaDon = new HoaDon();
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                String date = String.valueOf(java.time.LocalDate.now());

                if(httt==1){
                    hoaDon.setManv(1);
                    hoaDon.setMakh(1);
                    hoaDon.setMahttt(httt);
                    hoaDon.setNgaytao(date);
                    hoaDon.setNgaythanhtoan(date);

                    hoaDonDAO.save(hoaDon);

                    chiTietHoaDon.setMactg(entry.getValue().getChiTietGiay().getMactg());
                    chiTietHoaDon.setMahd(hoaDon.getMahd());
                    chiTietHoaDon.setSoluong(soluong);

                    chiTietHoaDonDAO.save(chiTietHoaDon);
                }else {
                    hoaDon.setManv(1);
                    hoaDon.setMakh(1);
                    hoaDon.setMahttt(httt);

                    hoaDon.setNgaytao(date);

                    hoaDonDAO.save(hoaDon);

                    chiTietHoaDon.setMactg(entry.getValue().getChiTietGiay().getMactg());
                    chiTietHoaDon.setMahd(hoaDon.getMahd());
                    chiTietHoaDon.setSoluong(soluong);

                    chiTietHoaDonDAO.save(chiTietHoaDon);
                }

            }
            cartItems.clear();
            return "redirect:/hoadon/index";
        }

    }

    public int totalPrice(HashMap<Integer, Cart> cartItems) {
        int count = 0;
        for (Map.Entry<Integer, Cart> list : cartItems.entrySet()) {
            int mag =list.getValue().getChiTietGiay().getMag();
            Giay giay = giayDAO.getById(mag);
            count += giay.getGia()* list.getValue().getSoluong();
        }
        return count;
    }


}


