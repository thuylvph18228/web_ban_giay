package poly.edu.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.HoaDon;
import poly.edu.Entity.Size;
import poly.edu.Entity.TraHang;

import java.util.List;


public interface TraHangDAO extends JpaRepository<TraHang, Integer> {
    @Query("SELECT t FROM TraHang t where t.makh  =?1")
    List<TraHang> lichsuTraHang(int makh);
    @Query("SELECT t FROM TraHang t  where t.trangthai =0 and t.xacnhan=0")
    Page<TraHang> trahangcanxuly(Pageable pageable);

    @Query("SELECT t FROM TraHang t where t.xacnhan =1 or  t.xacnhan = 2 and t.trangthai=1")
    Page<TraHang> trahangdaxuly(Pageable pageable);
}
