package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.Giay;
import poly.edu.Entity.Nsx;

import java.util.List;

public interface NsxDAO extends JpaRepository<Nsx, Integer> {
    @Query("SELECT e FROM Nsx e WHERE e.ten = ?1")
    List<Giay> findByTennsx(String tennsx);
}
