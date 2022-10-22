package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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

    @NotNull(message = "Không được để trống")
    @Min(value = 1, message = "Giá phải lớn hơn 0")
    @Column(name="gia")
    private double gia;

    @NotNull(message = "Không được để trống")
    @Min(value = 1, message = "Giá phải lớn hơn 0")
    @Column(name="soluong")
    private int soluong;

    @Column(name="trangthai")
    private int trangthai;
}