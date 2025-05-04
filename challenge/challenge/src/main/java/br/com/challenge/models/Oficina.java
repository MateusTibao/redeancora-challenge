package br.com.challenge.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "OficinaEntidade")
@Table(name = "oficinas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Oficina implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "representante_id")
    private Pessoa representante;

    public Oficina(String nome, String endereco, String email, String telefone, String senha, Pessoa representante) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode ser nulo ou vazio.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio.");
        }
        if (telefone == null || telefone.isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio.");
        }
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia.");
        }
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.representante = representante;
    }

    // Implementação da interface UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Sem roles por enquanto
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
