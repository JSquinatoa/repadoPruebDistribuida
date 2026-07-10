package db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@ToString
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pago_id")
    private Integer id;
    @Column(name = "pago_montoPago")
    private Float montoPago;
    @Column(name = "pago_fechaPago")
    private Date fechaPago;

    // relacion de uno a muchos
    @ManyToOne
    @JoinColumn(name = "pago_prestamo_id")
    private Prestamo prestamo;
}
