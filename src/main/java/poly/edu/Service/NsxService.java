package poly.edu.Service;

import poly.edu.Entity.NSX;

import java.util.List;

public interface NsxService {
    List<NSX> findAll();
    NSX findById(Integer id);
}
