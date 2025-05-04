package br.com.challenge.repositories;

import br.com.challenge.models.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {

    Optional<Gerente> findGerenteById(Long id);

    Optional<Gerente> findGerenteByCpf(String cpf);

    List<Gerente> findGerenteByNomeContainingIgnoreCase(String nome);

    List<Gerente> findGerenteByFuncionariosIsNotEmpty();

    Optional<Gerente> findGerenteByFuncional(String funcional);
}

