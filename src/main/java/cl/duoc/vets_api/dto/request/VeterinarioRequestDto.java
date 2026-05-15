package cl.duoc.vets_api.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NotBlank
@AllArgsConstructor
public class VeterinarioRequestDto {

    @NotBlank
    @Size(min = 8, max = 12)
    private String numeroRegistroProfesional;

    @Past
    private LocalDate egresoProfesional;

    @NotNull
    private Boolean escirujano;

    @NotNull
    private List<Long> idHorarios; // cuando trabaja cada veterinario
}
