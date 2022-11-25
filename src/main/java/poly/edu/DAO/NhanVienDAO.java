package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.KhachHang;
import poly.edu.Entity.NhanVien;

public interface NhanVienDAO extends JpaRepository<NhanVien, Integer> {
    @Query("SELECT e FROM NhanVien e WHERE e.email = ?1")
    NhanVien findByEmail(String username);
}
