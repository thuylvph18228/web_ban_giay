package poly.edu.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "lydotrahang")
@NoArgsConstructor
@AllArgsConstructor
public class LyDoTraHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mald")
    private int mald;

    @Column(name="ten")
    private String ten;
}
