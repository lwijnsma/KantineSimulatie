import javax.persistence.*;
import java.io.Serializable;

public class FactuurRegel implements Serializable {

    private Long id;

    private Factuur factuur;

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
