package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.HoaDon;
import poly.edu.Entity.ThongBao;

import java.util.List;

public interface ThongBaoDAO  extends JpaRepository<ThongBao, Integer> {

    @Query(" SELECT t FROM ThongBao t  WHERE t.trangthai  =0")
    List<ThongBao> findByThongBaoChuaXem();
    @Query(" SELECT t FROM ThongBao t  WHERE t.mahd  =?1")
    ThongBao findThongBaoByMahd(int mahd);
}
