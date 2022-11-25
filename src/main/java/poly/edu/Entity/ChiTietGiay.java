package poly.edu.Entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@Table(name = "chitietgiay")
@NoArgsConstructor
@AllArgsConstructor

public class ChiTietGiay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mactg")
    private int mactg;

    @Column(name="mas")
    private int mas;

    @Column(name="mansx")
    private int mansx;

    @Column(name="malg")
    private int malg;

    @Column(name="mag")
    private int mag;

    @NotNull()
    @Min(value = 1, message = "Giá phải lớn hơn 0")    @Column(name="soluong")
    private int soluong;

}
