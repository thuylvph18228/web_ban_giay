package poly.edu.Service;

import poly.edu.Entity.LoaiGiay;

import java.util.List;

public interface LoaiGiayService {
    List<LoaiGiay> findAll();
    LoaiGiay findById(Integer id);
}
