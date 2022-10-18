package poly.edu.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import poly.edu.DAO.GiayDAO;
import poly.edu.Entity.Giay;
import poly.edu.Service.GiayService;

import java.util.List;

public class GiayServiceImpl implements GiayService {

    @Autowired
    GiayDAO giaydao;

    @Override
    public List<Giay> findAll() {
        return giaydao.findAll();
    }

    @Override
    public Giay findById(Integer id) {
        return giaydao.findById(id).get();
    }
}
