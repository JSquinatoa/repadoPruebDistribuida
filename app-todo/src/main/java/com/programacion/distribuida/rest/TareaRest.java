package com.programacion.distribuida.rest;

import com.programacion.distribuida.client.UsuarioRestClient;
import com.programacion.distribuida.db.Tarea;
import com.programacion.distribuida.dto.TareaDto;
import com.programacion.distribuida.dto.TareaUsuarioDTO;
import com.programacion.distribuida.dto.UsuarioDto;
import com.programacion.distribuida.repo.TareaRepo;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;


@Path("/tareas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TareaRest {

    @Inject
    private TareaRepo tareaRepo;

    @RestClient
    @Inject
    UsuarioRestClient usuarioRestClient;

    @POST
    @Path("/")
    public Response guardarTarea (TareaDto tareaDto){
        // validar si existe ese usuario
        if (usuarioRestClient.getById(tareaDto.getUserId()) == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("El usuario no existe").build();
        }

        // crear la tarea

        Tarea t = new Tarea();
        t.setUserId(tareaDto.getUserId());
        t.setTitle(tareaDto.getTitle());
        t.setCompleted(tareaDto.getCompleted());

        tareaRepo.persist(t);

        return Response.status(Response.Status.CREATED).entity(tareaDto).build();

    }

    @GET
    @Path("/")
    public List<TareaUsuarioDTO> listarTodos (){
        return tareaRepo.listAll()
                .stream()
                .map(it -> {
                    TareaUsuarioDTO tudto = new TareaUsuarioDTO();

                    UsuarioDto udto = usuarioRestClient.getById(it.getUserId());
                    tudto.setName(udto.getName());
                    tudto.setUsername(udto.getUsername());

                    tudto.setTitle(it.getTitle());
                    tudto.setCompleted(it.getCompleted());

                    return tudto;
                }).toList();
    }

}
