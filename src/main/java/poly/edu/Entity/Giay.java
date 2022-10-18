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
    
    @NotBlank(message = "Không được để trống")
    @Column(name="ten")
    private String ten;

    @NotNull(message = "Không được để trống")
    @Min(value = 1, message = "Giá phải lớn hơn 0")
    @Column(name="gia")
    private double gia;

    @NotNull(message = "Không được để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    @Column(name="soluong")
    private int soluong;

    @Column(name="trangthai")
    private int trangthai;

}
