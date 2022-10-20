package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull()
    @Size(min = 1, message = "Không để trống")
    @Column(name="ten")
    private String ten;

    @NotNull()
    @Column(name="ngaysinh")
    private Date ngaysinh;

    @NotNull()
    @Size(min = 1, message = "Không để trống")
    @Column(name="gioitinh")
    private String gioitinh;

    @NotNull()
    @Size(min = 1, message = "Không để trống")
    @Email(message = "Nhập đúng định dạng email")
    @Column(name="email")
    private String email;

    @NotNull()
    @Size(min = 1, message = "Không để trống")
    @Column(name="diachi")
    private String diachi;

    @NotNull()
    @Size(min = 1, message = "Không để trống")
    @Column(name="sdt")
    private String sdt;

    @NotNull()
    @Size(min = 1, message = "Không để trống")
    @Column(name="matkhau")
    private String matkhau;

    @Column(name="trangthai")
    private int trangthai;
}
