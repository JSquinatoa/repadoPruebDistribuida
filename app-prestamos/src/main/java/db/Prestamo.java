package db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "prestamos")
@Getter
@Setter
@ToString
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pres_id")
    private Integer id;
    @Column(name = "pres_clienteId")
    private Integer clienteId;
    @Column(name = "pres_tasaInteres")
    private Float tasaInteres;
    @Column(name = "pres_montoTotal")
    private Float montoTotal;

    //Relaciones uno a muchos a pagos
    @OneToMany(mappedBy = "prestamo")
    private List<Pago> pagos;

}
