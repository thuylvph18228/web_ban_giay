package poly.edu.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import poly.edu.DAO.ChucVuDAO;
import poly.edu.Entity.ChucVu;
import poly.edu.Service.ChucVuService;

import java.util.List;

public class ChucVuServiceImpl implements ChucVuService {

    @Autowired
    ChucVuDAO cvdao;

    @Override
    public List<ChucVu> findAll() {
        return cvdao.findAll();
    }

    @Override
    public ChucVu findById(Integer id) {
        return cvdao.findById(id).get();
    }
}
