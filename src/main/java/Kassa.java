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
        Betaalwijze betaalwijze = dienblad.getKlant().getBetaalwijze();
        Persoon klant = dienblad.getKlant();
        double tebetalen =0;

        while (artikelen.hasNext()) {
            aantalTotaal++;
            Artikel artikel = artikelen.next();
            if (artikel.getKorting() != 0) {
                tebetalen += (artikel.getPrijs() - artikel.getKorting());
                break;
            } else tebetalen += artikel.getPrijs();

            if (klant instanceof KortingskaartHouder) {
                if (((KortingskaartHouder) klant).heeftMaximum()) {
                    double maximum = ((KortingskaartHouder) klant).geefMaximum();
                    double korting = tebetalen * ((KortingskaartHouder) klant).geefKortingsPercentage();
                    if (korting > maximum) {
                        tebetalen = tebetalen - korting;
                    } else tebetalen = tebetalen * ((KortingskaartHouder) klant).geefKortingsPercentage();
                } else tebetalen = tebetalen * ((KortingskaartHouder) klant).geefKortingsPercentage();
            }
        }
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
