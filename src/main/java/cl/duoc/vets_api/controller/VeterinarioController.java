/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.controller;

import cl.duoc.vets_api.dto.DtoApiError;
import cl.duoc.vets_api.dto.request.VeterinarioRequestDto;
import cl.duoc.vets_api.dto.response.VeterinarioResponseDto;
import cl.duoc.vets_api.service.VeterinarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
@Tag(name = "Veterinarios", description = "Operaciones CRUD del dominio veterinarios.")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @PostMapping
    @Operation(summary = "Registrar veterinario", description = "Crea un nuevo registro de veterinario.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Veterinario registrado correctamente",
                content =
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = VeterinarioResponseDto.class))),
        @ApiResponse(
                responseCode = "400",
                description = "Datos de entrada inválidos",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public ResponseEntity<VeterinarioResponseDto> registrarVeterinario(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                            description = "Datos del veterinario a registrar",
                            required = true,
                            content =
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = VeterinarioRequestDto.class),
                                            examples = @ExampleObject(name = "Ejemplo de veterinario", value = """
                                                                    {
                                                                      "nombre": "Ana",
                                                                      "segundoNombre": "María",
                                                                      "apellido": "González",
                                                                      "segundoApellido": "Pérez",
                                                                      "rut": "12345678",
                                                                      "dv": "9",
                                                                      "email": "ana.gonzalez@vet.cl",
                                                                      "telefonoCelular": "+56912345678",
                                                                      "fechaNacimiento": "1990-03-15",
                                                                      "numeroRegistroProfesional": "VET-123456",
                                                                      "egresoProfesional": "2015-12-01",
                                                                      "puedeOperar": true,
                                                                      "horarioVeterinario": [1, 2]
                                                                    }
                                                                    """)))
                    @Valid
                    @RequestBody
                    VeterinarioRequestDto veterinarioRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(veterinarioService.registrarVeterinario(veterinarioRequest));
    }

    @GetMapping
    @Operation(summary = "Todos los veterinarios", description = "Obtiene una lista con todos los veterinarios")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Listado obtenido correctamente",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public ResponseEntity<List<VeterinarioResponseDto>> buscarTodos() {
        return ResponseEntity.ok(veterinarioService.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar veterinario por ID", description = "Obtiene un veterinario por su identificador.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Veterinario encontrado",
                content =
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = VeterinarioResponseDto.class))),
        @ApiResponse(
                responseCode = "404",
                description = "Veterinario no encontrado",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public ResponseEntity<VeterinarioResponseDto> consultarVeterinarioId(@PathVariable Long id) {
        return ResponseEntity.ok(veterinarioService.consultarVeterinarioId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar veterinario", description = "Actualiza los datos de un veterinario existente.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Veterinario actualizado",
                content =
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = VeterinarioResponseDto.class))),
        @ApiResponse(
                responseCode = "400",
                description = "Datos de entrada inválidos",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "404",
                description = "Veterinario no encontrado",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public ResponseEntity<VeterinarioResponseDto> actualizarVeterinario(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                            description = "Nuevos datos del veterinario",
                            required = true,
                            content =
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = VeterinarioRequestDto.class),
                                            examples = @ExampleObject(name = "Ejemplo de actualización", value = """
                                                                    {
                                                                      "nombre": "Ana",
                                                                      "segundoNombre": "María",
                                                                      "apellido": "González",
                                                                      "segundoApellido": "Pérez",
                                                                      "rut": "12345678",
                                                                      "dv": "9",
                                                                      "email": "ana.actualizada@vet.cl",
                                                                      "telefonoCelular": "+56987654321",
                                                                      "fechaNacimiento": "1990-03-15",
                                                                      "numeroRegistroProfesional": "VET-123456",
                                                                      "egresoProfesional": "2015-12-01",
                                                                      "puedeOperar": true,
                                                                      "horarioVeterinario": [1, 3]
                                                                    }
                                                                    """)))
                    @Valid
                    @RequestBody
                    VeterinarioRequestDto veterinarioRequest) {
        return ResponseEntity.ok(veterinarioService.actualizarVeterinario(id, veterinarioRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar veterinario", description = "Elimina un veterinario por su identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Veterinario eliminado correctamente"),
        @ApiResponse(
                responseCode = "404",
                description = "Veterinario no encontrado",
                content = @Content(schema = @Schema(implementation = DtoApiError.class))),
        @ApiResponse(
                responseCode = "401",
                description = "Token JWT ausente o inválido",
                content = @Content(schema = @Schema(implementation = DtoApiError.class)))
    })
    public ResponseEntity<Void> eliminarVeterinario(@PathVariable Long id) {
        veterinarioService.eliminarVeterinario(id);
        return ResponseEntity.noContent().build();
    }
}
