package poly.edu.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import poly.edu.DAO.MauSacDAO;
import poly.edu.DAO.NsxDAO;
import poly.edu.Entity.MauSac;
import poly.edu.Entity.NSX;
import poly.edu.Service.MauSacService;
import poly.edu.Service.NsxService;

import java.util.List;

public class NsxServiceImpl implements NsxService {

    @Autowired
    NsxDAO nsxdao;

    @Override
    public List<NSX> findAll() {
        return nsxdao.findAll();
    }

    @Override
    public NSX findById(Integer id) {
        return nsxdao.findById(id).get();
    }
}
