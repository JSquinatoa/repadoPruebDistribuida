package com.programacion.distribuida.repo;

import com.programacion.distribuida.db.Tarea;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TareaRepo implements PanacheRepositoryBase<Tarea, Integer> {
}
