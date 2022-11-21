package poly.edu.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import poly.edu.Entity.Giay;
import poly.edu.Entity.GioHang;
import poly.edu.Entity.HoaDon;
import poly.edu.Entity.KhachHang;

import java.util.Collections;
import java.util.List;

@Repository
public interface GiayDAO extends JpaRepository<Giay, Integer> {
    public Giay findByTenEquals(String ten) ;

    @Query("SELECT e FROM Giay e WHERE e.mag = ?1")
    List<Giay> findByMag(int mag);

    @Query("SELECT e FROM Giay e WHERE e.ten = ?1")
    List<Giay> findByName(String teng);

    @Query("SELECT g FROM  Giay g join ChiTietGiay c on c.mag=g.mag\n" +
            "join ChiTietHoaDon  ch on ch.mactg  = c.mactg\n" +
            "                                where ch.mahd  = ?1")
    List<Giay> findByMahd(int mahd);
    @Query(value ="SELECT * from Giay g order by  g.mag desc limit 4 ", nativeQuery = true )
    List<Giay> findByTop5New();

//    @Query(value = "SELECT * FROM Giay g \n" +
//            "join ChiTietGiay ctg on ctg.mag  = g.mag\n" +
//            "join ChiTietHoaDon c on c.mactg = ctg.mactg \n" +
//            "group by g.mag  \n" +
//            "order by sum(ctg.soluong) desc LIMIT 4"  ,nativeQuery = true)
//    List<Giay> findBySelling( );

    @Query( value = "SELECT * FROM Giay e WHERE e.ten LIKE %?1%" ,nativeQuery = true)
    Page<Giay> findByNameLike(String name, Pageable pageable);

    @Query("SELECT g FROM Giay g join ChiTietGiay c on g.mag = c.mag where c.mansx=?1 group by g.mag")
    Page<Giay> findByNsx(int mansx, Pageable pageable);

//    @Query(value ="SELECT * from Giay g order by  g.mag desc limit 4 ", nativeQuery = true )
//    List<Giay> findByTop5New();

    @Query(value = "SELECT * FROM Giay g \n" +
            "join ChiTietGiay ctg on ctg.mag  = g.mag\n" +
            "join ChiTietHoaDon c on c.mactg = ctg.mactg \n" +
            "group by g.mag  \n" +
            "order by sum(ctg.soluong) desc LIMIT 4"  ,nativeQuery = true)
    List<Giay> findBySellingTop5( );
}
