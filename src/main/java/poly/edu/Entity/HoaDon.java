package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "hoadon")
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mahd")
    private int mahd;


    @Column(name="manv")
    private int manv;


    @Column(name="makh")
    private int makh;

    @Column(name="mahttt")
    private int mahttt;

    @Column(name="ngaytao")
    private String ngaytao;

    @Column(name="ngaythanhtoan")
    private String ngaythanhtoan;

    @Column(name="ngayship")
    private String ngayship;

    @Column(name="ngaynhan")
    private String ngaynhan;

    @Column(name="tennguoinhan")
    private String tennguoinhan;

//    @NotBlank(message = "Không được để trống địa chỉ")
    @Column(name="diachi")
    private String diachi;

    @Column(name="sdt")
    private String sdt;
}
