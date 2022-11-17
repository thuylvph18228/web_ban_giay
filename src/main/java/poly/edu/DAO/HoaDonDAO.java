package poly.edu.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import poly.edu.Entity.ChiTietHoaDon;
import poly.edu.Entity.HoaDon;

public interface HoaDonDAO extends JpaRepository<HoaDon,Integer> {

    @Query("SELECT h FROM  HoaDon h join KhachHang k on h.makh = k.makh\n" +
            "join ChiTietHoaDon c on c.mahd= h.mahd\n" +
            "                        join ChiTietGiay  ctg on ctg.mactg = c.mactg\n" +
            "                        join Giay g on ctg.mag = g.mag \n" +
            " where k.sdt = ?1 group by h.mahd")
    List<HoaDon> findBySdt(String sdt);

    @Query("SELECT h FROM HoaDon h where h.trangthaidh=0")
    List<HoaDon> findByTrangthaicxn();
    @Query("SELECT h FROM HoaDon h where h.trangthaidh =1")
    List<HoaDon> findByTrangthaidvc();

    @Query(" SELECT e FROM HoaDon e  WHERE e.makh = ?1")
    Page<HoaDon> findByMakh(int makh, Pageable pageable);


    @Query(" SELECT e FROM HoaDon e  WHERE e.trangthaidh  =0 and e.makh = ?1")
    Page<HoaDon> findByMakhprocessing(int makh, Pageable pageable);

    @Query(" SELECT e FROM HoaDon e  WHERE e.trangthaidh  =1 and e.makh = ?1")
    Page<HoaDon> findByMakhshipping(int makh, Pageable pageable);

    @Query(" SELECT e FROM HoaDon e  WHERE e.trangthaidh  =2 and e.makh = ?1")
    Page<HoaDon> findByMakhdelivered(int makh, Pageable pageable);


    @Query("SELECT e FROM HoaDon e\n" +
            "WHERE e.mahd = (SELECT MAX(e.mahd) FROM HoaDon )")
    List<HoaDon> findMaxHDByMa(int mahd);

    @Query("SELECT h  from HoaDon h where DATEDIFF( DATE(now()),h.ngaynhan )<7 and h.trahang= 0 " +
            "group by h.mahd")
    Page<HoaDon> findHDsmaller7( Pageable pageable);

    @Query(value = "SELECT *, sum(c.soluong) as soluong\n" +
            "FROM Giay g\n" +
            "         join ChiTietGiay ctg on ctg.mag = g.mag\n" +
            "         join ChiTietHoaDon c on c.mactg = ctg.mactg\n" +
            "         join hoadon hd on hd.mahd = c.mahd\n" +
            "group by g.mag\n" +
            "order by sum(c.soluong) desc\n" +
            "LIMIT 5;", nativeQuery = true)
    List<HoaDon> findBySellingHd();

    @Query(value = "select * from hoadon hd where hd.ngaythanhtoan = '2022-11-11';", nativeQuery = true)
    List<HoaDon> findDoanhThuByDay();

    @Query("select hd from HoaDon hd where hd.ngaythanhtoan = ?1")
    List<HoaDon> findDoanhThuByNgay(String date);

    @Query("SELECT hd from HoaDon hd where month(hd.ngaythanhtoan) = ?1")
    List<HoaDon> findDoanhThuByMonth(int month);

    @Query(value = "SELECT * from hoadon where month(ngaythanhtoan) = '11';", nativeQuery = true)
    List<HoaDon> findDoanhThuByThang();

    @Query("SELECT hd from HoaDon hd where month(hd.ngaythanhtoan) between ?1 and ?2")
    List<HoaDon> findDoanhThuByQ(String month1, String month2);

    @Query(value = "SELECT * from hoadon where month(ngaythanhtoan) between '10' and '12';", nativeQuery = true)
    List<HoaDon> findDoanhThuByQuy();

    @Query("SELECT hd from HoaDon hd where year(hd.ngaythanhtoan) = ?1")
    List<HoaDon> findDoanhThuByYear(int year);

    @Query(value = "SELECT * from hoadon where year(ngaythanhtoan) = '2022';", nativeQuery = true)
    List<HoaDon> findDoanhThuByNam();
}
