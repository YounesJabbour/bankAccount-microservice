package ma.enset.banque.dao.Repository;

import ma.enset.banque.dao.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CompteRepository  extends JpaRepository<Compte,Long> {
}
