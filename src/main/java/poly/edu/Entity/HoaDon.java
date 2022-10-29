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

<<<<<<<<< Temporary merge branch 1
    @Column(name="trangthaidh")
    private String trangthaidh;

    @Column(name="trangthaihd")
    private String trangthaihd;
=========
    @Column(name="trangthaihd")
    private int trangthaihd;

    @Column(name="trangthaidh")
    private int trangthaidh;

>>>>>>>>> Temporary merge branch 2
    @Column(name="tongtien")
    private int tongtien;
}
