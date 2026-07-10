package com.programacion.distribuida.productos.rest;

import com.programacion.distribuida.productos.db.Producto;
import com.programacion.distribuida.productos.dto.ProductoDto;
import com.programacion.distribuida.productos.repo.ProductoRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoRest {

    // obtener todos los productos
    @Inject
    private ProductoRepo productoRepo;

    @GET
    @Path("/")
    public List<ProductoDto> getAll (){
        return productoRepo.listAll()
                .stream()
                .map(it -> ProductoDto.builder()
                        .id(it.getId())
                        .nombre(it.getNombre())
                        .precio(it.getPrecio())
                        .build()
                ).toList();
    }

    // obtener por id
    @GET
    @Path("/{id}")
    public Response buscarPorId (@PathParam("id") Integer id){
        return productoRepo.findByIdOptional(id)
                .map(it -> ProductoDto.builder()
                        .id(it.getId())
                        .nombre(it.getNombre())
                        .precio(it.getPrecio())
                        .build()
                ).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Transactional // Sin esto, Panache no te deja guardar en base de datos
    public Response crear(ProductoDto dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());

        productoRepo.persist(p);

        return Response.status(Response.Status.CREATED).entity(p).build();
    }
}
