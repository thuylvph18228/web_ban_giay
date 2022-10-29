package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.ChiTietHoaDon;
import poly.edu.Entity.HoaDon;

import java.util.List;

public interface ChiTietHoaDonDAO extends JpaRepository<ChiTietHoaDon,Integer> {
    @Query(" SELECT e FROM ChiTietHoaDon e  WHERE e.mahd = ?1")
    List<HoaDon> findByMahd(int mahd);
}
