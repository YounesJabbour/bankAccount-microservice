package ma.enset.banque.dto;


import lombok.*;
import ma.enset.banque.dao.enums.CompteType;

@Getter
@Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CompteDto {
    private String numero;
    private double solde;
    private String currency;
    private CompteType type;
}
