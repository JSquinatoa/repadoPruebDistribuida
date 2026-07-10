package rest;

import client.PrestamoRestClient;
import db.Cliente;
import dto.ClienteDto;
import dto.PrestamoDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import repo.ClienteRepo;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteRest {

    @Inject
    private ClienteRepo clienteRepo;

    @RestClient
    @Inject
    PrestamoRestClient prestamoRestClient;

    // metodo para listar prestamos de un cliente
    @GET
    @Path("/{cedula}")
    public Response buscarPrestamosPorCedua(@PathParam("cedula") String cedula){

        Cliente c = clienteRepo.buscarPorCedula(cedula);
        var pdto = prestamoRestClient.ListarPrestamosPorIdCliente(c.getId());
        ClienteDto cdto = ClienteDto.builder()
                .id(c.getId())
                .cedula(c.getCedula())
                .email(c.getEmail())
                .prestamosDto(pdto)
                .build();
        return Response.status(Response.Status.OK).entity(cdto).build();

    }

}
