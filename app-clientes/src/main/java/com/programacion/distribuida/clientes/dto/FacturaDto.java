package com.programacion.distribuida.clientes.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDto {

    private Integer id;
    private Integer producto_id;
    private Float precio;
    private Integer cliente_id;
}
