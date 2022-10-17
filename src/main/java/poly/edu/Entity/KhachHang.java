package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Data
@Table(name = "khachhang")
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="makh")
    private int makh;

    @NotBlank(message = "Không được để trống tên")
    @Column(name="ten")
    private String ten;

    @NotBlank(message = "Không được để trống ngày sinh")
    @Column(name="ngaysinh")
    private String ngaysinh;

    @NotBlank(message = "Không được để trống email")
    @Email(message = "Sai định dạng email")
    @Column(name="email")
    private String email;

    @NotBlank(message = "Không được để trống địa chỉ")
    @Column(name="diachi")
    private String diachi;

    @NotBlank(message = "Không được để trống sđt")
    @Column(name="sdt")
    private String sdt;

    @NotBlank(message = "Không được để trống mật khẩu")
    @Column(name="matkhau")
    private String matkhau;
}
