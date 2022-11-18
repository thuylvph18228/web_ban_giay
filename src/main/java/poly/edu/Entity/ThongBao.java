package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "thongbao")
@NoArgsConstructor
@AllArgsConstructor
public class ThongBao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="matb")
    private int matb;

    @Column(name="makh")
    private int makh;

    @Column(name="mahd")
    private int mahd;

    @Column(name="trangthai")
    private int trangthai;


    @Column(name="ngaytao")
    private String ngaytao;

    @Column(name="ngayxem")
    private String ngayxem;

    @Column(name="mota")
    private String mota;
}
