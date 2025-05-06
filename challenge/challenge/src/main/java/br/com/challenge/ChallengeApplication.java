package br.com.challenge;

import br.com.challenge.models.*;
import br.com.challenge.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private OficinaRepository oficinaRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private GerenteRepository gerenteRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (pessoaRepository.count() == 0 && oficinaRepository.count() == 0) {
			Gerente gerente = new Gerente();
			gerente.setNome("Caio Costa");
			gerente.setTelefone("(11) 99876-4321");
			gerente.setCpf("123.456.789-09");
			gerente.setFuncional("GER2025001");

			Funcionario funcionario1 = new Funcionario();
			funcionario1.setNome("Caio Hideki");
			funcionario1.setTelefone("(11) 98765-1234");
			funcionario1.setCpf("321.654.987-00");
			funcionario1.setFuncional("FUN2025010");
			funcionario1.setGerente(gerente);

			Funcionario funcionario2 = new Funcionario();
			funcionario2.setNome("Lana Andrade");
			funcionario2.setTelefone("(11) 97654-3210");
			funcionario2.setCpf("456.789.123-77");
			funcionario2.setFuncional("FUN2025011");
			funcionario2.setGerente(gerente);

			gerenteRepository.save(gerente);
			funcionarioRepository.save(funcionario1);
			funcionarioRepository.save(funcionario2);

			if (oficinaRepository.count() == 0) {
				Oficina oficina = new Oficina();
				oficina.setNome("Auto Booz");
				oficina.setEndereco("Rua das Oficinas, 123 - São Paulo/SP");
				oficina.setEmail("contato@autobooz.com");
				oficina.setTelefone("(11) 3344-5566");
				oficina.setSenha(new BCryptPasswordEncoder().encode("CaioeJorge2028"));
				oficina.setRepresentante(gerente);

				oficinaRepository.save(oficina);
			}
		}

		if (produtoRepository.count() == 0) {
			Produto p1 = new Produto("Óleo de Motor 5W30", "Lubrificante sintético para motores a gasolina e flex", 45.90, 120);
			Produto p2 = new Produto("Filtro de Ar", "Filtro de ar compatível com veículos linha Fiat e GM", 25.00, 85);
			Produto p3 = new Produto("Pastilha de Freio Dianteira", "Jogo de pastilhas para freio dianteiro, linha Volkswagen", 89.90, 60);
			Produto p4 = new Produto("Bateria 60Ah", "Bateria automotiva 60 amperes, 12V, 18 meses de garantia", 349.00, 20);
			Produto p5 = new Produto("Pneu 175/65 R14", "Pneu de alta durabilidade para carros compactos", 289.99, 35);

			produtoRepository.save(p1);
			produtoRepository.save(p2);
			produtoRepository.save(p3);
			produtoRepository.save(p4);
			produtoRepository.save(p5);
		}

		System.out.println("RODOU!");
	}
}