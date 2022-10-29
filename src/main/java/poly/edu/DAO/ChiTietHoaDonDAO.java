package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<<<< Temporary merge branch 1
import poly.edu.Entity.ChiTietGiay;
=========
>>>>>>>>> Temporary merge branch 2
import poly.edu.Entity.ChiTietHoaDon;
import poly.edu.Entity.HoaDon;

import java.util.List;

import java.util.List;

public interface ChiTietHoaDonDAO extends JpaRepository<ChiTietHoaDon,Integer> {
<<<<<<<<< Temporary merge branch 1
    @Query(" SELECT c from  ChiTietHoaDon c join ChiTietGiay ctg on ctg.mactg  = c.mactg\n" +
            "  where c .mahd=?1 group by ctg .mactg")
    List<ChiTietHoaDon> findByMahd(int mahd);
=========
    @Query(" SELECT e FROM ChiTietHoaDon e  WHERE e.mahd = ?1")
    List<HoaDon> findByMahd(int mahd);
>>>>>>>>> Temporary merge branch 2
}
