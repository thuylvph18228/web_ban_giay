package poly.edu.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import poly.edu.DAO.ChiTietGiayDAO;
import poly.edu.Entity.ChiTietGiay;
import poly.edu.Service.ChiTietGiayService;

import java.util.List;

public class ChiTetGiayServiceImpl implements ChiTietGiayService {

    @Autowired
    ChiTietGiayDAO ctgdao;

    @Override
    public List<ChiTietGiay> findAll() {
        return ctgdao.findAll();
    }

    @Override
    public ChiTietGiay findById(Integer id) {
        return ctgdao.findById(id).get();
    }
}
