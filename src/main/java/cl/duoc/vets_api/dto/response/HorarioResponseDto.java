package cl.duoc.vets_api.dto.response;

import java.time.LocalTime;

import cl.duoc.vets_api.model.DiasSemana;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioResponseDto {

    private Long id;
    private DiasSemana dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

}
