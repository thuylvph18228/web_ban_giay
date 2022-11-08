package poly.edu.DAO;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import poly.edu.Entity.HoaDon;

public interface HoaDonDAO extends JpaRepository<HoaDon,Integer> {

    @Query("SELECT h FROM  HoaDon h join KhachHang k on h.makh = k.makh\n" +
            "join ChiTietHoaDon c on c.mahd= h.mahd\n" +
            "                        join ChiTietGiay  ctg on ctg.mactg = c.mactg\n" +
            "                        join Giay g on ctg.mag = g.mag \n" +
            " where k.sdt = ?1 group by h.mahd")
    List<HoaDon> findBySdt(String sdt);

    @Query("SELECT h FROM HoaDon h where h.trangthaidh=0")
    List<HoaDon> findByTrangthaicxn();
    @Query("SELECT h FROM HoaDon h where h.trangthaidh =1")
    List<HoaDon> findByTrangthaidvc();
    @Query(" SELECT e FROM HoaDon e  WHERE e.makh = ?1")
    List<HoaDon> findByMakh(int makh);

    @Query("SELECT e FROM HoaDon e\n" +
            "WHERE e.mahd = (SELECT MAX(e.mahd) FROM HoaDon )")
    List<HoaDon> findMaxHDByMa(int mahd);

    @Query("SELECT h  from HoaDon h where DATEDIFF( DATE(now()),h.ngaynhan )<7 and trahang= 0" +
            "group by h.mahd")
    List<HoaDon> findHDsmaller7();

}
