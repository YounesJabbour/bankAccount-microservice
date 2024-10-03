package ma.enset.banque;

import lombok.RequiredArgsConstructor;
import ma.enset.banque.dao.Compte;
import ma.enset.banque.dao.Customer;
import ma.enset.banque.dao.Repository.CompteRepository;
import ma.enset.banque.dao.Repository.CustomerRepository;
import ma.enset.banque.dao.enums.CompteType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@RequiredArgsConstructor
@SpringBootApplication
public class BanqueApplication {

	private final CompteRepository compteRepository;
	private final CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(BanqueApplication.class, args);
	}


	@Bean
	public CommandLineRunner initializeComptes(CompteRepository compteRepository, CustomerRepository customerRepository) {
		return args -> {
			List<Compte> comptes = new ArrayList<>();
			Random random = new Random();

			Stream.of("mohammed", "younes", "ilyas").forEach(name-> {
				Customer customer =  Customer.builder()
						.nom(name)
						.prenom(name)
						.build();
				customerRepository.save(customer);
			});


			customerRepository.findAll().forEach(customer-> {
				for (int i = 0; i < 10; i++) {
					Compte compte = new Compte();
					compte.setNumero(generateAccountNumber());
					compte.setSolde(random.nextDouble() * 10000); // Random balance between 0 and 10000
					compte.setCurrency(random.nextBoolean() ? "MAD" : "EURO");
					compte.setType(random.nextBoolean() ? CompteType.CURRENT_ACCOUNT : CompteType.SAVING_ACCOUNT);
					compte.setCustomer(customer);
					comptes.add(compte);

					}
				compteRepository.saveAll(comptes);
			});

			System.out.println("the mock data created and saved to the database.");
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
