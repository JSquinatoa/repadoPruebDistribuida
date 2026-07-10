package com.programacion.distribuida.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TareaUsuarioDTO {

    private String name;
    private String username;
    private String title;
    private Boolean completed;

}
