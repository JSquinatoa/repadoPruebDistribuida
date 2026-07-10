package com.programacion.distribuida.clientes.rest;

import com.programacion.distribuida.clientes.db.Cliente;
import com.programacion.distribuida.clientes.dto.ClienteDto;
import com.programacion.distribuida.clientes.repo.ClienteRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteRest {

    @Inject
    private ClienteRepo clienteRepo;

    // listar todos
    @GET
    @Path("/")
    public List<ClienteDto> obtenerTodos (){
        return clienteRepo.listAll()
                .stream()
                .map(it -> ClienteDto.builder()
                           .id(it.getId())
                           .nombre(it.getNombre())
                           .apellido(it.getApellido())
                           .direccion(it.getDireccion())
                        .build()
                )
                .toList();
    }

    // listar por id
    @GET
    @Path("/{id}")
    public Response buscarorId (@PathParam("id") Integer id){
        return clienteRepo.findByIdOptional(id)
                .map(it -> ClienteDto.builder()
                        .id(it.getId())
                        .nombre(it.getNombre())
                        .apellido(it.getApellido())
                        .direccion(it.getDireccion())
                        .build()
                )
                .map(Response::ok)
                .orElse(Response.status(Response.Status.OK))
                .build();
    }

    @POST
    @Transactional // Sin esto, Panache no te deja guardar en base de datos
    public Response crear(ClienteDto dto) {
        Cliente c = new Cliente();
        c.setNombre(dto.getNombre());
        c.setApellido(dto.getApellido());
        c.setDireccion(dto.getDireccion());
        clienteRepo.persist(c);

        return Response.status(Response.Status.CREATED).entity(c).build();
    }
}
