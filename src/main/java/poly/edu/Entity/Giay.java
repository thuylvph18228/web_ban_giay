package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@Table(name = "giay")
@NoArgsConstructor
@AllArgsConstructor

public class Giay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mag")
    private int mag;

    @NotBlank(message = "Không được để trống tên")
    @Column(name="ten")
    private String ten;

    @NotNull(message = "Không được để trống giá")
    @Min(value = 1, message = "Giá phải lớn hơn 0")
    @Column(name="gia")
    private int gia;

    @NotBlank(message = "Không được để trống ảnh")
    @Column(name="anh")
    private String anh;

    @NotBlank(message = "Không được để trống mô tả")
    @Column(name="mota")
    private String mota;

}
