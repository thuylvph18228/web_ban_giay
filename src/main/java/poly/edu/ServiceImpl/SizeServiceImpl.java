package poly.edu.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import poly.edu.DAO.SizeDAO;
import poly.edu.Entity.Size;
import poly.edu.Service.SizeService;

import java.util.List;

public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeDAO sizedao;

    @Override
    public List<Size> findAll() {
        return sizedao.findAll();
    }

    @Override
    public Size findById(Integer id) {
        return sizedao.findById(id).get();
    }
}
