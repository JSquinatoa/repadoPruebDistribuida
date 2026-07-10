package repo;

import db.Prestamo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class PrestamoRepo implements PanacheRepositoryBase<Prestamo, Integer> {

    // buscar los prestamos de un cliente
    public List<Prestamo> buscarPrestoPoridCliente (Integer clienteId){
        return this.find("select pr from Prestamo pr where pr.clienteId = ?1", clienteId).list();
    }
}
