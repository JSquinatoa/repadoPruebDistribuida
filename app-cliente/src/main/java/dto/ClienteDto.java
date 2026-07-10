package dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {

    private Integer id;
    private String cedula;
    private String email;
    // objeto que mde euvla los prestamos
    private List<PrestamoDto> prestamosDto;


}
