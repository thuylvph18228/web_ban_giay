package poly.edu.Service;

import poly.edu.Entity.Giay;

import java.util.List;

public interface GiayService {
    List<Giay> findAll();
    Giay findById(Integer id);
}
