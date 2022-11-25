package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.edu.DAO.*;
import poly.edu.Entity.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ThongKeController {

    @Autowired
    GiayDAO giayDAO;

    @Autowired
    HttpSession httpSession;


    @Autowired
    ThongBaoDAO thongBaoDAO;
    @Autowired
    ChiTietGiayDAO chiTietGiayDAO;

    @Autowired
    HoaDonDAO hoaDonDAO;

    @Autowired
    KhachHangDAO khachHangDAO;

    @Autowired
    ChiTietHoaDonDAO chiTietHoaDonDAO;

    @GetMapping("/rest/admin/thongkengay")
    public String thongke(Model model){
        List<Giay> ps = this.giayDAO.findBySellingTop5();
        model.addAttribute("listgs", ps);

        List<HoaDon> hd = this.hoaDonDAO.findBySellingHd();
        model.addAttribute("listhd", hd);

        List<KhachHang> kh = this.khachHangDAO.findAll();
        model.addAttribute("listkh", kh);

        List<ChiTietHoaDon> cthd = this.chiTietHoaDonDAO.findBySellingCthd();
        model.addAttribute("listcthd", cthd);

        List<ChiTietGiay> ctg = this.chiTietGiayDAO.findBySellingCtg();
        model.addAttribute("listctg", ctg);

        model.addAttribute("findByDay", "/rest/admin/findByDay");
        List<ThongBao> listtb = thongBaoDAO.findByThongBaoChuaXem();
//        model.addAttribute("size", size);
        httpSession.setAttribute("sizeth",listtb.size());
        httpSession.setAttribute("listb",listtb);
        return "admin/thongke/thongkengay";
    }

    @GetMapping("/rest/admin/findByDay")
    public String findDoanhThuByNgay(Model model, @RequestParam("date") String date){
        List<HoaDon> doanhthungay = hoaDonDAO.findDoanhThuByNgay(date);
        int tongtienngay =0;
        for (HoaDon dtn : doanhthungay){
            tongtienngay += dtn.getTongtien();
        }
        model.addAttribute("tongtienngay", tongtienngay);

        List<ChiTietHoaDon> soluong = chiTietHoaDonDAO.findSouluongByDay(date);
        int soluongngay =0;
        for (ChiTietHoaDon sln : soluong){
            soluongngay += sln.getSoluong();
        }
        model.addAttribute("soluongngay", soluongngay);

        int tonghoadon = doanhthungay.size();
        System.out.println(doanhthungay);
        model.addAttribute("tonghoadon", tonghoadon);

        List<Giay> ps = this.giayDAO.findBySellingTop5();
        model.addAttribute("listgs", ps);

        List<HoaDon> hd = this.hoaDonDAO.findBySellingHd();
        model.addAttribute("listhd", hd);

        List<KhachHang> kh = this.khachHangDAO.findAll();
        model.addAttribute("listkh", kh);

        List<ChiTietHoaDon> cthd = this.chiTietHoaDonDAO.findBySellingCthd();
        model.addAttribute("listcthd", cthd);

        List<ChiTietGiay> ctg = this.chiTietGiayDAO.findBySellingCtg();
        model.addAttribute("listctg", ctg);
        return "admin/thongke/thongkengay";
    }

    @GetMapping("/rest/admin/thongkethang")
    public String thongkethang(Model model){
        List<Giay> ps = this.giayDAO.findBySellingTop5();
        model.addAttribute("listgs", ps);

        List<HoaDon> hd = this.hoaDonDAO.findBySellingHd();
        model.addAttribute("listhd", hd);

        List<KhachHang> kh = this.khachHangDAO.findAll();
        model.addAttribute("listkh", kh);

        List<ChiTietHoaDon> cthd = this.chiTietHoaDonDAO.findBySellingCthd();
        model.addAttribute("listcthd", cthd);

        List<ChiTietGiay> ctg = this.chiTietGiayDAO.findBySellingCtg();
        model.addAttribute("listctg", ctg);

        model.addAttribute("findByMonth", "/rest/admin/findByMonth");

        return "admin/thongke/thongkethang";
    }

    @GetMapping("/rest/admin/findByMonth")
    public String findDoanhThuByMonth(Model model, @RequestParam("month") int month){

//        List<HoaDon> listmonth = hoaDonDAO.findByMonthNTT();
//        model.addAttribute("listmonth", listmonth);
        List<HoaDon> doanhthuthang = hoaDonDAO.findDoanhThuByMonth(month);
        int tongtienthang =0;
        for (HoaDon dtt : doanhthuthang){
            tongtienthang += dtt.getTongtien();
        }
        model.addAttribute("tongtienthang", tongtienthang);

        List<ChiTietHoaDon> soluong = chiTietHoaDonDAO.findSouluongByMonth(month);
        int soluongthang =0;
        for (ChiTietHoaDon sln : soluong){
            soluongthang += sln.getSoluong();
        }
        model.addAttribute("soluongthang", soluongthang);

        int tonghoadon = doanhthuthang.size();
        model.addAttribute("tonghoadon", tonghoadon);

        List<Giay> ps = this.giayDAO.findBySellingTop5();
        model.addAttribute("listgs", ps);

        List<HoaDon> hd = this.hoaDonDAO.findBySellingHd();
        model.addAttribute("listhd", hd);

        List<KhachHang> kh = this.khachHangDAO.findAll();
        model.addAttribute("listkh", kh);

        List<ChiTietHoaDon> cthd = this.chiTietHoaDonDAO.findBySellingCthd();
        model.addAttribute("listcthd", cthd);

        List<ChiTietGiay> ctg = this.chiTietGiayDAO.findBySellingCtg();
        model.addAttribute("listctg", ctg);
        return "admin/thongke/thongkethang";
    }

    @GetMapping("/rest/admin/thongkenam")
    public String thongkenam(Model model){
        List<Giay> ps = this.giayDAO.findBySellingTop5();
        model.addAttribute("listgs", ps);


        List<HoaDon> hd = this.hoaDonDAO.findBySellingHd();
        model.addAttribute("listhd", hd);

        List<KhachHang> kh = this.khachHangDAO.findAll();
        model.addAttribute("listkh", kh);

        List<ChiTietHoaDon> cthd = this.chiTietHoaDonDAO.findBySellingCthd();
        model.addAttribute("listcthd", cthd);

        List<ChiTietGiay> ctg = this.chiTietGiayDAO.findBySellingCtg();
        model.addAttribute("listctg", ctg);

        model.addAttribute("findByYear", "/rest/admin/findByYear");

        return "admin/thongke/thongkenam";
    }

    @GetMapping("/rest/admin/findByYear")
    public String findDoanhThuByYear(Model model, @RequestParam("year") int year){
        List<HoaDon> doanhthunam = hoaDonDAO.findDoanhThuByYear(year);
        int tongtiennam =0;
        for (HoaDon dtn : doanhthunam){
            tongtiennam += dtn.getTongtien();
        }
        model.addAttribute("tongtiennam", tongtiennam);

        List<ChiTietHoaDon> soluong = chiTietHoaDonDAO.findSouluongByYear(year);
        int soluongnam =0;
        for (ChiTietHoaDon sln : soluong){
            soluongnam += sln.getSoluong();
        }
        model.addAttribute("soluongnam", soluongnam);

        int tonghoadon = doanhthunam.size();
        model.addAttribute("tonghoadon", tonghoadon);

        List<Giay> ps = this.giayDAO.findBySellingTop5();
        model.addAttribute("listgs", ps);

        List<HoaDon> hd = this.hoaDonDAO.findBySellingHd();
        model.addAttribute("listhd", hd);

        List<KhachHang> kh = this.khachHangDAO.findAll();
        model.addAttribute("listkh", kh);

        List<ChiTietHoaDon> cthd = this.chiTietHoaDonDAO.findBySellingCthd();
        model.addAttribute("listcthd", cthd);

        List<ChiTietGiay> ctg = this.chiTietGiayDAO.findBySellingCtg();
        model.addAttribute("listctg", ctg);
        return "admin/thongke/thongkenam";
    }
}
