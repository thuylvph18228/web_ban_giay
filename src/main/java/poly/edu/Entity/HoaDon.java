package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

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

    @Column(name="trahang")
    private int trahang;

    @Column(name="danhgia")
    private int danhgia;

    @Column(name="trangthaidh")
    private int trangthaidh;

    @Column(name="trangthaihd")
    private int trangthaihd;


    @Column(name="tongtien")
    private int tongtien;


    @Column(name="danhgia")
    private int danhgia;
}
