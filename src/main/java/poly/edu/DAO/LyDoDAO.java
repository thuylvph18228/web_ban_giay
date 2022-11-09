package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.Entity.LyDoTraHang;



public interface LyDoDAO extends JpaRepository<LyDoTraHang, Integer> {
}
