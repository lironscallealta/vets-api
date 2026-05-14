package cl.duoc.vets_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoResponseDto {

    private Long id;
    private String horarioTurno; // dia

}
