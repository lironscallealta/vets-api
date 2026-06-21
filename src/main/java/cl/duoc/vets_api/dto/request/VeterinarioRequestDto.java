/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para registrar o actualizar un veterinario")
public class VeterinarioRequestDto {

    @NotBlank
    @Schema(description = "Primer nombre", example = "Ana", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank
    @Schema(description = "Segundo nombre", example = "María", requiredMode = Schema.RequiredMode.REQUIRED)
    private String segundoNombre;

    @NotBlank
    @Schema(description = "Primer apellido", example = "González", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellido;

    @NotBlank
    @Schema(description = "Segundo apellido", example = "Pérez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String segundoApellido;

    @NotBlank
    @Size(max = 22)
    @Schema(
            description = "RUT sin dígito verificador",
            example = "12345678",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String rut;

    @NotBlank
    @Size(min = 1, max = 1)
    @Schema(description = "Dígito verificador del RUT", example = "9", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dv;

    @NotBlank
    @Email
    @Schema(
            description = "Correo electrónico",
            example = "ana.gonzalez@vet.cl",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank
    @Size(max = 20)
    @Schema(description = "Teléfono celular", example = "+56912345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String telefonoCelular;

    @NotNull
    @Past
    @Schema(description = "Fecha de nacimiento", example = "1990-03-15", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fechaNacimiento;

    @NotBlank
    @Size(min = 8, max = 12)
    @Schema(
            description = "Número de registro profesional",
            example = "VET-123456",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String numeroRegistroProfesional;

    @Past
    @Schema(description = "Fecha de egreso profesional", example = "2015-12-01")
    private LocalDate egresoProfesional;

    @NotNull
    @Schema(
            description = "Indica si puede operar en la clínica",
            example = "true",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean puedeOperar;

    @NotNull
    @Schema(description = "IDs de horarios asociados", example = "[1, 2]", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Long> horarioVeterinario;
}
