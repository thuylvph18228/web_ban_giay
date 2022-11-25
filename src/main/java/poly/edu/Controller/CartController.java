package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.*;
import poly.edu.Entity.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
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
    public LoaiGiayDAO lgdao;

    @Autowired
    public KhachHangDAO khachHangDAO;

    @Autowired
    public DanhGiaDAO danhGiaDAO;

    @Autowired
    public HttpSession session;

    @Autowired
    public HoaDonDAO hoadondao;

    @Autowired
    public ChiTietHoaDonDAO chitiethoadondao;
    @Autowired
    public ChiTietGiayDAO chiTietGiayDAO;

    @GetMapping("/user/cart/listcart")
    public String list(Model model , HttpSession session) {
        List<Size> listsize = sdao.findAll();
        List<Giay> listg = giaydao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        List<ChiTietGiay> chiTietGiayList = chiTietGiayDAO.findAll();
        model.addAttribute("chiTietGiayList", chiTietGiayList);

        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listsize", listsize);
        model.addAttribute("listg", listg);
        model.addAttribute("savetthd", "/savetthd");
        model.addAttribute("viewupdate", "/viewupdate/{mactg}");
        return "user/giohang/giohangkhach";
    }




    @GetMapping("/user/cart/buy/{mag}")
    public String buy(@ModelAttribute("chitietgiay") ChiTietGiay chiTietGiay,Model model, @PathVariable(name = "mag") int mag,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                      @RequestParam(name = "size", defaultValue = "3") int size) {
        List<ChiTietGiay> listctg = chiTietGiayDAO.findByMag(mag);
        Giay giay = giaydao.getById(mag);
        List<Size> listsize = sdao.findAll();
        List<KhachHang> listkh = khachHangDAO.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        List<LoaiGiay> listlg = lgdao.findAll();
        List<Giay> listg = giaydao.findAll();
        Pageable pageable = PageRequest.of(page, size);
        Page<DanhGia> p = this.danhGiaDAO.findByDanhGia(pageable,mag);


        List<DanhGia> listdg0 = danhGiaDAO.findByDanhGia0(mag);
        List<DanhGia> listdg1 = danhGiaDAO.findByDanhGia1(mag);
        List<DanhGia> listdg2 = danhGiaDAO.findByDanhGia2(mag);

        int totalPages = p.getTotalPages()-1;
        int end = p.getTotalPages()-1;
        int begin = 0;
        int index = p.getNumber();
        int pre = p.getNumber()-1;
        int next = p.getNumber()+1;
        String baseUrl = "/user/cart/buy/"+mag+"/?page=";

        model.addAttribute("listdg", p);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        model.addAttribute("index", index);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);
        model.addAttribute("baseUrl", baseUrl);
        session.setAttribute("listdg0", listdg0.size());
        session.setAttribute("listdg1", listdg1.size());
        session.setAttribute("listdg2", listdg2.size());
        model.addAttribute("listkh", listkh);
