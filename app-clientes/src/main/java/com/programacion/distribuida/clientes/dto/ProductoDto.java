package com.programacion.distribuida.clientes.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

    private Integer id;
    private String nombre;
    private Float precio;
}
