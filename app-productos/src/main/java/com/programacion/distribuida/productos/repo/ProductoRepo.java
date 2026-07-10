package com.programacion.distribuida.productos.repo;

import com.programacion.distribuida.productos.db.Producto;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ProductoRepo implements PanacheRepositoryBase<Producto, Integer> {
}
