package poly.edu.Service;

import poly.edu.Entity.ChiTietGiay;

import java.util.List;

public interface ChiTietGiayService {
    List<ChiTietGiay> findAll();
    ChiTietGiay findById(Integer id);
}
