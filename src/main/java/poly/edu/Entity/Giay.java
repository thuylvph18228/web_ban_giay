package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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

    @NotBlank(message = "Không được để trống giá")
    @Min(value = 1, message = "Giá phải lớn hơn 0")
    @Column(name="gia")
    private double gia;

    @NotNull(message = "Không được để trống ảnh")
    @Column(name="anh")
    private String anh;

    @NotNull(message = "Không được để trống mô tả")
    @Column(name="mota")
    private String mota;
}
