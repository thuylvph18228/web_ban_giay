package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.Giay;
import poly.edu.Entity.KhachHang;

import java.util.List;

public interface KhachHangDAO extends JpaRepository<KhachHang, Integer> {
<<<<<<<<< Temporary merge branch 1
=========
    @Query("SELECT e FROM KhachHang e WHERE e.sdt = ?1")
    KhachHang findBySdt(String sdt);
>>>>>>>>> Temporary merge branch 2
}
