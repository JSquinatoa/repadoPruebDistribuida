package com.programacion.distribuida.clientes.client;

import com.programacion.distribuida.clientes.dto.ProductoDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "productos-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/productos")
public interface ProductoRestClient {

    @GET
    @Path("/{id}")
    ProductoDto buscarPorId (@PathParam("id") Integer id);

}
