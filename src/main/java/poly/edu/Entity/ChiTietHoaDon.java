package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "chitiethoadon")
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="macthd")
    private int macthd;

    @Column(name="mactg")
    private int mactg;

    @Column(name="mahd")
    private int mahd;

//    @NotNull(message = "Không được để trống số lượng")
//    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    @Column(name="soluong")
    private int soluong;

}