package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.Entity.KhachHang;

public interface KhachHangDAO  extends JpaRepository<KhachHang, Integer> {
}
