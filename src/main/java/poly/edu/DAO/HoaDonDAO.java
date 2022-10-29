package poly.edu.DAO;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import poly.edu.Entity.Giay;
import poly.edu.Entity.HoaDon;

public interface HoaDonDAO extends JpaRepository<HoaDon,Integer> {
//    @Query("SELECT h FROM HoaDon h WHERE h.makh = ?1")
//    List<HoaDon> findByMaKH(int makh);


    @Query(" SELECT e FROM HoaDon e  WHERE e.sdt = ?1")
    List<HoaDon> findBySdt(String sdt);

    @Query("SELECT e FROM HoaDon e\n" +
            "WHERE e.mahd = (SELECT MAX(e.mahd) FROM HoaDon )")
    List<HoaDon> findByMa(int mahd);
}
