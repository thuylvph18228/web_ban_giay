package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "giay")
@NoArgsConstructor
@AllArgsConstructor
public class Giay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mag")
    private int mag;

    @Column(name="ten")
    private String ten;

    @Column(name="gia")
    private int gia;

    @Column(name="soluong")
    private String soluong;

    @Column(name="trangthai")
    private String trangthai;

}
