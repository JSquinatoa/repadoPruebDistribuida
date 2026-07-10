package com.programacion.distribuida.clientes.repo;

import com.programacion.distribuida.clientes.db.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ClienteRepo implements PanacheRepositoryBase<Cliente, Integer> {
}