//        model.addAttribute("listdg", listdg);
        model.addAttribute("giay", giay);
        model.addAttribute("listctg", listctg);
        model.addAttribute("listsize", listsize);
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listlg", listlg);
        model.addAttribute("listg", listg);
        model.addAttribute("addproduct", "/user/cart/addproduct");

        return "user/giay/buy";
    }

    @GetMapping("/user/cart/buynow/{mag}")
    public String viewbuynow(@ModelAttribute("chitietgiay") ChiTietGiay chiTietGiay, @PathVariable(name = "mag") int mag, Model model) {
        List<ChiTietGiay> listctg = chiTietGiayDAO.findByMag(mag);
        Giay giay = giaydao.getById(mag);
        List<Size> listsize = sdao.findAll();
        List<Nsx> listnsx = nsxdao.findAll();
        List<LoaiGiay> listlg = lgdao.findAll();
        List<Giay> listg = giaydao.findAll();

        model.addAttribute("giay", giay);
        model.addAttribute("listctg", listctg);
        model.addAttribute("listsize", listsize);
        model.addAttribute("listnsx", listnsx);
        model.addAttribute("listlg", listlg);
        model.addAttribute("listg", listg);
        model.addAttribute("buynow", "/user/cart/buynow");


        return "user/giay/buynow";
    }
    @PostMapping("/user/cart/buynow")
    public String buynow(ModelMap mm, HttpSession session, @RequestParam("soluong") int soluong,
    @RequestParam("mactg") int mactg , Model model  ) {

        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        ChiTietGiay chiTietGiay = chiTietGiayDAO.getById(mactg);
        if (chiTietGiay != null) {
            if (cartItems.containsKey(mactg)) {
                Cart item = cartItems.get(chiTietGiay.getMactg());
                if (  soluong<1||soluong>chiTietGiay.getSoluong()+item.getSoluong()){
                    model.addAttribute("error", "Vui lòng nhập lại giỏ hàng!!");
                    List<ChiTietGiay> listctg = chiTietGiayDAO.findByMag(chiTietGiay.getMag());
                    Giay giay = giaydao.getById(chiTietGiay.getMag());
                    List<Size> listsize = sdao.findAll();
                    List<Nsx> listnsx = nsxdao.findAll();
                    List<LoaiGiay> listlg = lgdao.findAll();
                    List<Giay> listg = giaydao.findAll();

                    model.addAttribute("error", "Vui lòng nhập lại số lượng!!");
                    model.addAttribute("giay", giay);
                    model.addAttribute("listctg", listctg);
                    model.addAttribute("listsize", listsize);
                    model.addAttribute("listnsx", listnsx);
                    model.addAttribute("listlg", listlg);
                    model.addAttribute("listg", listg);
                    model.addAttribute("addproduct", "/user/cart/addproduct");

                    return "user/giay/buynow";
                }else {
                    item.setChiTietGiay(chiTietGiay);
                    item.setSoluong(item.getSoluong() + soluong);
                    cartItems.put(mactg, item);
                }
            } else {
                if (soluong<1||soluong>chiTietGiay.getSoluong()) {
                    model.addAttribute("error", "Vui lòng nhập lại giỏ hàng!!");
                    List<ChiTietGiay> listctg = chiTietGiayDAO.findByMag(chiTietGiay.getMag());
                    Giay giay = giaydao.getById(chiTietGiay.getMag());
                    List<Size> listsize = sdao.findAll();
                    List<Nsx> listnsx = nsxdao.findAll();
                    List<LoaiGiay> listlg = lgdao.findAll();
                    List<Giay> listg = giaydao.findAll();
                    model.addAttribute("error", "Vui lòng nhập lại số lượng!!");
                    model.addAttribute("giay", giay);
                    model.addAttribute("listctg", listctg);
                    model.addAttribute("listsize", listsize);
                    model.addAttribute("listnsx", listnsx);
                    model.addAttribute("listlg", listlg);
                    model.addAttribute("listg", listg);
                    model.addAttribute("addproduct", "/user/cart/addproduct");

                    return "user/giay/buynow";
                }else {
                Cart item = new Cart();
                item.setChiTietGiay(chiTietGiay);
                item.setSoluong(soluong);
                cartItems.put(mactg, item);
                }
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
        session.setAttribute("myCartNum", cartItems.size());
        return "redirect:/user/cart/listcart";

    }
    @PostMapping("/user/cart/addproduct")
    public String viewAdd(ModelMap mm, HttpSession session, @RequestParam("soluong") int soluong,
                          @RequestParam("mactg") int mactg , Model model  ) {

        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        ChiTietGiay chiTietGiay = chiTietGiayDAO.getById(mactg);
        if (chiTietGiay != null) {
            if (cartItems.containsKey(mactg)) {
                Cart item = cartItems.get(chiTietGiay.getMactg());
                if (  soluong<1||soluong>chiTietGiay.getSoluong()+item.getSoluong()){
                    model.addAttribute("error", "Vui lòng nhập lại giỏ hàng!!");
                    List<ChiTietGiay> listctg = chiTietGiayDAO.findByMag(chiTietGiay.getMag());
                    Giay giay = giaydao.getById(chiTietGiay.getMag());
                    List<Size> listsize = sdao.findAll();
                    List<Nsx> listnsx = nsxdao.findAll();
                    List<LoaiGiay> listlg = lgdao.findAll();
                    List<Giay> listg = giaydao.findAll();

                    model.addAttribute("error", "Vui lòng nhập lại số lượng!!");
                    model.addAttribute("giay", giay);
                    model.addAttribute("listctg", listctg);
                    model.addAttribute("listsize", listsize);
                    model.addAttribute("listnsx", listnsx);
                    model.addAttribute("listlg", listlg);
                    model.addAttribute("listg", listg);
                    model.addAttribute("addproduct", "/user/cart/addproduct");

                    return "user/giay/buy";
                }else {
                    item.setChiTietGiay(chiTietGiay);
                    item.setSoluong(item.getSoluong() + soluong);
                    cartItems.put(mactg, item);
                }
            } else {
                if (soluong<1||soluong>chiTietGiay.getSoluong()) {
                    model.addAttribute("error", "Vui lòng nhập lại giỏ hàng!!");
                    List<ChiTietGiay> listctg = chiTietGiayDAO.findByMag(chiTietGiay.getMag());
                    Giay giay = giaydao.getById(chiTietGiay.getMag());
                    List<Size> listsize = sdao.findAll();
                    List<Nsx> listnsx = nsxdao.findAll();
                    List<LoaiGiay> listlg = lgdao.findAll();
                    List<Giay> listg = giaydao.findAll();
                    model.addAttribute("error", "Vui lòng nhập lại số lượng!!");
                    model.addAttribute("giay", giay);
                    model.addAttribute("listctg", listctg);
                    model.addAttribute("listsize", listsize);
                    model.addAttribute("listnsx", listnsx);
                    model.addAttribute("listlg", listlg);
                    model.addAttribute("listg", listg);
                    model.addAttribute("addproduct", "/user/cart/addproduct");

                    return "user/giay/buy";
                }else {
                    Cart item = new Cart();
                    item.setChiTietGiay(chiTietGiay);
                    item.setSoluong(soluong);
                    cartItems.put(mactg, item);
                }
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
        session.setAttribute("myCartNum", cartItems.size());
        return "redirect:/giay/product  ";

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


    @GetMapping("/user/viewupdate/{mactg}")
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

        model.addAttribute("lists", lists);
        model.addAttribute("chiTietGiay", chiTietGiay);
        model.addAttribute("listg", listg);
        model.addAttribute("updatecart", "/user/cart/updatecart");
        return "user/giohang/save";
    }

    @PostMapping("/user/cart/updatecart")
    public String updatecart(@Valid @ModelAttribute("cart") Cart cart, Model model, ModelMap mm, HttpSession session,
                    @RequestParam("soluong") String soluongmua ,   @RequestParam  ("mactg") int mactg) {
        int soluong   = Integer.parseInt(soluongmua);
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        ChiTietGiay chiTietGiay = chiTietGiayDAO.getById(mactg);
        System.out.println(cart);
        System.out.println(chiTietGiay);
        if (chiTietGiay != null) {
            if (cartItems.containsKey(mactg)) {
                Cart item = cartItems.get(chiTietGiay.getMactg());
                item.setChiTietGiay(chiTietGiay);
                if (soluong<1  ||chiTietGiay.getSoluong() < soluong){
                    model.addAttribute("error", "Vui lòng nhập lại giỏ hàng!!");
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
        session.setAttribute("myCartNum", cartItems.size());
        System.out.println(cartItems);
        System.out.println(totalPrice(cartItems));
        return "redirect:/user/cart/listcart";
    }

    @GetMapping("/user/cart/removecart/{mactg}")
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
        return "redirect:/user/cart/listcart";
    }
//    @GetMapping("/user/cart/viewfindcart")
//    public String viewFindCart(Model model) {
//        model.addAttribute("yourorder", "/user/cart/yourorder");
//        return "user/hoadon/viewbysdt";
//    }
//    @GetMapping("/user/cart/yourorder")
//    public String yourorder(@RequestParam("sdt") String sdt,Model model) {
//
//        List<HoaDon> hoaDonList=hoadondao.findBySdt(sdt);
//        List<KhachHang> khachHangList =khachHangDAO.findAll();
//        model.addAttribute("khachHangList", khachHangList);
//        model.addAttribute("hoaDonList", hoaDonList);
//        return "user/hoadon/findhdkhach";
//    }
    @GetMapping("/user/cart/purchasedproduct/{mahd}")
    public String purchasedproduct(@PathVariable("mahd") int mahd, Model model) {
        List<ChiTietGiay> chiTietGiayList =  chiTietGiayDAO.findByMahd(mahd);
        List<ChiTietHoaDon> chiTietHoaDonList =  chitiethoadondao.findByMahd(mahd);

        List<Size> listsize = sdao.findAll();
        List<Giay> listg = giaydao.findAll();

        model.addAttribute("chiTietHoaDonList", chiTietHoaDonList);
        model.addAttribute("chiTietGiayList", chiTietGiayList);
        model.addAttribute("listsize", listsize);
        model.addAttribute("listg", listg);

        return "user/hoadon/purchasedproduct";
    }
}
