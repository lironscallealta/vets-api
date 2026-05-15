package cl.duoc.vets_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.duoc.vets_api.dto.request.VeterinarioRequestDto;
import cl.duoc.vets_api.dto.response.HorarioResponseDto;
import cl.duoc.vets_api.dto.response.TurnoResponseDto;
import cl.duoc.vets_api.dto.response.VeterinarioResponseDto;
import cl.duoc.vets_api.model.Horario;
import cl.duoc.vets_api.model.Veterinario;
import cl.duoc.vets_api.repository.HorarioRepository;
import cl.duoc.vets_api.repository.TurnoRepository;
import cl.duoc.vets_api.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;
    private final HorarioRepository horarioRepository;
    private final TurnoRepository turnoRepository;

    private VeterinarioResponseDto mapToVeterinaroToVeterinarioResponse(Veterinario veterinarioModel) {

        VeterinarioResponseDto veterinarioResponse = new VeterinarioResponseDto();

        // 1. Mapeo de datos básicos del Veterinario
        veterinarioResponse.setId(veterinarioModel.getId());
        veterinarioResponse.setNumeroRegistroProfesional(veterinarioModel.getNumeroRegistroProfesional());
        veterinarioResponse.setEgresoProfesional(veterinarioModel.getEgresoProfesional());
        veterinarioResponse.setEscirujano(veterinarioModel.getEsCirujano());

        // 2. Mapeo de la lista de Horarios
        List<HorarioResponseDto> listaDeHorariosDto = new ArrayList<>();

        if (veterinarioModel.getHorarios() != null) {
            for (Horario horario : veterinarioModel.getHorarios()) {

                // Mapeo del Turno lo hice con for la lista y aun no manejo stream bien
                TurnoResponseDto turnoResponse = new TurnoResponseDto();
                if (horario.getTurno() != null) {
                    turnoResponse.setId(horario.getTurno().getId());
                    turnoResponse.setHorarioTurno(horario.getTurno().getHorarioTurno());
                }

                // Mapeo del Horario
                HorarioResponseDto horarioResponse = new HorarioResponseDto();
                horarioResponse.setId(horario.getId());
                horarioResponse.setDia(horario.getDia());

                // asocio turno a horario
                horarioResponse.setTurno(turnoResponse);

                // agrego lista final
                listaDeHorariosDto.add(horarioResponse);
            }
        }

        veterinarioResponse.setHorario(listaDeHorariosDto);

        return veterinarioResponse;
    }

    public VeterinarioResponseDto registrarVeterinario(VeterinarioRequestDto veterinarioRequest) {

        Veterinario veterinarioModel = new Veterinario();

        List<Horario> horarios = horarioRepository.findAllById(veterinarioRequest.getIdHorarios());

        if (horarios.isEmpty()) {

            throw new RuntimeException("Error al crear veterinario, no se encontraron horarios");

        }

        veterinarioModel.setEsCirujano(veterinarioRequest.getEscirujano());
        veterinarioModel.setEgresoProfesional(veterinarioRequest.getEgresoProfesional());
        veterinarioModel.setNumeroRegistroProfesional(veterinarioRequest.getNumeroRegistroProfesional());
        veterinarioModel.setHorarios(horarios);
        veterinarioModel.setEsCirujano(veterinarioRequest.getEscirujano());

        VeterinarioResponseDto response = mapToVeterinaroToVeterinarioResponse(veterinarioModel);

        return response;

    }

    public VeterinarioResponseDto ConsultarVeterinarioId(Long veterinarioidId) {

        Optional<Veterinario> veterinario = veterinarioRepository.findById(veterinarioidId);

        if (veterinario.isEmpty()) {

            throw new RuntimeException("Error al buscar veterinario, no se encontro");

        }

        VeterinarioResponseDto response = mapToVeterinaroToVeterinarioResponse(veterinario.get());

        return response;

    }

    public List<VeterinarioResponseDto> ConsultarVeterinarios() {

        List<Veterinario> veterinarios = veterinarioRepository.findAll();
        List<VeterinarioResponseDto> responseList = new ArrayList<>();

        if (veterinarios.isEmpty()) {

            throw new RuntimeException("No hay veterinarios registrados");

        }

        for (Veterinario veterinario : veterinarios) {

            VeterinarioResponseDto response = mapToVeterinaroToVeterinarioResponse(veterinario);
            responseList.add(response);

        }

        return responseList;

    }

    public VeterinarioResponseDto actualizarVeterinario() {

        return null;

    }

    public VeterinarioResponseDto eliminarVeterinario() {

        return null;

    }

}
