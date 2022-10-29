package poly.edu.DAO;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

//    @Query("SELECT h FROM HoaDon h WHERE h.makh = ?1")
//    List<HoaDon> findByMaKH(int makh);


    @Query(" SELECT e FROM HoaDon e  WHERE e.makh = ?1")
    List<HoaDon> findByMakh(int makh);

    @Query("SELECT e FROM HoaDon e\n" +
            "WHERE e.mahd = (SELECT MAX(e.mahd) FROM HoaDon )")
    List<HoaDon> findMaxHDByMa(int mahd);

}
