package cl.duoc.vets_api.service;

import org.springframework.stereotype.Service;
import cl.duoc.vets_api.dto.request.VeterinarioRequestDto;
import cl.duoc.vets_api.dto.response.VeterinarioResponseDto;
import cl.duoc.vets_api.model.Veterinario;
import cl.duoc.vets_api.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    private VeterinarioResponseDto mapToVeterinaroToVeterinarioResponse(Veterinario veterinarioModel) {

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
