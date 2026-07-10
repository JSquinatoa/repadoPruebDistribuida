package com.programacion.distribuida.clientes.repo;

import com.programacion.distribuida.clientes.db.Factura;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class FacturaRepo implements PanacheRepositoryBase<Factura, Integer> {

}
