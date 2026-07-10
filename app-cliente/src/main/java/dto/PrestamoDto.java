package dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrestamoDto {

    private Integer id;
    private Integer clienteId;
    private Float tasaInteres;
    private Float montoTotal;
    private List<PagoDto> pagosDto;

}
