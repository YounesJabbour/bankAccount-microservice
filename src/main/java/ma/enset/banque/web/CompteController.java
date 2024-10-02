package ma.enset.banque.web;


import ma.enset.banque.dao.Compte;
import ma.enset.banque.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/comptes")
public class CompteController {
    @Autowired
    private CompteService compteService;

    @GetMapping("")
    public ResponseEntity<List<Compte>> getAccounts() {
    List<Compte> listAccounts = compteService.getAccounts();
    return ResponseEntity.ok(listAccounts);
    }
}
