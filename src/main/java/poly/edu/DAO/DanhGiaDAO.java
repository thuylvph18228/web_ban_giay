package poly.edu.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.DanhGia;

import java.util.List;

public interface DanhGiaDAO extends JpaRepository<DanhGia, Integer> {
    @Query("SELECT d FROM DanhGia d  where d.mag =?1")
    Page<DanhGia> findByDanhGia(Pageable mag, int pageable);
    @Query("SELECT d FROM DanhGia d  where d.loaidg =0 and d.mag=?1")
    List<DanhGia> findByDanhGia0(int mag);
    @Query("SELECT d FROM DanhGia d  where d.loaidg =0 ")
    List<DanhGia> findByDanhGia();
    @Query("SELECT d FROM DanhGia d  where d.loaidg =1 and d.mag=?1")
    List<DanhGia> findByDanhGia1(int mag);
    @Query("SELECT d FROM DanhGia d  where d.loaidg =2 and d.mag=?1")
    List<DanhGia> findByDanhGia2(int mag);
}
