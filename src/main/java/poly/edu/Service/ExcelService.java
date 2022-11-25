package poly.edu.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import poly.edu.DAO.GiayDAO;
import poly.edu.Entity.Giay;
import poly.edu.Helper.ExcelHelper;

@Service
public class ExcelService {
    @Autowired
    GiayDAO giayDAO;

    public void save(MultipartFile file) {
        try {
            List<Giay> giays = ExcelHelper.excelToTutorials(file.getInputStream());
            giayDAO.saveAll(giays);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Giay> getAllTutorials() {
        return giayDAO.findAll();
    }
}
