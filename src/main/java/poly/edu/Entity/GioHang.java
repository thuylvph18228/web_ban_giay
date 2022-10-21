package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
@Table(name = "giohang")
@NoArgsConstructor
@AllArgsConstructor
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="magh")
    private int magh;

    @Column(name="makh")
    private int makh;

    @Column(name="mag")
    private int mag;

   // @NotBlank(message = "Không được để trống ngày tạo")
    @Column(name="ngaytao")
    private String ngaytao;

    @NotBlank(message = "Không được để trống địa chỉ")
    @Column(name="diachi")
    private String diachi;

   @NotBlank(message = "Không được để trống sđt")
    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message = "Sai định dạng")
   @Column(name="sdt")
    private String sdt;

//    @NotBlank(message = "Không được để trống địa tên người nhận")
    private String tennguoinhan;

   @Min (value =0,message = "Số lượng phải lớn hơn 0")
    @Column(name="soluong")
    private int soluong;

    @Column(name="trangthai")
    private int trangthai;

}
