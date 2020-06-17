import java.time.LocalDate;
import java.io.Serializable;
import java.util.Iterator;

public class Factuur implements Serializable {

    private Long id;

    private LocalDate datum;

    private double korting;

    private double totaal;

    private int aantalArtikelen;

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
            aantalArtikelen++;
            Artikel artikel = artikelen.next();
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
                "datum=" + datum +
                ", korting=" + korting +
                ", totaal=" + totaal +
                '}';
    }

    public int getAantalArtikelen() {
        return aantalArtikelen;
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
