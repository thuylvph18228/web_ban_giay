package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "nsx")
@NoArgsConstructor
@AllArgsConstructor

public class NSX implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mansx")
    private int mansx;
    
    @NotBlank(message = "Không được để trống")
    @Column(name="ten")
    private String ten;

}
