package com.programacion.distribuida.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TareaDto {

    private Integer id;
    private String title;
    private Boolean completed;
    private Integer userId;
}
