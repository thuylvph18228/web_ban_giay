package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.*;
import poly.edu.Entity.*;
import poly.edu.Entity.Respon.TToan;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;


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

    @GetMapping("/giohang/index")
    public String listkh(Model model) {
        List<GioHang> listgh =gioHangDAO.findAll();
        model.addAttribute("listgh", listgh);
        List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);
        return ("giohang/index");
    }

    @GetMapping("/giohang/create")
    public String create(@ModelAttribute("giohang") GioHang gioHang, Model model) {
        List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);
        model.addAttribute("savegh", "/savegh");
        return "giohang/save";
    }

    @GetMapping("/giohang/edit/{magh}")
    public String edit(@PathVariable(name = "magh") int magh, Model model) {
        model.addAttribute("magh", magh);
        GioHang gh = gioHangDAO.getById(magh);
        List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
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
    public String save(@Valid @ModelAttribute("giohang")   GioHang gioHang, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
            model.addAttribute("listctg", listctg);
            return "giohang/save";
        }
        gioHangDAO.save(gioHang);
        return "redirect:/giohang/index";
    }


    @GetMapping("/giohang/thanhtoan/{magh}")
    public String thanhtoan(@PathVariable(name = "magh") int magh, Model model) {
        model.addAttribute("magh", magh);
        GioHang gh = gioHangDAO.getById(magh);
        model.addAttribute("giohang", gh);

        List<Giay> listg =giayDAO.findAll();
        model.addAttribute("listg", listg);

        List<Size> lists =sizeDAO.findAll();
        model.addAttribute("lists", lists);

        List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
        model.addAttribute("listctg", listctg);

        List<ThanhToan> listtt =thanhToanDAO.findAll();
        model.addAttribute("listtt", listtt);

        model.addAttribute("savetthd", "/savetthd");
        return "giohang/thanhtoan";
    }

    @PostMapping("/savetthd")
    public String savett(@Valid @ModelAttribute("giohang")   GioHang gioHang,
                         BindingResult bindingResult,Model model,
                         @RequestParam("soluong") Integer soluong,
                         @RequestParam(name="ten") String ten,
                         @RequestParam("sdt") String sdt,
                         @RequestParam("diachi") String diachi,
                         @RequestParam("httt") Integer httt,
                         @RequestParam("mactg") Integer mactg
    ) {
        if (bindingResult.hasErrors()) {
            List<Giay> listg =giayDAO.findAll();
            model.addAttribute("listg", listg);
            List<KhachHang> listkh =khachHangDAO.findAll();
            model.addAttribute("listkh", listkh);
            return "giohang/thanhtoan";
        }else {
            ChiTietGiay ctg = chiTietGiayDAO.getById(mactg);
            if(soluong>ctg.getSoluong()){
                model.addAttribute("message", "Số lượng bạn muốn mua lớn hơn số lượng trong kho");
                List<GioHang> listgh =gioHangDAO.findAll();
                model.addAttribute("listgh", listgh);
                List<ChiTietGiay> listctg =chiTietGiayDAO.findAll();
                model.addAttribute("listctg", listctg);
                return "/giohang/index";
            }else {

                ctg.setSoluong(ctg.getSoluong() - soluong);
                chiTietGiayDAO.save(ctg);
            }
            HoaDon hd = new HoaDon();
            String date = String.valueOf(java.time.LocalDate.now());
            hd.setManv(1);
            hd.setMakh(1);
            hd.setMahttt(httt);
            hd.setNgaytao(date);
            hd.setDiachi(diachi);
            hd.setSdt(sdt);
            hd.setTennguoinhan(ten);
            hoaDonDAO.save(hd);
            gioHangDAO.delete(gioHang);
            return "redirect:/hoadon/index";
        }
    }

}


