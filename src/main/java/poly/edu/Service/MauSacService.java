package poly.edu.Service;

import poly.edu.Entity.MauSac;

import java.util.List;

public interface MauSacService {
    List<MauSac> findAll();
    MauSac findById(Integer id);
}
