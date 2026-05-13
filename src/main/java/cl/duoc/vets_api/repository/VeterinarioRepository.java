package cl.duoc.vets_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.vets_api.model.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

}
