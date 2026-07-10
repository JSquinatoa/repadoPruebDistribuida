package client;

import dto.PrestamoDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "app-prestamos")
@Path("/prestamos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PrestamoRestClient {

    @GET
    @Path("/{clienteId}")
    public List<PrestamoDto> ListarPrestamosPorIdCliente(@PathParam("clienteId") Integer clienteId);
}
