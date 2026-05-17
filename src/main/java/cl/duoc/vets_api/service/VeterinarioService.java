package cl.duoc.vets_api.service;

import java.time.LocalDate;
import java.time.Period;

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
            horarioResponse.setHoraInicio(horario.getHoraInicio());
            horarioResponse.setHoraFin(horario.getHoraFin());

            horarioVeterinarioLista.add(horarioResponse);

        }

        veterinarioResponse.setId(veterinarioModel.getId());
        veterinarioResponse.setNombreCompleto(veterinarioModel.getNombre() + " " + veterinarioModel.getSegundoNombre());

        veterinarioResponse.setApellidos(veterinarioModel.getApellido() + " " + veterinarioModel.getSegundoApellido());
        veterinarioResponse.setRut(veterinarioModel.getRut() + "-" + veterinarioModel.getDv());
        veterinarioResponse.setEmail(veterinarioModel.getEmail());
        Integer edad = Period.between(veterinarioModel.getFechaNacimiento(), LocalDate.now()).getYears();
        veterinarioResponse.setEdad(edad);
        veterinarioResponse.setNumeroRegistroProfesional(veterinarioModel.getNumeroRegistroProfesional());

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
        Veterinario veterinarioModel = veterinarioRepository.findById(veterinarioidId).orElseThrow();
        VeterinarioResponseDto response = mapToVeterinaroToVeterinarioResponse(veterinarioModel);
        return response;

    }

    public VeterinarioResponseDto actualizarVeterinario(Long veterinarioId, VeterinarioRequestDto veterinarioRequest) {

        Veterinario veterinarioModel = veterinarioRepository.findById(veterinarioId).orElseThrow();
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

        VeterinarioResponseDto response = mapToVeterinaroToVeterinarioResponse(veterinarioModel);

        return response;

    }

    public VeterinarioResponseDto eliminarVeterinario(Long veterinarioId) {

        Veterinario veterinarioEliminar = veterinarioRepository.findById(veterinarioId).orElseThrow();
        VeterinarioResponseDto response = mapToVeterinaroToVeterinarioResponse(veterinarioEliminar);
        veterinarioRepository.deleteById(veterinarioId);
        return response;

    }

}