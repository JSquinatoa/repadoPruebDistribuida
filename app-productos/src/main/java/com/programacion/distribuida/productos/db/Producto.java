package com.programacion.distribuida.productos.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "productos")
@Getter
@Setter
@ToString
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Integer id;
    @Column(name = "prod_nombre")
    private String nombre;
    @Column(name = "prod_precio")
    private Float precio;


}
