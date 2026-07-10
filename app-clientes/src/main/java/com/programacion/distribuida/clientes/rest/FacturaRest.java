package com.programacion.distribuida.clientes.rest;

import com.programacion.distribuida.clientes.client.ProductoRestClient;
import com.programacion.distribuida.clientes.db.Cliente;
import com.programacion.distribuida.clientes.db.Factura;
import com.programacion.distribuida.clientes.dto.FacturaDto;
import com.programacion.distribuida.clientes.dto.ProductoDto;
import com.programacion.distribuida.clientes.repo.ClienteRepo;
import com.programacion.distribuida.clientes.repo.FacturaRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/facturas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FacturaRest {

    @Inject
    private FacturaRepo facturaRepo;

    @Inject
    private ClienteRepo clienteRepo;

    @RestClient
    @Inject
    ProductoRestClient productoRestClient;

    // obtener todas las facturas
    @GET
    @Path("/")
    public List<FacturaDto> obtenerTodas (){
        return facturaRepo.listAll()
                .stream()
                .map(it -> FacturaDto.builder()
                        .id(it.getId())
                        .producto_id(it.getProducto_id())
                        .precio(it.getPrecio())
                        .cliente_id(it.getCliente().getId())
                        .build()
                ).toList();
    }

    // obtener factura por id

    @GET
    @Path("/{id}")
    public Response obtenerPorId (@PathParam("id") Integer id){
        return facturaRepo.findByIdOptional(id)
                .map(it -> FacturaDto.builder()
                        .id(it.getId())
                        .producto_id(it.getProducto_id())
                        .precio(it.getPrecio())
                        .cliente_id(it.getCliente().getId())
                        .build()
                ).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();

    }

    @POST
    @Transactional
    public Response crearFactura(FacturaDto dto) {

        // 1. Buscamos si el Cliente existe en nuestra propia Base de Datos
        Cliente cliente = clienteRepo.findById(dto.getCliente_id());
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("El cliente no existe").build();
        }
        // 2. Usamos el RestClient para preguntar a la OTRA aplicación por el Producto
        ProductoDto productoExtraido;
        try {
            productoExtraido = productoRestClient.buscarPorId(dto.getProducto_id());
        } catch (Exception e) {
            // Si da error (ej. 404), significa que el producto no existe en el otro server
            return Response.status(Response.Status.NOT_FOUND).entity("El producto no existe en el catálogo").build();
        }
        // 3. Si ambos existen, armamos la Factura
        Factura factura = new Factura();
        factura.setCliente(cliente); // ¡La magia de la relación @ManyToOne!
        factura.setProducto_id(productoExtraido.getId());
        factura.setPrecio(productoExtraido.getPrecio()); // Le cobramos el precio oficial del otro server
        // 4. Guardamos
        facturaRepo.persist(factura);
        return Response.status(Response.Status.CREATED).entity("Factura creada con éxito!").build();
    }
}
