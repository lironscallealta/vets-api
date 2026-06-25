/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.duoc.vets_api.dto.request.VeterinarioRequestDto;
import cl.duoc.vets_api.dto.response.VeterinarioResponseDto;
import cl.duoc.vets_api.exception.GlobalExceptionHandler;
import cl.duoc.vets_api.exception.ResourceNotFoundException;
import cl.duoc.vets_api.security.JwtAuthFilter;
import cl.duoc.vets_api.service.VeterinarioService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

@WebMvcTest(VeterinarioController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
@ActiveProfiles("test")
class VeterinarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @MockitoBean
    private VeterinarioService veterinarioService;

    @MockitoBean
    private JwtAuthFilter jwtAuthFilter;

    @Test
    void registrarVeterinarioDebeRetornar201CuandoRequestEsValido() throws Exception {
        VeterinarioRequestDto request = crearRequestValido();
        VeterinarioResponseDto response = new VeterinarioResponseDto(
                1L, "Ana María", "González Pérez", "12345678-9", "ana.gonzalez@vet.cl", 36, "VET-123456", List.of());

        when(veterinarioService.registrarVeterinario(any(VeterinarioRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/vets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("ana.gonzalez@vet.cl"));

        verify(veterinarioService).registrarVeterinario(any(VeterinarioRequestDto.class));
    }

    @Test
    void registrarVeterinarioDebeRetornar400CuandoEmailEsInvalido() throws Exception {
        VeterinarioRequestDto request = crearRequestValido();
        request.setEmail("correo-invalido");

        mockMvc.perform(post("/api/v1/vets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(veterinarioService, never()).registrarVeterinario(any(VeterinarioRequestDto.class));
    }

    @Test
    void consultarVeterinarioIdDebeRetornar200CuandoExiste() throws Exception {
        VeterinarioResponseDto response = new VeterinarioResponseDto(
                1L, "Ana María", "González Pérez", "12345678-9", "ana.gonzalez@vet.cl", 36, "VET-123456", List.of());

        when(veterinarioService.consultarVeterinarioId(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/vets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroRegistroProfesional").value("VET-123456"));
    }

    @Test
    void consultarVeterinarioIdDebeRetornar404CuandoNoExiste() throws Exception {
        when(veterinarioService.consultarVeterinarioId(99L))
                .thenThrow(new ResourceNotFoundException("no se encontro veterinario con id 99"));

        mockMvc.perform(get("/api/v1/vets/99")).andExpect(status().isNotFound());
    }

    @Test
    void buscarTodosDebeRetornar200() throws Exception {
        VeterinarioResponseDto response = new VeterinarioResponseDto(
                1L, "Ana María", "González Pérez", "12345678-9", "ana.gonzalez@vet.cl", 36, "VET-123456", List.of());

        when(veterinarioService.buscarTodos()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/vets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("ana.gonzalez@vet.cl"));
    }

    @Test
    void actualizarVeterinarioDebeRetornar200CuandoExiste() throws Exception {
        VeterinarioRequestDto request = crearRequestValido();
        request.setEmail("ana.actualizada@vet.cl");
        VeterinarioResponseDto response = new VeterinarioResponseDto(
                1L, "Ana María", "González Pérez", "12345678-9", "ana.actualizada@vet.cl", 36, "VET-123456", List.of());

        when(veterinarioService.actualizarVeterinario(eq(1L), any(VeterinarioRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/v1/vets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ana.actualizada@vet.cl"));
    }

    @Test
    void eliminarVeterinarioDebeRetornar204() throws Exception {
        mockMvc.perform(delete("/api/v1/vets/1")).andExpect(status().isNoContent());

        verify(veterinarioService).eliminarVeterinario(eq(1L));
    }

    @Test
    void eliminarVeterinarioDebeRetornar404CuandoNoExiste() throws Exception {
        doThrow(new ResourceNotFoundException("no se puede eliminar porque no existe el veterinario con id: 99"))
                .when(veterinarioService)
                .eliminarVeterinario(99L);

        mockMvc.perform(delete("/api/v1/vets/99")).andExpect(status().isNotFound());
    }

    private VeterinarioRequestDto crearRequestValido() {
        return new VeterinarioRequestDto(
                "Ana",
                "María",
                "González",
                "Pérez",
                "12345678",
                "9",
                "ana.gonzalez@vet.cl",
                "+56912345678",
                LocalDate.of(1990, 3, 15),
                "VET-123456",
                LocalDate.of(2015, 12, 1),
                true,
                List.of(1L));
    }
}
