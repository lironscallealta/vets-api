/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.controller;

import cl.duoc.vets_api.dto.request.VeterinarioRequestDto;
import cl.duoc.vets_api.dto.response.VetScheduleResponse;
import cl.duoc.vets_api.dto.response.VeterinarioResponseDto;
import cl.duoc.vets_api.service.VeterinarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
@Tag(name = "Veterinarios", description = "Operaciones CRUD del dominio veterinarios.")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @GetMapping("/schedules")
    @Operation(
            summary = "Consular horas de todos los veterinarios por fecha",
            description = "Retorna el horario de trabajo de todos los veterinarios para una fecha")
    public ResponseEntity<List<VetScheduleResponse>> getAllSchedulesByDate(
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReq) {
        return ResponseEntity.ok(veterinarioService.consultarHorariosPorDia(dateReq));
    }

    @PostMapping
    @Operation(summary = "Registrar veterinario", description = "Crea un nuevo registro de veterinario.")
    public ResponseEntity<VeterinarioResponseDto> registrarVeterinario(
            @Valid @RequestBody VeterinarioRequestDto veterinarioRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(veterinarioService.registrarVeterinario(veterinarioRequest));
    }

    @GetMapping
    @Operation(summary = "Todos los veterinarios", description = "Obtiene una lista con todos los veterinarios")
    public ResponseEntity<List<VeterinarioResponseDto>> buscarTodos() {
        return ResponseEntity.ok(veterinarioService.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar veterinario por ID", description = "Obtiene un veterinario por su identificador.")
    public ResponseEntity<VeterinarioResponseDto> consultarVeterinarioId(@PathVariable Long id) {
        return ResponseEntity.ok(veterinarioService.consultarVeterinarioId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar veterinario", description = "Actualiza los datos de un veterinario existente.")
    public ResponseEntity<VeterinarioResponseDto> actualizarVeterinario(
            @PathVariable Long id, @Valid @RequestBody VeterinarioRequestDto veterinarioRequest) {
        return ResponseEntity.ok(veterinarioService.actualizarVeterinario(id, veterinarioRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar veterinario", description = "Elimina un veterinario por su identificador.")
    public ResponseEntity<Void> eliminarVeterinario(@PathVariable Long id) {
        veterinarioService.eliminarVeterinario(id);
        return ResponseEntity.noContent().build();
    }
}
