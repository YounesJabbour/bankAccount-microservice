package ma.enset.banque.dao;


import jakarta.persistence.*;
import lombok.*;
import ma.enset.banque.dao.enums.CompteType;


@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String numero;
    private double solde;
    private String currency;
    @Enumerated(EnumType.STRING)
    private CompteType type;

    @ManyToOne
    private Customer customer;
}
