package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.Giay;
import poly.edu.Entity.GioHang;
import poly.edu.Entity.KhachHang;

import java.util.List;

public interface GiayDAO extends JpaRepository<Giay, Integer> {
    public Giay findByTenEquals(String ten) ;

    @Query("SELECT e FROM Giay e WHERE e.mag = ?1")
    List<Giay> findByMag(int mag);
}
