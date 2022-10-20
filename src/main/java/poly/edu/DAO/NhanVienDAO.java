package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.Entity.KhachHang;
import poly.edu.Entity.NhanVien;

public interface NhanVienDAO extends JpaRepository<NhanVien, Integer> {
    public NhanVien findByEmailEquals(String email) ;
}
