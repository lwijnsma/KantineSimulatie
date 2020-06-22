import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FactuurRegel")
public class FactuurRegel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Factuur factuur;

    @Embedded
    public Artikel artikel;

    public FactuurRegel() {
    }

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
