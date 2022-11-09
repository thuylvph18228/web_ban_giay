package poly.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "trahang")
@NoArgsConstructor
@AllArgsConstructor
public class TraHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="math")
    private int math;

    @Column(name="makh")
    private int makh;

    @Column(name="mahd")
    private int mahd;

    @Column(name="manv")
    private int manv;

    @Column(name="mald")
    private int mald;

    @Column(name="ghichi")
    private String ghichu;

    @Column(name="xacnhan")
    private int xacnhan;

    @Column(name="trangthai")
    private int trangthai;



}