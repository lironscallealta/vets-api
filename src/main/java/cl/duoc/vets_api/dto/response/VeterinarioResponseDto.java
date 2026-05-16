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
    private String nombre;
    private String segundoNombre;
    private String apellido;
    private String segundoApellido;
    private String rut;
    private String dv;
    private String email;
    private String telefonoCelular;
    private LocalDate fechaNacimiento;
    private String numeroRegistroProfesional;
    private LocalDate egresoProfesional;
    private Boolean escirujano;
    private List<HorarioResponseDto> horarioVeterinario;

    public void setHorario(List<HorarioResponseDto> horarioVeterinarioLista) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHorario'");
    }

}
