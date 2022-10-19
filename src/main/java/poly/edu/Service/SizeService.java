package poly.edu.Service;

import poly.edu.Entity.Size;

import java.util.List;

public interface SizeService {
    List<Size> findAll();
    Size findById(Integer id);
}
