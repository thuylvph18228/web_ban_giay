package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.*;
import poly.edu.Entity.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    public GioHangDAO gioHangDAO;

    @Autowired
    public SizeDAO sdao;

    @Autowired
    public GiayDAO giaydao;

    @Autowired
    public NsxDAO nsxdao;

    @Autowired
    public KhachHangDAO khachHangDAO;
    @Autowired
    public HoaDonDAO hoadondao;

    @Autowired
    public ChiTietHoaDonDAO chitiethoadondao;
    @Autowired
    public ChiTietGiayDAO chiTietGiayDAO;

    @GetMapping("/listcart")
    public String list(Model model) {
        List<Size> listsize = sdao.findAll();
        List<Giay> listg = giaydao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listsize", listsize);
        model.addAttribute("listg", listg);
        model.addAttribute("savetthd", "/savetthd");
        return "giohang/giohangkhach";
    }

    @PostMapping("/addproduct")
    public String viewAdd(ModelMap mm, HttpSession session, @RequestParam("mactg") int mactg, Model model) {
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        ChiTietGiay chiTietGiay = chiTietGiayDAO.getById(mactg);
        if (chiTietGiay != null) {
            if (cartItems.containsKey(mactg)) {
                Cart item = cartItems.get(chiTietGiay.getMactg());
                item.setChiTietGiay(chiTietGiay);
                item.setSoluong(item.getSoluong() + 1);
                cartItems.put(mactg, item);
            } else {
                Cart item = new Cart();
                item.setChiTietGiay(chiTietGiay);
                item.setSoluong(1);
                cartItems.put(mactg, item);
            }
            model.addAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công");
        }
        List<Size> listsize = sdao.findAll();
        List<Giay> listg = giaydao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listsize", listsize);
        model.addAttribute("listg", listg);
        model.addAttribute("savetthd", "/savetthd");
        session.setAttribute("myCartItems",cartItems);
        session.setAttribute("myCartToTal",totalPrice(cartItems));
        return "giohang/giohangkhach";
    }

    public int totalPrice(HashMap<Integer, Cart> cartItems) {
        int count = 0;
        for (Map.Entry<Integer, Cart> list : cartItems.entrySet()) {
            int mag = list.getValue().getChiTietGiay().getMag();
            Giay giay = giaydao.getById(mag);
            count += giay.getGia() * list.getValue().getSoluong();
        }
        return count;
    }


    @GetMapping("/viewupdate/{mactg}")
    public String viewUpdate(@ModelAttribute("cart") Cart cart, Model model, ModelMap mm, HttpSession session, @PathVariable("mactg") int mactg) {
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        System.out.println(cartItems);
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        ChiTietGiay chiTietGiay = chiTietGiayDAO.getById(mactg);
        session.setAttribute("myCartItems", cartItems);
        List<Giay> listg = giaydao.findAll();
        List<Size> lists = sdao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("lists", lists);
        model.addAttribute("chiTietGiay", chiTietGiay);
        model.addAttribute("listg", listg);
        model.addAttribute("updatecart", "/updatecart");
        return "giohang/save";
    }

    @PostMapping("/updatecart")
    public String updatecart(@Valid @ModelAttribute("cart") Cart cart, Model model, ModelMap mm, HttpSession session,
                             @RequestParam("soluong") int soluong, @RequestParam("mactg") int mactg,
                             @RequestParam("soluongcon") int soluongcon , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<Size> listsize = sdao.findAll();
            List<Giay> listg = giaydao.findAll();
            model.addAttribute("listsize", listsize);
            model.addAttribute("listg", listg);
            return "giohang/save";
        }
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        ChiTietGiay chiTietGiay = chiTietGiayDAO.getById(mactg);
        if (chiTietGiay != null) {
            if (cartItems.containsKey(mactg)) {
                Cart item = cartItems.get(chiTietGiay.getMactg());
                item.setChiTietGiay(chiTietGiay);
                if (soluong<1  ||soluongcon < soluong){
                    model.addAttribute("error", "Vui lòng nhập lại giỏ hàng!!");
                    List<Nsx> listnsx = nsxdao.findAll();
                    model.addAttribute("listnsx", listnsx);
                    List<Size> listsize = sdao.findAll();
                    List<Giay> listg = giaydao.findAll();
                    model.addAttribute("listsize", listsize);
                    model.addAttribute("listg", listg);
                    return "giohang/giohangkhach";
                }

                item.setSoluong(soluong);
                cartItems.put(mactg, item);
                model.addAttribute("message", "Cập nhập số lượng thành công");
            }
        }
        List<Size> listsize = sdao.findAll();
        List<Giay> listg = giaydao.findAll();
        model.addAttribute("listsize", listsize);
        model.addAttribute("listg", listg);

        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);

        session.setAttribute("myCartItems",cartItems);
        session.setAttribute("myCartToTal",totalPrice(cartItems));
        System.out.println(cartItems);
        System.out.println(totalPrice(cartItems));
        return "redirect:/listcart";


    }

    @GetMapping("/removecart/{mactg}")
    public String viewRemove(ModelMap mm,Model model, HttpSession session, @PathVariable("mactg") int mactg) {
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        if (cartItems.containsKey(mactg)) {
            cartItems.remove(mactg);
            model.addAttribute("message", "Xóa sản phẩm thành công");
        }

        List<Size> listsize = sdao.findAll();
        List<Giay> listg = giaydao.findAll();
        model.addAttribute("listsize", listsize);
        model.addAttribute("listg", listg);
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartToTal",totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "redirect:/listcart";
    }

    @GetMapping("/viewfindcart")
    public String viewFindCart(Model model) {
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("yourorder", "/yourorder");
        return "hoadon/viewbysdt";
    }
    @GetMapping("/yourorder")
    public String yourorder(@RequestParam("sdt") String sdt,Model model) {
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);
        List<HoaDon> hoaDonList=hoadondao.findBySdt(sdt);
        List<KhachHang> khachHangList =khachHangDAO.findAll();
        model.addAttribute("khachHangList", khachHangList);
        model.addAttribute("hoaDonList", hoaDonList);
        return "hoadon/findhdkhach";
    }
    @GetMapping("/purchasedproduct/{mahd}")
    public String purchasedproduct(@PathVariable("mahd") int mahd, Model model) {
     List<ChiTietGiay> chiTietGiayList =  chiTietGiayDAO.findByMahd(mahd);
     List<ChiTietHoaDon> chiTietHoaDonList =  chitiethoadondao.findByMahd(mahd);

        List<Size> listsize = sdao.findAll();
        List<Giay> listg = giaydao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("chiTietHoaDonList", chiTietHoaDonList);
        model.addAttribute("chiTietGiayList", chiTietGiayList);
        model.addAttribute("listsize", listsize);
        model.addAttribute("listg", listg);

        return "hoadon/purchasedproduct";
    }
}
