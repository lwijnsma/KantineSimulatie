import com.sun.xml.bind.v2.schemagen.episode.Klass;

import java.util.Iterator;

public class Kassa {

    private int aantalTotaal;
    private double geldTotaal;
    private KassaRij kassaRij;

  /**
     * Constructor
     */
    public Kassa(KassaRij kassaRij) {
        this.kassaRij = kassaRij;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param dienblad van klant die moet afrekenen
     */
    public void rekenAf(Dienblad dienblad){
        Iterator<Artikel> artikelen = dienblad.getDienblad();
        Persoon klant = dienblad.getKlant();
        Betaalwijze betaalwijze = klant.getBetaalwijze();

        //hier word de tijdelijke variabeles geinitaliseerd
        double totaal = 0;
        double korting =0 ;
        double kaarthouderKorting = 0.00;

        // loopt door de artikelen op het dienblad
        while (artikelen.hasNext()) {
            aantalTotaal++;
            Artikel artikel = artikelen.next();
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

        //het tebetalen bedrag word bepaald door de korting van het totaal af te trekken
        double tebetalen = totaal - korting;

        /*
        *er word geprobeerd te betalen met de betaal wijze van de klant.
        * mislukt dit word er een error bericht getoond en word het tebetalen bedrag niet bij de dagtotalen gerekend.
         */
        try{
            betaalwijze.betaal(tebetalen);
            geldTotaal += tebetalen;
        }
        catch (TeWeinigGeldException message){
            System.out.println(klant.getVoornaam()+" "+message);
        }

    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return aantalTotaal;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return geldTotaal;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        aantalTotaal = 0;
        geldTotaal = 0;
    }

}
