package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.Giay;
import poly.edu.Entity.HoaDon;

import java.util.List;

public interface HoaDonDAO extends JpaRepository<HoaDon,Integer> {
    @Query("SELECT h FROM  HoaDon h join KhachHang k on h.makh = k.makh\n" +
            "join ChiTietHoaDon c on c.mahd= h.mahd\n" +
            "                        join ChiTietGiay  ctg on ctg.mactg = c.mactg\n" +
            "                        join Giay g on ctg.mag = g.mag \n" +
            " where k.sdt = ?1 group by h.mahd")
    List<HoaDon> findBySdt(String sdt);
}
