package cl.duoc.vets_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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

    public VeterinarioResponseDto MapToVeterinaroToVeterinarioResponse(Veterinario veterinarioModel) {

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

                // Asocia turno a horario
                horarioResponse.setTurno(turnoResponse);

                // Agregamos el horario lista final
                listaDeHorariosDto.add(horarioResponse);
            }
        }

        veterinarioResponse.setHorario(listaDeHorariosDto);

        return veterinarioResponse;
    }
}
