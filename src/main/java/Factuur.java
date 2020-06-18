import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "factuur")
public class Factuur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idFactuur")
    private Long id;

    @Column(name = "datum")
    private LocalDate datum;

    @Column(name = "korting")
    private double korting;

    @Column(name = "totaal")
    private double totaal;

    private ArrayList<FactuurRegel> regels = new ArrayList<>();

    public Factuur() {
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum){
        this();
        this.datum = datum;

        verwerkBestelling(klant) ;
    }

    private void verwerkBestelling(Dienblad dienblad){
        Iterator<Artikel> artikelen = dienblad.getDienblad();
        Persoon klant = dienblad.getKlant();
        double kaarthouderKorting = 0.00;

        while (artikelen.hasNext()) {
            Artikel artikel = artikelen.next();
            FactuurRegel regel = new FactuurRegel(this, artikel);
            regels.add(regel);

            if (artikel.getKorting() != 0) {
                double dagKorting = artikel.getKorting();
                korting += dagKorting;
                totaal += artikel.getPrijs();
            } else if(klant instanceof KortingskaartHouder){
                kaarthouderKorting += totaal * ((KortingskaartHouder) klant).geefKortingsPercentage();
                totaal += artikel.getPrijs();
            }else totaal += artikel.getPrijs();
        }

        if(klant instanceof KortingskaartHouder && ((KortingskaartHouder) klant).heeftMaximum()){
            if(kaarthouderKorting > ((KortingskaartHouder) klant).geefMaximum()){
                kaarthouderKorting = ((KortingskaartHouder) klant).geefMaximum();
                korting += kaarthouderKorting; }
            }else if(klant instanceof KortingskaartHouder){
            korting += kaarthouderKorting; }
    }

    /**
     *
     * @return bonnetje van de bestelling
     */
    @Override
    public String toString() {
        return "Factuur{" +
                "regels=" + regels +
                '}';
    }

    /**
     *
     * @return de korting
     */
    public double getKorting() { return korting; }
    /**
     *
     * @return het totaalbedrag
     */
    public double getTotaal() { return totaal; }

}
