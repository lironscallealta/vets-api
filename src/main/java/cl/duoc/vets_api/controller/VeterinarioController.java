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
import jakarta.validation.Valid;
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
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @PostMapping
    public ResponseEntity<VeterinarioResponseDto> registrarVeterinario(
            @Valid @RequestBody VeterinarioRequestDto veterinarioRequest) {

        VeterinarioResponseDto registrarVeterinario = veterinarioService.registrarVeterinario(veterinarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(registrarVeterinario);
    } /*
       *
       * try {
       * Fiesta registar = fiestaService.registrarFiesta(nuevaFiesta);
       * return ResponseEntity.status(HttpStatus.CREATED).body(registar);
       * } catch (IllegalArgumentException e) {
       * return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("Error",
       * "Codigo Existe"));
       * }
       */

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDto> consultarVeterinarioId(@PathVariable Long id) {

        VeterinarioResponseDto consultarVeterinarioId = veterinarioService.ConsultarVeterinarioId(id);

        return ResponseEntity.ok(consultarVeterinarioId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDto> actualizarVeterinario(
            @PathVariable Long id, @Valid @RequestBody VeterinarioRequestDto veterinarioRequest) {

        VeterinarioResponseDto actualizarVeterinario = veterinarioService.actualizarVeterinario(id, veterinarioRequest);

        return ResponseEntity.ok(actualizarVeterinario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDto> eliminarVeterinario(@PathVariable Long id) {
        VeterinarioResponseDto eliminarVeterinario = veterinarioService.eliminarVeterinario(id);
        return ResponseEntity.ok(eliminarVeterinario);
    }
}
