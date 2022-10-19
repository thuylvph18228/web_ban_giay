package poly.edu.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import poly.edu.DAO.LoaiGiayDAO;
import poly.edu.Entity.LoaiGiay;
import poly.edu.Service.LoaiGiayService;

import java.util.List;

public class LoaiGiayServiceImpl implements LoaiGiayService {

    @Autowired
    LoaiGiayDAO loaigiaydao;

    @Override
    public List<LoaiGiay> findAll() {
        return loaigiaydao.findAll();
    }

    @Override
    public LoaiGiay findById(Integer id) {
        return loaigiaydao.findById(id).get();
    }
}
