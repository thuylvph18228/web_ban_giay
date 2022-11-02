package poly.edu.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import poly.edu.Entity.Giay;
import poly.edu.Entity.GioHang;
import poly.edu.Entity.HoaDon;
import poly.edu.Entity.KhachHang;

import java.util.Collections;
import java.util.List;

public interface GiayDAO extends JpaRepository<Giay, Integer> {



    @Query("SELECT e FROM Giay e WHERE e.ten LIKE %?1%")
    List<Giay> findByNameLike(String name);


    @Query("SELECT g FROM Giay g join ChiTietGiay c on g.mag = c.mag where c.mansx=?1 group by g.mag")
    List<Giay> findByNsx(int mansx);



}
