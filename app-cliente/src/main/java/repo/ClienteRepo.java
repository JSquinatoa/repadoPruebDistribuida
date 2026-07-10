package repo;

import db.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ClienteRepo implements PanacheRepositoryBase<Cliente, Integer> {

    public Cliente buscarPorCedula(String cedula) {
        return find("cedula = ?1", cedula).firstResult();
    }
}
