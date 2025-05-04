package br.com.challenge.repositories;

import br.com.challenge.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    Optional<Compra> findCompraById(Long id);

    List<Compra> findByOficinaId(Long idOficina);

    List<Compra> findByFinalizada(Boolean finalizada);

    List<Compra> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);


}
