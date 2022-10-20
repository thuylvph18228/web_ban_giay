package poly.edu.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "hinhthucthanhtoan")
@NoArgsConstructor
@AllArgsConstructor
public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mahttt")
    private int mahttt;

    @NotBlank(message = "Không được để trống tên ")
    @Column(name="ten")
    private String ten;
}
