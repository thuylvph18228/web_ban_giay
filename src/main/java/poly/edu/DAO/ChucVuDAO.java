package poly.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.Entity.ChucVu;

public interface ChucVuDAO extends JpaRepository<ChucVu, Integer> {
    @Query("SELECT e FROM ChucVu e WHERE e.macv = ?1")
    ChucVu findByMaCV(int macv);
}
