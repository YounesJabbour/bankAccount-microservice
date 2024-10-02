package ma.enset.banque;

import ma.enset.banque.dao.Compte;
import ma.enset.banque.dao.Repository.CompteRepository;
import ma.enset.banque.dao.enums.CompteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class BanqueApplication {

	@Autowired
	private CompteRepository compteRepository;
	public static void main(String[] args) {
		SpringApplication.run(BanqueApplication.class, args);
	}


	@Bean
	public CommandLineRunner initializeComptes(CompteRepository compteRepository) {
		return args -> {
			List<Compte> comptes = new ArrayList<>();
			Random random = new Random();

			for (int i = 0; i < 10; i++) {
				Compte compte = new Compte();
				compte.setNumero(generateAccountNumber());
				compte.setSolde(random.nextDouble() * 10000); // Random balance between 0 and 10000
				compte.setType(random.nextBoolean() ? CompteType.CURRENT_ACCOUNT : CompteType.SAVING_ACCOUNT);
				comptes.add(compte);
			}

			compteRepository.saveAll(comptes);
			System.out.println("10 mock bank accounts have been created and saved to the database.");
		};
	}

	private String generateAccountNumber() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
}
