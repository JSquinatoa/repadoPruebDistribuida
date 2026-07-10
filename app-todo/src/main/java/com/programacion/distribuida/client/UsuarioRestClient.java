package com.programacion.distribuida.client;

import com.programacion.distribuida.dto.UsuarioDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "usuarios-api")
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UsuarioRestClient {

    @GET
    @Path("/{userId}")
    public UsuarioDto getById (@PathParam("userId") Integer userId);

}
