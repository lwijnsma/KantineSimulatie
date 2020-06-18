import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factuurregel")
public class FactuurRegel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Factuur.class, cascade = CascadeType.ALL)
    @Column(name = "Factuur_idFactuur")
    private Factuur factuur;

    @Embedded
    @Column(name = "Artikel_idArtikel")
    public Artikel artikel;

    public FactuurRegel(){}

    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
    }

    @Override
    public String toString() {
        return "FactuurRegel{" +
                "factuur=" + factuur +
                ", artikel=" + artikel +
                '}';
    }
}
