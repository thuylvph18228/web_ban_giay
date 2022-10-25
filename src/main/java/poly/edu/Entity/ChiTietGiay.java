package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

//    @NotBlank(message = "Không được để trống số lượng")
    @Column(name="soluong")
    private int soluong;

}
