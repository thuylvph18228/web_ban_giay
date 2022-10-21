package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import poly.edu.DAO.*;
import poly.edu.Entity.*;

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
    public KhachHangDAO khachHangDAO;

    @Autowired
    public GioHangDAO gioHangDAO;

    @Autowired
    ThanhToanDAO ttdao;

    @Autowired
    HoaDonDAO hddao;

    @GetMapping("/giohang/index")
    public String listkh(Model model) {
        List<GioHang> listgh =gioHangDAO.findAll();
        model.addAttribute("listgh", listgh);
        List<Giay> listg =giayDAO.findAll();
        model.addAttribute("listg", listg);
        List<KhachHang> listkh =khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        return ("giohang/index");
    }

    @GetMapping("/giohang/create")
    public String create(@ModelAttribute("giohang") GioHang gioHang, Model model) {


        List<Giay> listg =giayDAO.findAll();
        model.addAttribute("listg", listg);
        List<KhachHang> listkh =khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        model.addAttribute("savegh", "/savegh");
        return "giohang/save";
    }

    @GetMapping("/giohang/edit/{magh}")
    public String edit(@PathVariable(name = "magh") int magh, Model model) {
        model.addAttribute("magh", magh);
        GioHang gh = gioHangDAO.getById(magh);
        List<Giay> listg =giayDAO.findAll();
        model.addAttribute("listg", listg);
        List<KhachHang> listkh =khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        model.addAttribute("giohang", gh);
        model.addAttribute("savegh", "/savegh");
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
            List<Giay> listg =giayDAO.findAll();
            model.addAttribute("listg", listg);
            List<KhachHang> listkh =khachHangDAO.findAll();
            model.addAttribute("listkh", listkh);
            return "giohang/save";
        }
        gioHangDAO.save(gioHang);
        return "redirect:/giohang/index";
    }

    @GetMapping("/giohang/thanhtoan/{magh}")
    public String thanhtoan(@PathVariable(name = "magh") int magh, Model model) {
        model.addAttribute("magh", magh);
        GioHang gh = gioHangDAO.getById(magh);
        List<Giay> listg =giayDAO.findAll();
        model.addAttribute("listg", listg);
        List<ThanhToan> listtt =ttdao.findAll();
        model.addAttribute("listtt", listtt);
        List<KhachHang> listkh =khachHangDAO.findAll();
        model.addAttribute("listkh", listkh);
        model.addAttribute("giohang", gh);
        model.addAttribute("savetthd", "/savetthd");
        return "giohang/thanhtoan";
    }

    @PostMapping("/savetthd")
    public String savett(@Valid @ModelAttribute("giohang")   GioHang gioHang, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            List<Giay> listg =giayDAO.findAll();
            model.addAttribute("listg", listg);
            List<KhachHang> listkh =khachHangDAO.findAll();
            model.addAttribute("listkh", listkh);
            return "giohang/thanhtoan";
        }else {
            HoaDon hd = new HoaDon();
            String date = String.valueOf(java.time.LocalDate.now());
            if(gioHang.getTrangthai()==1){
                hd.setMakh(gioHang.getMakh());
                hd.setManv(1);
                hd.setMahttt(2);
                hd.setNgaytao(date);
                hd.setNgaythanhtoan(date);
                hd.setDiachi(gioHang.getDiachi());
                hd.setTennguoinhan(gioHang.getTennguoinhan());
            }else {
                hd.setMakh(gioHang.getMakh());
                hd.setManv(1);
                hd.setMahttt(1);
                hd.setNgaytao(date);
                hd.setDiachi(gioHang.getDiachi());
                hd.setTennguoinhan(gioHang.getTennguoinhan());
            }
            hddao.save(hd);
            gioHangDAO.delete(gioHang);
            return "redirect:/hoadon/index";
        }
    }
}


