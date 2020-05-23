import java.util.Random;

public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt en aan elkaar gekoppeld. Maak twee
     * Artikelen aan en plaats deze op het dienblad. Tenslotte sluit de Persoon zich aan bij de rij
     * voor de kassa.
     *
     *
     */
    //TODO geef persoon en artikel waarden

    public void loopPakSluitAan() {
        Random rand = new Random();
        Datum datum = new Datum(1, 1, 2003);
        Persoon persoon = new Persoon(rand.nextInt(10),"John", "Doh", datum, 'M');
        Dienblad dienblad = new Dienblad(persoon);
        Artikel artikel1 = new Artikel("friet",10.00);
        Artikel artikel2 = new Artikel("cola", 0.80);
        dienblad.voegToe(artikel1);
        dienblad.voegToe(artikel2);
        kassarij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while (kassarij.erIsEenRij()) {
            kassa.rekenAf(kassarij.eerstePersoonInRij());
        }
    }

    public Kassa getKassa() {
        return kassa;
    }
}
