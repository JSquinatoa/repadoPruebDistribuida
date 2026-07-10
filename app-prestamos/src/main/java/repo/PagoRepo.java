package repo;

import db.Pago;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class PagoRepo implements PanacheRepositoryBase<Pago, Integer> {
    public List<Pago> buscarPagosPrestamoPorId(Integer prestamoId) {
        return this.find("select pa from Pago pa where pa.prestamo.id = ?1 ",prestamoId).list();
    }
}
