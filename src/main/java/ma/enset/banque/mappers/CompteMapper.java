package ma.enset.banque.mappers;

import ma.enset.banque.dao.Compte;
import ma.enset.banque.dto.CompteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompteMapper {
    CompteDto   toDto(Compte compte);
    Compte   toCompte(CompteDto compteDto);
}
