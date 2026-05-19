/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.controller;

import cl.duoc.vets_api.dto.request.VeterinarioRequestDto;
import cl.duoc.vets_api.dto.response.VeterinarioResponseDto;
import cl.duoc.vets_api.service.VeterinarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @PostMapping
    public ResponseEntity<VeterinarioResponseDto> registrarVeterinario(
            @Valid @RequestBody VeterinarioRequestDto veterinarioRequest) {

        VeterinarioResponseDto response = veterinarioService.registrarVeterinario(veterinarioRequest);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<VeterinarioResponseDto> ConsultarVeterinarioId(@PathVariable Long id) {

        VeterinarioResponseDto response = veterinarioService.ConsultarVeterinarioId(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDto> actualizarVeterinario(
            @PathVariable Long id, @Valid @RequestBody VeterinarioRequestDto veterinarioRequest) {

        VeterinarioResponseDto response = veterinarioService.actualizarVeterinario(id, veterinarioRequest);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDto> eliminarVeterinario(@PathVariable Long id) {
        VeterinarioResponseDto response = veterinarioService.eliminarVeterinario(id);
        return ResponseEntity.ok(response);
    }
}
