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
     * @param dienblad, artikelnamen[]
     */

    public void loopPakSluitAan(Dienblad dienblad,String[] artikelnamen,String dagaanbieding) {
        for (String artikelnaam : artikelnamen) {
            Artikel artikel = kantineaanbod.getArtikel(artikelnaam);
            if(artikel.getNaam().equals(dagaanbieding)){
                artikel.setKorting((artikel.getPrijs()*0.2));
            }
            dienblad.voegToe(artikel);
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
