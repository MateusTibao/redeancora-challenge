package br.com.challenge.repositories;

import br.com.challenge.models.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OficinaRepository extends JpaRepository<Oficina, Long> {

    Optional<Oficina> findOficinaById(Long id);

    List<Oficina> findOficinaByNomeContainingIgnoreCase(String nome);

    List<Oficina> findOficinaByRepresentanteId(Long idRepresentante);

    List<Oficina> findOficinaByRepresentanteNomeContainingIgnoreCase(String nomeRepresentante);

    Optional<Oficina> findByEmail(String email);
}
