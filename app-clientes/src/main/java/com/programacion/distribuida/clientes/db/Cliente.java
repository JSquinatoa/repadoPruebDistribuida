package com.programacion.distribuida.clientes.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clie_id")
    private Integer id;
    @Column(name = "clie_nombre")
    private String nombre;
    @Column(name = "clie_apellido")
    private String apellido;
    @Column(name = "clie_direccion")
    private String direccion;

    // Relaciones
    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;

}
