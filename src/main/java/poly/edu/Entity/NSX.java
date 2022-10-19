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

public class NSX {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mansx")
    private int mas;

    @NotBlank(message = "Không được để trống tên")
    @Column(name="ten")
    private String ten;

}
