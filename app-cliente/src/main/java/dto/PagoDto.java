package dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagoDto {

    private Integer id;
    private Float montoPago;
    private Date fechaPago;
    private Integer prestamoId;

}
