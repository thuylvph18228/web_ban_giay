package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.Giay;
import poly.edu.Entity.GioHang;

import java.util.List;

public interface GioHangDAO extends JpaRepository<GioHang, Integer> {
    //public GioHang findByMakhEquals(int makh) ;
    @Query("SELECT e FROM GioHang e WHERE e.makh = ?1")
    List<GioHang> findByMakh(int makh);
}
