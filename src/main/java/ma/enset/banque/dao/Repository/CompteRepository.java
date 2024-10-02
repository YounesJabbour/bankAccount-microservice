package ma.enset.banque.dao.Repository;

import ma.enset.banque.dao.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository  extends JpaRepository<Compte,Long> {

}
