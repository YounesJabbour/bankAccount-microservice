package ma.enset.banque.service;

import ma.enset.banque.dao.Compte;
import ma.enset.banque.projection.CompteSummary;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource (collectionResourceRel = "compte", path = "compte", excerptProjection = CompteSummary.class)
public interface CompteService {
    Compte createAccount(Compte c);
    void deleteAccount(Compte c);
    Optional<Compte> getAccount(Compte c);
    List<Compte> getAccounts();
    void debiter(Compte c, double montant);
    void crediter(Compte c, double montant);
    double getSolde(Compte c);
    void transferer(Compte source, Compte destination, double montant);
    boolean verifierSolde(Compte c, double montant);
}
