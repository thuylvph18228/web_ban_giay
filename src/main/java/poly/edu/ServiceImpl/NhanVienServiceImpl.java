package poly.edu.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import poly.edu.DAO.NhanVienDAO;
import poly.edu.Entity.NhanVien;
import poly.edu.Service.NhanVienService;

import java.util.List;

public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    NhanVienDAO nvdao;

    @Override
    public List<NhanVien> findAll() {
        return nvdao.findAll();
    }

    @Override
    public NhanVien findById(Integer id) {
        return nvdao.findById(id).get();
    }

    @Override
    public List<NhanVien> findByChucVuId(Integer id) {
        return null;
    }
}
