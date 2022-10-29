package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;

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

//    @NotBlank(message = "Không được để trống tổng tiền")
//    @Min(value = 1, message = "Tổng tiền phải lớn hơn 0")
<<<<<<<<< Temporary merge branch 1
=========

>>>>>>>>> Temporary merge branch 2
//
//    @NotBlank(message = "Không được để trống số lượng")
//    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    @Column(name="soluong")
    private int soluong;

}