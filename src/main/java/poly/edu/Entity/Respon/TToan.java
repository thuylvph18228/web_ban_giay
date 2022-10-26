package poly.edu.Entity.Respon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TToan {
    @NotBlank(message = "Không được để trống tên")
    private String ten;

    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message = "Sai định dạng")
    @javax.validation.constraints.NotBlank(message = "Không được để trống sđt")
    private String sdt;

    @NotBlank(message = "Không được để trống địa chỉ")
    private String diachi;

    private int httt;
}
