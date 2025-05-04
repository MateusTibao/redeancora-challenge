package br.com.challenge.services;

import br.com.challenge.models.Oficina;
import br.com.challenge.repositories.OficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OficinaService {

    private final OficinaRepository oficinaRepository;

    @Autowired
    public OficinaService(OficinaRepository oficinaRepository) {
        this.oficinaRepository = oficinaRepository;
    }

    public Oficina salvarOficina(Oficina oficina) {
        return oficinaRepository.save(oficina);
    }

    public Oficina buscarOficinaPorId(Long id) {
        return oficinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oficina não encontrada"));
    }

    public List<Oficina> buscarOficinasPorNome(String nome) {
        return oficinaRepository.findOficinaByNomeContainingIgnoreCase(nome);
    }

    public List<Oficina> buscarOficinasPorRepresentanteId(Long idRepresentante) {
        return oficinaRepository.findOficinaByRepresentanteId(idRepresentante);
    }

    public List<Oficina> buscarOficinasPorRepresentanteNome(String nomeRepresentante) {
        return oficinaRepository.findOficinaByRepresentanteNomeContainingIgnoreCase(nomeRepresentante);
    }

    public void excluirOficina(Long id) {
        oficinaRepository.deleteById(id);
    }


}
