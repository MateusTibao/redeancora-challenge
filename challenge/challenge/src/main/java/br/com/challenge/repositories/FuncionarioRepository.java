package br.com.challenge.repositories;

import br.com.challenge.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findFuncionarioById(Long id);

    Optional<Funcionario> findFuncionarioByFuncional(String funcional);

    List<Funcionario> findFuncionarioByNomeContainingIgnoreCase(String nome);

    List<Funcionario> findFuncionarioByGerenteId(Long idGerente);

}
