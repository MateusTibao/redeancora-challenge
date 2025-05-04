package br.com.challenge;

import br.com.challenge.models.Oficina;
import br.com.challenge.models.Produto;
import br.com.challenge.security.*;
import br.com.challenge.repositories.OficinaRepository;
import br.com.challenge.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private OficinaRepository oficinaRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (oficinaRepository.count() == 0) {
			Oficina oficina = new Oficina();
			oficina.setNome("Oficina Teste");
			oficina.setEmail("teste@oficina.com");
			oficina.setTelefone("11999999999");
			oficina.setEndereco("rua dois, 194");
			oficina.setSenha(passwordEncoder.encode("1234"));

			oficinaRepository.save(oficina);
		}

		if (produtoRepository.count() == 0) {
			Produto produto1 = new Produto("Produto 1", "Descrição do Produto 1", 10.0, 100);
			Produto produto2 = new Produto("Produto 2", "Descrição do Produto 2", 20.0, 50);
			Produto produto3 = new Produto("Produto 3", "Descrição do Produto 3", 30.0, 200);

			produtoRepository.save(produto1);
			produtoRepository.save(produto2);
			produtoRepository.save(produto3);
		}

		System.out.println("RODOU!");
	}
}