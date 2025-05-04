package br.com.challenge.security;

import br.com.challenge.models.Oficina;
import br.com.challenge.repositories.OficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class OficinaUserDetailsService implements UserDetailsService {

    @Autowired
    private OficinaRepository oficinaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Oficina oficina = oficinaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Oficina não encontrada com o email: " + email));

        return new User(
                oficina.getEmail(),
                oficina.getSenha(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
