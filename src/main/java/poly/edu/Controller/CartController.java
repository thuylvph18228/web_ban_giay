package poly.edu.Controller;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.ChiTietGiayDAO;
import poly.edu.DAO.GiayDAO;
import poly.edu.DAO.GioHangDAO;
import poly.edu.DAO.SizeDAO;
import poly.edu.Entity.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    public GioHangDAO gioHangDAO;

    @Autowired
    public SizeDAO sdao;

    @Autowired
    public GiayDAO giaydao;

    @Autowired
    public ChiTietGiayDAO chiTietGiayDAO;

    @GetMapping("/listcart")
    public String list (Model model){


        List<Size> listsize = sdao.findAll();
        List<Giay> listg = giaydao.findAll();
        model.addAttribute("listsize", listsize);
        model.addAttribute("listg", listg);
          return "giohang/giohangkhach";
    }

    @PostMapping ("/addproduct")
    public String viewAdd (ModelMap mm, HttpSession session, @RequestParam("mactg") int mactg,Model model) {
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if(cartItems==null){
            cartItems  =new HashMap<>();
        }

        ChiTietGiay chiTietGiay = chiTietGiayDAO.getById( mactg);
        if (chiTietGiay!=null) {
            if (cartItems.containsKey(mactg)) {
                Cart item = cartItems.get(chiTietGiay.getMactg());
                item.setChiTietGiay(chiTietGiay);
                item.setSoluong(item.getSoluong() + 1);
                cartItems.put(mactg, item);
            }else{
                Cart item  = new Cart();
                item.setChiTietGiay(chiTietGiay);
                item.setSoluong(1);
                cartItems.put(mactg, item);
            }
        }
        List<Size> listsize = sdao.findAll();
        List<Giay> listg = giaydao.findAll();
        model.addAttribute("listsize", listsize);
        model.addAttribute("listg", listg);

        session.setAttribute("myCartItems",cartItems);
       // session.setAttribute("myCartToTal",totalPrice(cartItems));
        session.setAttribute("myCartNum",cartItems.size());
        System.out.println(cartItems);
        return "giohang/giohangkhach";
    }
    @GetMapping("/viewupdate/{mactg}")
    public String viewUpdate ( @ModelAttribute("cart") Cart cart,Model  model , ModelMap mm, HttpSession session, @PathVariable("mactg") int mactg) {
            HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        System.out.println(cartItems);
            if (cartItems == null) {
                cartItems = new HashMap<>();
            }
            ChiTietGiay chiTietGiay =chiTietGiayDAO.getById(mactg);
            session.setAttribute("myCartItems", cartItems);
            List<Giay> listg = giaydao.findAll();
            List<Size> lists = sdao.findAll();
            model.addAttribute("lists", lists);
            model.addAttribute("chiTietGiay", chiTietGiay);
            model.addAttribute("listg", listg);
          return "giohang/save";


    }
}
