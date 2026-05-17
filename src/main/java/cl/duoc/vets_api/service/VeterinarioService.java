package cl.duoc.vets_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import cl.duoc.vets_api.dto.request.VeterinarioRequestDto;
import cl.duoc.vets_api.dto.response.HorarioResponseDto;
import cl.duoc.vets_api.dto.response.VeterinarioResponseDto;
import cl.duoc.vets_api.model.Horario;
import cl.duoc.vets_api.model.Veterinario;
import cl.duoc.vets_api.repository.HorarioRepository;
import cl.duoc.vets_api.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;
    private final HorarioRepository horarioRepository;

    private VeterinarioResponseDto mapToVeterinaroToVeterinarioResponse(Veterinario veterinarioModel) { // me demore
                                                                                                        // como 100 años
                                                                                                        // en esto

        VeterinarioResponseDto veterinarioResponse = new VeterinarioResponseDto();

        List<HorarioResponseDto> horarioVeterinarioLista = new ArrayList<>();

        for (Horario horario : veterinarioModel.getHorarioVeterinario()) {

            HorarioResponseDto horarioResponse = new HorarioResponseDto();

            horarioResponse.setId(horario.getId());
            horarioResponse.setDuracionTurno(horario.getDuracionTurno());
            horarioResponse.setHoraInicio(horario.getHoraInicio());
            horarioResponse.setHoraFin(horario.getHoraFin());

            horarioVeterinarioLista.add(horarioResponse);

        }

        veterinarioResponse.setId(veterinarioModel.getId());
        veterinarioResponse.setNombre(veterinarioModel.getNombre());
        veterinarioResponse.setSegundoNombre(veterinarioModel.getSegundoNombre());
        veterinarioResponse.setApellido(veterinarioModel.getApellido());
        veterinarioResponse.setSegundoApellido(veterinarioModel.getSegundoApellido());
        veterinarioResponse.setRut(veterinarioModel.getRut());
        veterinarioResponse.setDv(veterinarioModel.getDv());
        veterinarioResponse.setEmail(veterinarioModel.getEmail());
        veterinarioResponse.setTelefonoCelular(veterinarioModel.getTelefonoCelular());
        veterinarioResponse.setFechaNacimiento(veterinarioModel.getFechaNacimiento());
        veterinarioResponse.setNumeroRegistroProfesional(veterinarioModel.getNumeroRegistroProfesional());
        veterinarioResponse.setEgresoProfesional(veterinarioModel.getEgresoProfesional());
        veterinarioResponse.setPuedeOperar(veterinarioModel.getPuedeOperar());

        veterinarioResponse.setHorarioVeterinario(horarioVeterinarioLista);

        return veterinarioResponse;
    }

    public VeterinarioResponseDto registrarVeterinario(VeterinarioRequestDto veterinarioRequest) {

        Veterinario veterinario = new Veterinario();

        VeterinarioResponseDto response = new VeterinarioResponseDto();

        List<Horario> horarios = horarioRepository.findAllById(veterinarioRequest.getHorarioVeterinario());

        // me falta el error aca

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

        response = mapToVeterinaroToVeterinarioResponse(veterinario);

        return response;

    }

    public VeterinarioResponseDto ConsultarVeterinarioId(Long veterinarioidId) {
        Veterinario veterinario = veterinarioRepository.findById(veterinarioidId).orElseThrow();
        VeterinarioResponseDto response = mapToVeterinaroToVeterinarioResponse(veterinario);
        return response;

    }

    public VeterinarioResponseDto actualizarVeterinario() {

        return null;

    }

    public VeterinarioResponseDto eliminarVeterinario() {

        return null;

    }

}