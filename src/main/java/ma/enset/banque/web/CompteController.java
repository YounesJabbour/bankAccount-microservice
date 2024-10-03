package ma.enset.banque.web;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.enset.banque.dao.Compte;
import ma.enset.banque.dao.Repository.CompteRepository;
import ma.enset.banque.dto.CompteDto;
import ma.enset.banque.mappers.CompteMapper;
import ma.enset.banque.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompteController {
    private final CompteMapper compteMapper;
    private final CompteRepository compteRepository;

    @GetMapping("/comptes")
    public ResponseEntity<List<Compte>> getAccounts() {
    return ResponseEntity.ok(compteRepository.findAll());
    }

    @GetMapping("/compte/{id}")
    public Compte getCompteById(@PathVariable Long id) {
            return compteRepository.findById(id).orElseThrow(
                    () -> new RuntimeException(String.format("Compte with id %s not found", id)));

    }
}
