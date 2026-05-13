package cl.duoc.vets_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.vets_api.model.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

}
