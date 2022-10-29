package poly.edu.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Cart {
    private ChiTietGiay chiTietGiay;
    @NotNull(message = "Không được để trống số lượng")
    private int soluong;

    public Cart(){

    }
    public Cart(ChiTietGiay chiTietGiay) {
        this.chiTietGiay = chiTietGiay;
    }

    public ChiTietGiay getChiTietGiay() {
        return chiTietGiay;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setChiTietGiay(ChiTietGiay chiTietGiay) {
        this.chiTietGiay = chiTietGiay;
    }
}
