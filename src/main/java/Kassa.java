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
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Iterator<Artikel> dienblad = klant.getDienblad();
        Betaalwijze betaalwijze = klant.getKlant().getBetaalwijze();
        double tebetalen =0;
        while (dienblad.hasNext()) {
        aantalTotaal++;
        tebetalen += dienblad.next().getPrijs();
        }
        if(betaalwijze.betaal(tebetalen)){
          geldTotaal += tebetalen;
        }else System.out.println("Betaling mislukt");

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
