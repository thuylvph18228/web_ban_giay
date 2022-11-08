package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.HoaDon;
import poly.edu.Entity.Size;
import poly.edu.Entity.TraHang;

import java.util.List;


public interface TraHangDAO extends JpaRepository<TraHang, Integer> {
    @Query("SELECT t FROM TraHang t where t.makh  =?1")
    List<TraHang> lichsuTraHang(int makh);
}
