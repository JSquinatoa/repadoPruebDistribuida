package com.programacion.distribuida.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tareas")
@Getter
@Setter
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tare_id")
    private Integer id;
    @Column(name = "tare_title")
    private String title;
    @Column(name = "tare_completed")
    private Boolean completed;
    @Column(name = "tare_userId")
    private Integer userId;

}
