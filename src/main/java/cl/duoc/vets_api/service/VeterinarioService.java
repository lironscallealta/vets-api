package cl.duoc.vets_api.service;

import org.springframework.stereotype.Service;
import cl.duoc.vets_api.dto.request.VeterinarioRequestDto;
import cl.duoc.vets_api.dto.response.VeterinarioResponseDto;
import cl.duoc.vets_api.model.Disponibilidad;
import cl.duoc.vets_api.model.Veterinario;
import cl.duoc.vets_api.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    private VeterinarioResponseDto mapToVeterinaroToVeterinarioResponse(Veterinario veterinarioModel, List<Disponibilidad> horariosModel) {

        VeterinarioResponseDto vetResponse = new VeterinarioResponseDto();
        vetResponse.setId(veterinarioModel.getId());
        vetResponse.setNombre(veterinarioModel.getNombre());
        vetResponse.setSegundoNombre(veterinarioModel.getSegundoNombre());
        vetResponse.setApellido(veterinarioModel.getApellido());
        vetResponse.setSegundoApellido(veterinarioModel.getSegundoApellido());
        vetResponse.setRut(veterinarioModel.getRut());
        vetResponse.setDv(veterinarioModel.getDv());
        vetResponse.setEmail(veterinarioModel.getEmail());
        vetResponse.setTelefonoCelular(veterinarioModel.getTelefonoCelular());
        vetResponse.setFechaNacimiento(veterinarioModel.getFechaNacimiento());
        vetResponse.setNumeroRegistroProfesional(veterinarioModel.getNumeroRegistroProfesional());
        vetResponse.setEgresoProfesional(veterinarioModel.getEgresoProfesional());
        vetResponse.setEscirujano(veterinarioModel.getEscirujano());

        List<DisponibilidadResponseDto> listaHorariosDto = new ArrayList<>();
        
        if (horariosModel != null) {
            for (Disponibilidad h : horariosModel) {
                DisponibilidadResponseDto dispoDto = new DisponibilidadResponseDto();
                dispoDto.setId(h.getId());
                dispoDto.setDia(h.getDia());
                dispoDto.setHoraInicio(h.getHoraInicio());
                dispoDto.setHoraFin(h.getHoraFin());
                dispoDto.setDuracionTurno(h.getDuracionTurno());
                
                listaHorariosDto.add(dispoDto);
            }
        }

        vetResponse.setDisponibilidades(listaHorariosDto);

        return vetResponse;
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
