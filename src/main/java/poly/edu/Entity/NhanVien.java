package poly.edu.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;


@Entity
@Data
@Table(name = "nhanvien")
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manv")
    private int manv;

    @Column(name="macv")
    private int macv;

    @NotBlank(message = "Không được để trống tên")
    @Column(name="ten")
    private String ten;

    @NotBlank(message = "Không được để trống ngày sinh")
    @Column(name="ngaysinh")
    private String ngaysinh;

    @NotBlank(message = "Không được để trống giới tính")
    @Column(name="gioitinh")
    private String gioitinh;


    @Email(message = "Sai định dạng email")
    @Column(name="email")
    private String email;

    @NotBlank(message = "Không được để trống địa chỉ")
    @Column(name="diachi")
    private String diachi;

    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message = "Sai định dạng")
    @Column(name="sdt")
    private String sdt;

    @Size(min=5, max=1000,message = "Mật khẩu quá yếu")
    @NotBlank(message = "Không được để trống mật khẩu")
    @Column(name="matkhau")
    private String matkhau;

    @NotNull
    @Column(name="trangthai")
    private int trangthai;
}
