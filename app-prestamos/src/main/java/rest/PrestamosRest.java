package rest;

import dto.PagoDto;
import dto.PrestamoDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import repo.PagoRepo;
import repo.PrestamoRepo;

import java.util.List;
import java.util.SplittableRandom;

@Path("/prestamos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrestamosRest {

    @Inject
    private PrestamoRepo prestamoRepo;

    @Inject
    private PagoRepo pagoRepo;

    // listar todos los prestamos con los pagos requeridos

    @GET
    @Path("/{clienteId}")
    public List<PrestamoDto> ListarPrestamosPorIdCliente(@PathParam("clienteId") Integer clienteId) {

        return prestamoRepo.buscarPrestoPoridCliente(clienteId)
                .stream()
                .map(it -> PrestamoDto.builder()
                        .id(it.getId())
                        .clienteId(it.getClienteId())
                        .tasaInteres(it.getTasaInteres())
                        .montoTotal(it.getMontoTotal())
                        .pagosDto(pagoRepo.buscarPagosPrestamoPorId(it.getId())
                                .stream()
                                .map(it2 -> PagoDto.builder()
                                        .id(it2.getId())
                                        .montoPago(it2.getMontoPago())
                                        .fechaPago(it2.getFechaPago())
                                        .build()
                                ).toList()
                        )
                        .build()

                )
                .toList();
    }

    @GET
    @Path("/")
    public List<PrestamoDto> getall (){
        return prestamoRepo.listAll()
                .stream()
                .map(it -> PrestamoDto.builder()
                        .id(it.getId())
                        .clienteId(it.getClienteId())
                        .tasaInteres(it.getTasaInteres())
                        .montoTotal(it.getMontoTotal())
                        .pagosDto(pagoRepo.buscarPagosPrestamoPorId(it.getId())
                                .stream()
                                .map(it2 -> PagoDto.builder()
                                        .id(it2.getId())
                                        .montoPago(it2.getMontoPago())
                                        .fechaPago(it2.getFechaPago())
                                        .build()
                                ).toList()
                        )
                        .build()

                )
                .toList();
    }
}
