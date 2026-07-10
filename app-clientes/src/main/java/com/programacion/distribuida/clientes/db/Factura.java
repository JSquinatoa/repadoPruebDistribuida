package com.programacion.distribuida.clientes.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "facturas")
@Getter
@Setter
@ToString
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fact_id")
    private Integer id;
    @Column(name = "fact_producto_id")
    private Integer producto_id;
    @Column(name = "fact_precio")
    private Float precio;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "fact_cliente_id")
    private Cliente cliente;


}
