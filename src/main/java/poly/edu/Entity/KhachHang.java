package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotBlank(message = "Không được để trống địa chỉ")
    @Column(name="diachi")
    private String diachi;

    @NotBlank(message = "Không được để trống sđt")
    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message = "Sai định dạng")
    @Column(name="sdt")
    private String sdt;

    @NotBlank(message = "Không được để trống email")
    @Email(message = "Sai định dạng email")
    @Column(name="email")
    private String email;

    @Size(min=5, max=1000,message = "Mật khẩu quá yếu")
    @NotBlank(message = "Không được để trống mật khẩu")
    @Column(name="matkhau")
    private String matkhau;

}
