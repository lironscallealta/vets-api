/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Formato estándar de error de la API")
public class DtoApiError {

    @Schema(description = "Fecha del error", example = "2026-06-21")
    private LocalDate timestamp;

    @Schema(description = "Código HTTP", example = "404")
    private int status;

    @Schema(description = "Nombre del error HTTP", example = "Not Found")
    private String error;

    @Schema(description = "Mensaje descriptivo", example = "Veterinario no encontrado con id: 99")
    private String message;

    @Schema(description = "Ruta donde ocurrió el error", example = "/api/v1/vets/99")
    private String path;

    @Schema(description = "Clase de excepción Java", example = "ResourceNotFoundException")
    private String claseException;
}
