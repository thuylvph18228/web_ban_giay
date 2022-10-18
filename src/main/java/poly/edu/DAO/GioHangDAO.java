package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.Entity.GioHang;

public interface GioHangDAO extends JpaRepository<GioHang, Integer> {
}
