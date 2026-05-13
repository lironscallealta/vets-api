package cl.duoc.vets_api.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarioResponseDto {

    private Long id;
    private String numeroRegistroProfesional;
    private LocalDate egresoProfesional;
    private Boolean Escirujano;
    private List<HorarioResponseDto> horario;

}
