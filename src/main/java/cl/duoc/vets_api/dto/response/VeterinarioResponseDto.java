/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de un veterinario registrado")
public class VeterinarioResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;

    @Schema(description = "Nombre completo", example = "Ana María González")
    private String nombreCompleto;

    @Schema(description = "Apellidos", example = "González Pérez")
    private String apellidos;

    @Schema(description = "RUT completo", example = "12345678-9")
    private String rut;

    @Schema(description = "Correo electrónico", example = "ana.gonzalez@vet.cl")
    private String email;

    @Schema(description = "Edad calculada", example = "36")
    private Integer edad;

    @Schema(description = "Número de registro profesional", example = "VET-123456")
    private String numeroRegistroProfesional;

    @Schema(description = "Horarios de trabajo del veterinario")
    private List<HorarioResponseDto> horarioVeterinario;
}
