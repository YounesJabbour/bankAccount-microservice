package ma.enset.banque.web;

import lombok.RequiredArgsConstructor;
import ma.enset.banque.dao.Compte;
import ma.enset.banque.dao.Customer;
import ma.enset.banque.dao.Repository.CompteRepository;
import ma.enset.banque.dao.Repository.CustomerRepository;
import ma.enset.banque.dto.CompteDto;
import ma.enset.banque.mappers.CompteMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class CompteGraphQLController {
    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;
    private final CustomerRepository customerRepository;

    @QueryMapping
    public List<Compte> accountsList() {
        return compteRepository.findAll();
    }

    @QueryMapping
    public Compte bankAccountById(@Argument int id) {
        return compteRepository.findById    ((long) id).orElseThrow(
                () -> new RuntimeException(String.format("Compte with id %s not found", id)));
    }

    @MutationMapping
    public Compte AddCompte(@Argument("input") CompteDto c) {
        return compteRepository.save(compteMapper.toCompte(c));
    }

    @MutationMapping
    public Compte updateCompte(@Argument int id, @Argument("input") CompteDto c) {
        Optional<Compte> compteOptional = compteRepository.findById((long) id);
        if (compteOptional.isEmpty()) {
            throw new RuntimeException(String.format("Compte with id %s not found", id));
        }
        Compte compte = compteOptional.get();
        compte.setCurrency(c.getCurrency());
        compte.setNumero(c.getNumero());
        compte.setSolde(c.getSolde());
        compte.setType(c.getType());

        return compteRepository.save(compte);
    }

    @MutationMapping
    public void deleteCompte(@Argument int id) {
        Optional<Compte> compteOptional = compteRepository.findById((long) id);
        if (compteOptional.isEmpty()) {
            throw new RuntimeException(String.format("Compte with id %s not found", id));
        }
        compteRepository.delete(compteOptional.get());
    }

    @QueryMapping
    public List<Customer> customersList() {
        return customerRepository.findAll();
    }

}
