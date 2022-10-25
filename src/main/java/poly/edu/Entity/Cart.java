package poly.edu.Entity;

public class Cart {
    private ChiTietGiay chiTietGiay;
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
