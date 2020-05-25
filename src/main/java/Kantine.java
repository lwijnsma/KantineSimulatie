import java.util.Random;

public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineaanbod;

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
    }

    /**
     * 2
     * In deze methode wordt een dienblad met artikelen
     * in de kassarij geplaatst.
     *
     * @paramdienblad
     */

    public void loopPakSluitAan(Dienblad dienblad,String[] artikelnamen) {
        int size = artikelnamen.length;
        for (int i=0;i<size ;i++){
            dienblad.voegToe(kantineaanbod.getArtikel(artikelnamen[i]));
        }
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

    public KantineAanbod getKantineAanbod() { return kantineaanbod; }

    public void setKantineAanbod(KantineAanbod kantineaanbod) { this.kantineaanbod = kantineaanbod; }
    
}
