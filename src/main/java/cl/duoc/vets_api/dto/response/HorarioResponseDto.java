package cl.duoc.vets_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioResponseDto {

    private Long id;
    private String dia;
    private TurnoResponseDto turno;

}
