package ma.enset.banque.service;

import ma.enset.banque.dao.Compte;
import ma.enset.banque.dao.Repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompteServiceImpl implements CompteService{
    @Autowired
    private CompteRepository compteRepository;

    @Override
    public Compte createAccount(Compte c) {
        return compteRepository.save(c);
    }


    public void deleteAccount(Compte c) {
        Compte compte = compteRepository.findById(c.getId())
                .orElseThrow(null);
        if(compte != null) {
            compteRepository.delete(compte);
        }
    }
    @Override
    public Optional<Compte> getAccount(Compte c) {
        return compteRepository.findById(c.getId());
    }

    @Override
    public List<Compte> getAccounts() {
        return compteRepository.findAll();
    }

    @Override
    public void debiter(Compte c, double montant) {
        if (montant > c.getSolde()) {
            System.out.println("solde insuffisant !");
        }
        c.setSolde(c.getSolde() - montant);
    }

    @Override
    public void crediter(Compte c, double montant) {
        c.setSolde(c.getSolde() + montant);
    }

    @Override
    public double getSolde(Compte c) {
        return c.getSolde();
    }

    @Override
    public void transferer(Compte source, Compte destination, double montant) {
        debiter(source,montant);
        crediter(destination,montant);
    }

    @Override
    public boolean verifierSolde(Compte c, double montant) {
        return !(montant > c.getSolde());
    }
}
