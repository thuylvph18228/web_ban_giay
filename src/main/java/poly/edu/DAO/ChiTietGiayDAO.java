package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import poly.edu.Entity.ChiTietGiay;
import poly.edu.Entity.ChiTietHoaDon;

import java.util.List;

@Repository
public interface ChiTietGiayDAO extends JpaRepository<ChiTietGiay, Integer> {
    @Query("SELECT e FROM ChiTietGiay e WHERE e.mag = ?1")
    List<ChiTietGiay> findByMag(int mag);

    @Query(" SELECT ctg  from  ChiTietGiay  ctg join ChiTietHoaDon c on ctg.mactg  = c.mactg\n" +
            "  where c .mahd=?1 group by ctg .mactg")
    List<ChiTietGiay> findByMahd(int sdt);

    @Query(value = "SELECT *, sum(c.soluong) as soluong\n" +
            "FROM Giay g\n" +
            "         join ChiTietGiay ctg on ctg.mag = g.mag\n" +
            "         join ChiTietHoaDon c on c.mactg = ctg.mactg\n" +
            "         join hoadon hd on hd.mahd = c.mahd\n" +
            "group by g.mag\n" +
            "order by sum(c.soluong) desc\n" +
            "LIMIT 5;", nativeQuery = true)
    List<ChiTietGiay> findBySellingCtg();
}
