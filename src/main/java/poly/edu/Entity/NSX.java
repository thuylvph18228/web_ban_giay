package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Table(name = "nsx")
@NoArgsConstructor
@AllArgsConstructor

public class NXS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mas")
    private int mas;
    @Column(name="ten")
    private String ten;

}
