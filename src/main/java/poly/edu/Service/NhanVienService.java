package poly.edu.Service;

import poly.edu.Entity.NhanVien;

import java.util.List;

public interface NhanVienService {
    List<NhanVien> findAll();

    NhanVien findById(Integer id);

    List<NhanVien> findByChucVuId(Integer id);
}
