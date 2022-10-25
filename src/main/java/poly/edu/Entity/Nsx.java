package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@Table(name = "nsx")
@NoArgsConstructor
@AllArgsConstructor

public class Nsx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mansx")
    private int mansx;

    @NotBlank(message = "Không được để trống nhà sản xuất")
    @Column(name="ten")
    private String ten;

}
