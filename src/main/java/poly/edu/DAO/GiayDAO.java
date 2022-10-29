package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.edu.Entity.Giay;
import poly.edu.Entity.GioHang;
import poly.edu.Entity.HoaDon;
import poly.edu.Entity.KhachHang;

import java.util.List;

public interface GiayDAO extends JpaRepository<Giay, Integer> {
    public Giay findByTenEquals(String ten) ;

    @Query("SELECT e FROM Giay e WHERE e.mag = ?1")
    List<Giay> findByMag(int mag);

    @Query("SELECT e FROM Giay e WHERE e.ten = ?1")
    List<Giay> findByName(String teng);

<<<<<<<<< Temporary merge branch 1
    @Query("SELECT g FROM Giay g join ChiTietGiay c on g.mag = c.mag where c.mansx=?1 group by g.mag")
=========

    @Query("SELECT e FROM Giay e WHERE e.ten LIKE %?1%")
    List<Giay> findByNameLike(String name);

    @Query("SELECT g .mag,g .ten,g .anh,g.gia,g .mota FROM ChiTietGiay c join Giay g \n" +
            " on g .mag =c .mag where c .mansx =?1 group by g .mag")
>>>>>>>>> Temporary merge branch 2
    List<Giay> findByNsx(int mansx);
}
