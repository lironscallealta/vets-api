package cl.duoc.vets_api.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarioRequestDto {

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @Size(max = 50)
    private String segundoNombre;

    @NotBlank
    @Size(max = 50)
    private String apellido;

    @Size(max = 50)
    private String segundoApellido;

    @NotBlank
    @Size(max = 22)
    private String rut;

    @NotBlank
    @Size(max = 1)
    private String dv;

    @NotBlank
    @Email
    @Size(max = 50)
    private String email;

    @Size(max = 20)
    private String telefonoCelular;

    @NotNull
    @Past
    private LocalDate fechaNacimiento;

    @NotBlank
    @Size(min = 8, max = 12)
    private String numeroRegistroProfesional;

    @Past
    private LocalDate egresoProfesional;

    @NotNull
    private Boolean escirujano;
}
