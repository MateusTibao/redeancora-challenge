package br.com.challenge.repositories;

import br.com.challenge.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findPessoaById(Long id);

    Optional<Pessoa> findPessoaByCpf(String cpf);

    List<Pessoa> findPessoaByNomeContainingIgnoreCase(String parteDoNome);

}