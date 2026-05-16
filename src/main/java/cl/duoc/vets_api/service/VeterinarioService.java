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

    private VeterinarioResponseDto mapToVeterinaroToVeterinarioResponse(Veterinario veterinarioModel) {

        VeterinarioResponseDto veterinarioResponse = new VeterinarioResponseDto();
        HorarioResponseDto horarioResponse = new HorarioResponseDto();

       

        List<HorarioResponseDto> horariosvetList = new ArrayList<>(); 


        List<Horario> horariosBaseDeDatos = horarioRepository.findAll();

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
        veterinarioResponse.setEscirujano(veterinarioModel.getEsCirujano());
        //veterinarioResponse.setHorario(veterinarioModel.getHorario().getId();




        

        /*
         * private Long id;
         * private DiasSemana dia;
         * private LocalTime horaInicio;
         * private LocalTime horaFin;
         * private Integer duracionTurno;
         */

        return null;
    }

    public VeterinarioResponseDto registrarVeterinario(VeterinarioRequestDto veterinarioRequest) {

        return null;

    }

    public VeterinarioResponseDto ConsultarVeterinarioId(Long veterinarioidId) {
        return null;

    }

    public VeterinarioResponseDto actualizarVeterinario() {

        return null;

    }

    public VeterinarioResponseDto eliminarVeterinario() {

        return null;

    }

}