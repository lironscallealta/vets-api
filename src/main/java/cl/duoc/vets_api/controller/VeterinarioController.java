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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public ResponseEntity<VeterinarioResponseDto> registrarVeterinario(VeterinarioRequestDto veterinarioRequest) {

        VeterinarioResponseDto response = veterinarioService.registrarVeterinario(veterinarioRequest);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<VeterinarioResponseDto> ConsultarVeterinarioId(Long id) {

        VeterinarioResponseDto response = veterinarioService.ConsultarVeterinarioId(id);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<VeterinarioResponseDto> actualizarVeterinario(
            Long id, VeterinarioRequestDto veterinarioRequest) {

        VeterinarioResponseDto response = veterinarioService.actualizarVeterinario(id, veterinarioRequest);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<VeterinarioResponseDto> eliminarVeterinario(Long id) {
        VeterinarioResponseDto response = veterinarioService.eliminarVeterinario(id);
        return ResponseEntity.ok(response);
    }
}
