package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.ChiTietGiay;
import poly.edu.Entity.GioHang;
import poly.edu.Entity.Sizetheogiay;

import java.util.List;

public interface ChiTietGiayDAO extends JpaRepository<ChiTietGiay, Integer> {
    @Query("SELECT e FROM ChiTietGiay e WHERE e.mag = ?1")
    List<ChiTietGiay> findByMag(int mag);
      @Query("select e.mas from ChiTietGiay e join Giay g on e.mag =  g.mag where  g.mag =?1")
      List<ChiTietGiay> findByMas(int mag);

}
