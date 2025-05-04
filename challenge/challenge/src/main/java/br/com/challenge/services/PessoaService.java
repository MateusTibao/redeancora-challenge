package br.com.challenge.services;

import br.com.challenge.models.Pessoa;
import br.com.challenge.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {


    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> buscarPessoaPorId(Long id) {
        return pessoaRepository.findPessoaById(id);
    }

    public List<Pessoa> buscarPessoasPorNome(String nome) {
        return pessoaRepository.findPessoaByNomeContainingIgnoreCase(nome);
    }

    public Optional<Pessoa> buscarPessoaPorCpf(String cpf) {
        return pessoaRepository.findPessoaByCpf(cpf);
    }

    public void excluirPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}

