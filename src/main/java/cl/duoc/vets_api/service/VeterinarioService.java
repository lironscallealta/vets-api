/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.service;

import cl.duoc.vets_api.dto.request.VeterinarioRequestDto;
import cl.duoc.vets_api.dto.response.HorarioResponseDto;
import cl.duoc.vets_api.dto.response.VetScheduleResponse;
import cl.duoc.vets_api.dto.response.VeterinarioResponseDto;
import cl.duoc.vets_api.exception.BadRequestException;
import cl.duoc.vets_api.exception.ResourceNotFoundException;
import cl.duoc.vets_api.model.DiasSemana;
import cl.duoc.vets_api.model.Horario;
import cl.duoc.vets_api.model.Veterinario;
import cl.duoc.vets_api.repository.HorarioRepository;
import cl.duoc.vets_api.repository.VeterinarioRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;
    private final HorarioRepository horarioRepository;

    private VeterinarioResponseDto mapVeterinaroToVeterinarioResponse(Veterinario veterinarioModel) { // 100 años para
        // esta funcion
        VeterinarioResponseDto veterinarioResponse = new VeterinarioResponseDto();

        List<HorarioResponseDto> horarioVeterinarioLista = new ArrayList<>();

        for (Horario horario : veterinarioModel.getHorarioVeterinario()) {

            HorarioResponseDto horarioResponse = new HorarioResponseDto();

            horarioResponse.setId(horario.getId());
            horarioResponse.setDia(horario.getDia());
            horarioResponse.setHoraInicio(horario.getHoraInicio());
            horarioResponse.setHoraFin(horario.getHoraFin());

            horarioVeterinarioLista.add(horarioResponse);
        }

        veterinarioResponse.setId(veterinarioModel.getId());
        veterinarioResponse.setNombreCompleto(veterinarioModel.getNombre() + " " + veterinarioModel.getSegundoNombre());

        veterinarioResponse.setApellidos(veterinarioModel.getApellido() + " " + veterinarioModel.getSegundoApellido());
        veterinarioResponse.setRut(veterinarioModel.getRut() + "-" + veterinarioModel.getDv());
        veterinarioResponse.setEmail(veterinarioModel.getEmail());
        Integer edad = Period.between(veterinarioModel.getFechaNacimiento(), LocalDate.now())
                .getYears();
        veterinarioResponse.setEdad(edad);
        veterinarioResponse.setNumeroRegistroProfesional(veterinarioModel.getNumeroRegistroProfesional());

        veterinarioResponse.setHorarioVeterinario(horarioVeterinarioLista);

        return veterinarioResponse;
    }

    private static DiasSemana toDiasSemana(LocalDate fecha) {
        return switch (fecha.getDayOfWeek()) {
            case MONDAY -> DiasSemana.LUNES;
            case TUESDAY -> DiasSemana.MARTES;
            case WEDNESDAY -> DiasSemana.MIERCOLES;
            case THURSDAY -> DiasSemana.JUEVES;
            case FRIDAY -> DiasSemana.VIERNES;
            case SATURDAY -> DiasSemana.SABADO;
            case SUNDAY -> DiasSemana.DOMINGO;
        };
    }

    @Transactional
    public VeterinarioResponseDto registrarVeterinario(VeterinarioRequestDto veterinarioRequest) {
        log.info("Registrando veterinario: {}", veterinarioRequest.getNombre());

        if (veterinarioRequest.getNombre() == null
                || veterinarioRequest.getNombre().isBlank()) {
            throw new BadRequestException("El nombre del veterinario es requerido y no puede estar vacío.");
        }
        if (veterinarioRequest.getApellido() == null
                || veterinarioRequest.getApellido().isBlank()) {
            throw new BadRequestException("El apellido del veterinario es requerido y no puede estar vacío.");
        }
        if (veterinarioRequest.getEmail() == null
                || veterinarioRequest.getEmail().isBlank()) {
            throw new BadRequestException("El email del veterinario es requerido y no puede estar vacío.");
        }
        if (veterinarioRequest.getNumeroRegistroProfesional() == null
                || veterinarioRequest.getNumeroRegistroProfesional().isBlank()) {
            throw new BadRequestException("El número de registro profesional es requerido y no puede estar vacío.");
        }

        Veterinario veterinario = new Veterinario();

        VeterinarioResponseDto response = new VeterinarioResponseDto();

        List<Horario> horarios = horarioRepository.findAllById(veterinarioRequest.getHorarioVeterinario());

        veterinario.setNombre(veterinarioRequest.getNombre());
        veterinario.setSegundoNombre(veterinarioRequest.getSegundoNombre());
        veterinario.setApellido(veterinarioRequest.getApellido());
        veterinario.setSegundoApellido(veterinarioRequest.getSegundoApellido());
        veterinario.setRut(veterinarioRequest.getRut());
        veterinario.setDv(veterinarioRequest.getDv());
        veterinario.setEmail(veterinarioRequest.getEmail());
        veterinario.setTelefonoCelular(veterinarioRequest.getTelefonoCelular());
        veterinario.setFechaNacimiento(veterinarioRequest.getFechaNacimiento());
        veterinario.setNumeroRegistroProfesional(veterinarioRequest.getNumeroRegistroProfesional());
        veterinario.setEgresoProfesional(veterinarioRequest.getEgresoProfesional());
        veterinario.setPuedeOperar(veterinarioRequest.getPuedeOperar());
        veterinario.setHorarioVeterinario(horarios);

        Veterinario veterinarioGuardado = veterinarioRepository.save(veterinario);
        response = mapVeterinaroToVeterinarioResponse(veterinarioGuardado);

        return response;
    }

    public VeterinarioResponseDto consultarVeterinarioId(Long veterinarioidId) {
        Veterinario veterinarioModel = veterinarioRepository
                .findById(veterinarioidId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("no se encontro veterinario con id " + veterinarioidId));
        VeterinarioResponseDto response = mapVeterinaroToVeterinarioResponse(veterinarioModel);
        return response;
    }

    @Transactional
    public VeterinarioResponseDto actualizarVeterinario(Long veterinarioId, VeterinarioRequestDto veterinarioRequest) {

        Veterinario veterinarioModel = veterinarioRepository
                .findById(veterinarioId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("no se encontro veterinario con id: " + veterinarioId));
        veterinarioModel.setNombre(veterinarioRequest.getNombre());
        veterinarioModel.setSegundoNombre(veterinarioRequest.getSegundoNombre());
        veterinarioModel.setApellido(veterinarioRequest.getApellido());
        veterinarioModel.setSegundoApellido(veterinarioRequest.getSegundoApellido());
        veterinarioModel.setRut(veterinarioRequest.getRut());
        veterinarioModel.setDv(veterinarioRequest.getDv());
        veterinarioModel.setEmail(veterinarioRequest.getEmail());
        veterinarioModel.setTelefonoCelular(veterinarioRequest.getTelefonoCelular());
        veterinarioModel.setFechaNacimiento(veterinarioRequest.getFechaNacimiento());
        veterinarioModel.setNumeroRegistroProfesional(veterinarioRequest.getNumeroRegistroProfesional());
        veterinarioModel.setEgresoProfesional(veterinarioRequest.getEgresoProfesional());
        veterinarioModel.setPuedeOperar(veterinarioRequest.getPuedeOperar());

        VeterinarioResponseDto response = mapVeterinaroToVeterinarioResponse(veterinarioModel);

        return response;
    }

    @Transactional
    public void eliminarVeterinario(Long veterinarioId) {

        Veterinario veterinarioEliminar = veterinarioRepository
                .findById(veterinarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "no se puede eliminar porque no existe el veterinario con id: " + veterinarioId));
        veterinarioRepository.delete(veterinarioEliminar);
    }

    public List<VeterinarioResponseDto> buscarTodos() {
        return veterinarioRepository.findAll().stream()
                .map(this::mapVeterinaroToVeterinarioResponse)
                .toList();
    }

    public List<VetScheduleResponse> consultarHorariosPorDia(LocalDate req) {
        DiasSemana dia = toDiasSemana(req);
        return veterinarioRepository.findAll().stream()
                .flatMap(vet -> vet.getHorarioVeterinario().stream()
                        .filter(horario -> horario.getDia().equals(dia))
                        .map(horario -> new VetScheduleResponse(
                                vet.getId(),
                                generarNombreCompleto(vet),
                                horario.getHoraInicio(),
                                horario.getHoraFin())))
                .toList();
    }

    private String generarNombreCompleto(Veterinario vet) {
        return vet.getNombre() + " " + vet.getSegundoNombre() + " " + vet.getApellido() + " "
                + vet.getSegundoApellido();
    }
}
