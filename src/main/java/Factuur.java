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

    @Column(name = "artikelen")
    private int aantalArtikelen;

    @OneToMany(targetEntity = FactuurRegel.class, mappedBy = "factuur", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<FactuurRegel> regels = new ArrayList<>();

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
        //hier word de tijdelijke kaarhouder korting variabele geinitaliseerd i.v.m. maximum
        double kaarthouderKorting = 0.00;

        // loopt door de artikelen op het dienblad
        while (artikelen.hasNext()) {
            aantalArtikelen++;
            Artikel artikel = artikelen.next();
            //word een factuur regel gemaakt voor het betrefende artikel
            FactuurRegel regel = new FactuurRegel(this, artikel);
            regels.add(regel);

            /*
             * kijkt of er dag korting is
             * als er dagkorting is word die togepast en gaat de loop verder
             * als die er niet is word er gekeken of er recht is op kortingskaart houder korting en word dat toegepast
             * als dart er niet is word de normale prijs gerekend.
             */
            if (artikel.getKorting() != 0) {
                double dagKorting = artikel.getKorting();
                korting += dagKorting;
                totaal += artikel.getPrijs();
            } else if(klant instanceof KortingskaartHouder){
                kaarthouderKorting += totaal * ((KortingskaartHouder) klant).geefKortingsPercentage();
                totaal += artikel.getPrijs();
            }else totaal += artikel.getPrijs();
        }

        //hier word gekeken of er een maximum kaart houder korting is en daarom gehandhaafd

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
