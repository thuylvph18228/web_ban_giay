package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "chucvu")
@NoArgsConstructor
@AllArgsConstructor
public class ChucVu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="macv")
    private int macv;

    @NotNull(message = "Không được để trống")
    @Column(name="tencv")
    private String tencv;
}
