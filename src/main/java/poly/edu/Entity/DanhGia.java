package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "danhgia")
@NoArgsConstructor
@AllArgsConstructor
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="madg")
    private int madg;

    @Column(name="makh")
    private int makh;

    @Column(name="mag")
    private int mag;

    @Column(name="loaidanhgia")
    private int loaidg;

    @Column(name="mota")
    private String mota;


}
