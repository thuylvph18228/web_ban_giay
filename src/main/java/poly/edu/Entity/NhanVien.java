package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "nhanvien")
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manv")
    private int manv;
    @Column(name="macv")
    private int macv;
    @Column(name="ten")
    private String ten;
    @Column(name="ngaysinh")
    private Date ngaysinh;
    @Column(name="gioitinh")
    private String gioitinh;
    @Column(name="email")
    private String email;
    @Column(name="diachi")
    private String diachi;
    @Column(name="sdt")
    private String sdt;
    @Column(name="matkhau")
    private String matkhau;
    @Column(name="trangthai")
    private int trangthai;
}
