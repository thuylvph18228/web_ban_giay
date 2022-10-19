package poly.edu.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import poly.edu.DAO.MauSacDAO;
import poly.edu.Entity.MauSac;
import poly.edu.Service.MauSacService;

import java.util.List;

public class MauSacServiceImpl implements MauSacService {

    @Autowired
    MauSacDAO mausacdao;

    @Override
    public List<MauSac> findAll() {
        return mausacdao.findAll();
    }

    @Override
    public MauSac findById(Integer id) {
        return mausacdao.findById(id).get();
    }
}
