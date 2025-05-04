package br.com.challenge.services;

import br.com.challenge.models.Gerente;
import br.com.challenge.repositories.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenteService {

    private final GerenteRepository gerenteRepository;

    @Autowired
    public GerenteService(GerenteRepository gerenteRepository) {
        this.gerenteRepository = gerenteRepository;
    }

    public Gerente salvarGerente(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public Optional<Gerente> buscarGerentePorId(Long id) {
        return gerenteRepository.findGerenteById(id);
    }

    public Optional<Gerente> buscarGerentePorFuncional(String funcional) {
        return gerenteRepository.findGerenteByFuncional(funcional);
    }

    public List<Gerente> buscarGerentesPorNome(String nome) {
        return gerenteRepository.findGerenteByNomeContainingIgnoreCase(nome);
    }

    public void excluirGerente(Long id) {
        gerenteRepository.deleteById(id);
    }
}
