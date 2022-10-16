package poly.edu.Service;

import poly.edu.Entity.ChucVu;

import java.util.List;

public interface ChucVuService {
    List<ChucVu> findAll();
    ChucVu findById(Integer id);
}
