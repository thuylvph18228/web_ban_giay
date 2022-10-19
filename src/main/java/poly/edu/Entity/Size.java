package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "size")
@NoArgsConstructor
@AllArgsConstructor

public class Size implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mas")
    private int mas;
    
    @NotBlank(message = "Không được để trống")
    @Column(name="ten")
    private String ten;

}
