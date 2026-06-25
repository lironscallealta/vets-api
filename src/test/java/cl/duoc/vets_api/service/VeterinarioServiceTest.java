/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.duoc.vets_api.dto.request.VeterinarioRequestDto;
import cl.duoc.vets_api.dto.response.VeterinarioResponseDto;
import cl.duoc.vets_api.exception.BadRequestException;
import cl.duoc.vets_api.exception.ResourceNotFoundException;
import cl.duoc.vets_api.model.Horario;
import cl.duoc.vets_api.model.Veterinario;
import cl.duoc.vets_api.repository.HorarioRepository;
import cl.duoc.vets_api.repository.VeterinarioRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VeterinarioServiceTest {

    @Mock
    private VeterinarioRepository veterinarioRepository;

    @Mock
    private HorarioRepository horarioRepository;

    @InjectMocks
    private VeterinarioService veterinarioService;

    private VeterinarioRequestDto request;
    private Horario horario;

    @BeforeEach
    void setUp() {
        horario = new Horario(1L, null, LocalTime.of(9, 0), LocalTime.of(18, 0));
        request = new VeterinarioRequestDto(
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

    @Test
    void registrarVeterinarioDebeGuardarYRetornarResponse() {
        when(horarioRepository.findAllById(List.of(1L))).thenReturn(List.of(horario));
        when(veterinarioRepository.save(any(Veterinario.class))).thenAnswer(invocation -> {
            Veterinario veterinario = invocation.getArgument(0);
            veterinario.setId(10L);
            return veterinario;
        });

        VeterinarioResponseDto resultado = veterinarioService.registrarVeterinario(request);

        assertThat(resultado.getNombreCompleto()).isEqualTo("Ana María");
        assertThat(resultado.getRut()).isEqualTo("12345678-9");
        verify(veterinarioRepository).save(any(Veterinario.class));
    }

    @Test
    void registrarVeterinarioDebeLanzarBadRequestCuandoNombreEstaVacio() {
        request.setNombre("  ");

        assertThatThrownBy(() -> veterinarioService.registrarVeterinario(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("nombre");

        verify(veterinarioRepository, never()).save(any(Veterinario.class));
    }

    @Test
    void consultarVeterinarioIdDebeRetornarVeterinarioCuandoExiste() {
        Veterinario veterinario = crearVeterinario(5L);
        when(veterinarioRepository.findById(5L)).thenReturn(Optional.of(veterinario));

        VeterinarioResponseDto resultado = veterinarioService.consultarVeterinarioId(5L);

        assertThat(resultado.getId()).isEqualTo(5L);
        assertThat(resultado.getEmail()).isEqualTo("ana.gonzalez@vet.cl");
    }

    @Test
    void consultarVeterinarioIdDebeLanzarNotFoundCuandoNoExiste() {
        when(veterinarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> veterinarioService.consultarVeterinarioId(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void buscarTodosDebeRetornarListaMapeada() {
        when(veterinarioRepository.findAll()).thenReturn(List.of(crearVeterinario(1L), crearVeterinario(2L)));

        List<VeterinarioResponseDto> resultado = veterinarioService.buscarTodos();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getEmail()).isEqualTo("ana.gonzalez@vet.cl");
    }

    @Test
    void actualizarVeterinarioDebeRetornarResponseCuandoExiste() {
        Veterinario existente = crearVeterinario(4L);
        request.setEmail("nuevo.email@vet.cl");
        when(veterinarioRepository.findById(4L)).thenReturn(Optional.of(existente));

        VeterinarioResponseDto resultado = veterinarioService.actualizarVeterinario(4L, request);

        assertThat(resultado.getEmail()).isEqualTo("nuevo.email@vet.cl");
        assertThat(existente.getEmail()).isEqualTo("nuevo.email@vet.cl");
    }

    @Test
    void eliminarVeterinarioDebeEliminarCuandoExiste() {
        Veterinario veterinario = crearVeterinario(3L);
        when(veterinarioRepository.findById(3L)).thenReturn(Optional.of(veterinario));

        veterinarioService.eliminarVeterinario(3L);

        verify(veterinarioRepository).delete(veterinario);
    }

    @Test
    void eliminarVeterinarioDebeLanzarNotFoundCuandoNoExiste() {
        when(veterinarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> veterinarioService.eliminarVeterinario(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    private Veterinario crearVeterinario(Long id) {
        Veterinario veterinario = new Veterinario();
        veterinario.setId(id);
        veterinario.setNombre("Ana");
        veterinario.setSegundoNombre("María");
        veterinario.setApellido("González");
        veterinario.setSegundoApellido("Pérez");
        veterinario.setRut("12345678");
        veterinario.setDv("9");
        veterinario.setEmail("ana.gonzalez@vet.cl");
        veterinario.setTelefonoCelular("+56912345678");
        veterinario.setFechaNacimiento(LocalDate.of(1990, 3, 15));
        veterinario.setNumeroRegistroProfesional("VET-123456");
        veterinario.setEgresoProfesional(LocalDate.of(2015, 12, 1));
        veterinario.setPuedeOperar(true);
        veterinario.setHorarioVeterinario(List.of(horario));
        return veterinario;
    }
}
