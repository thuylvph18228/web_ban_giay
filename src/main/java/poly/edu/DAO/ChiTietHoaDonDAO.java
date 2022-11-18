package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.ChiTietGiay;

import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.ChiTietHoaDon;
import poly.edu.Entity.Giay;
import poly.edu.Entity.HoaDon;

import java.util.List;

import java.util.List;

public interface ChiTietHoaDonDAO extends JpaRepository<ChiTietHoaDon, Integer> {

    @Query(" SELECT c from  ChiTietHoaDon c join ChiTietGiay ctg on ctg.mactg  = c.mactg\n" +
            "  where c .mahd=?1 group by ctg .mactg")
    List<ChiTietHoaDon> findByMahd(int mahd);

    @Query(" select c from ChiTietHoaDon c join HoaDon h " +
            "on c.mahd = h.mahd where h.mahd  = ?1")
    List<ChiTietHoaDon> findByMahdtrahang(int mahd);

    @Query(value = "SELECT g.mag, g.ten, ctg.mactg, hd.mahd, c.macthd, sum(c.soluong) as soluong\n" +
            "FROM Giay g\n" +
            "         join ChiTietGiay ctg on ctg.mag = g.mag\n" +
            "         join ChiTietHoaDon c on c.mactg = ctg.mactg\n" +
            "         join hoadon hd on hd.mahd = c.mahd\n" +
            "group by g.mag\n" +
            "order by sum(c.soluong) desc\n" +
            "LIMIT 5;", nativeQuery = true)
    List<ChiTietHoaDon> findBySellingCthd();

    @Query("select c from HoaDon hd join ChiTietHoaDon c on hd.mahd = c.mahd  where hd.ngaythanhtoan = ?1")
    List<ChiTietHoaDon> findSouluongByDay(String date);

    @Query("select c from HoaDon hd join ChiTietHoaDon c on hd.mahd = c.mahd  where month(hd.ngaythanhtoan)= ?1")
    List<ChiTietHoaDon> findSouluongByMonth(int month);

    @Query("select c from HoaDon hd join ChiTietHoaDon c on hd.mahd = c.mahd  where year(hd.ngaythanhtoan)= ?1")
    List<ChiTietHoaDon> findSouluongByYear(int year);
}
