package br.com.challenge.services;

import br.com.challenge.models.Funcionario;
import br.com.challenge.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findFuncionarioById(id);
    }

    public Optional<Funcionario> buscarFuncionarioPorFuncional(String funcional) {
        return funcionarioRepository.findFuncionarioByFuncional(funcional);
    }

    public List<Funcionario> buscarFuncionariosPorNome(String nome) {
        return funcionarioRepository.findFuncionarioByNomeContainingIgnoreCase(nome);
    }

    public List<Funcionario> buscarFuncionariosPorGerenteId(Long gerenteId) {
        return funcionarioRepository.findFuncionarioByGerenteId(gerenteId);
    }

    public void excluirFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
