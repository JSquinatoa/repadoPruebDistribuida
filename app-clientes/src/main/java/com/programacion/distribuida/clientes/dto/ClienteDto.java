package com.programacion.distribuida.clientes.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;
}
