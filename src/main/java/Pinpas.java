public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     *
     * @param kredietlimiet het krediet limiet van de pas
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet=kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
      if(saldo > tebetalen){
       if(tebetalen <= kredietlimiet){
          setSaldo(saldo-tebetalen);
        } else throw new TeWeinigGeldException("betaling past niet binnen kredietlimiet");
      } else throw new TeWeinigGeldException("saldo is telaag");
    }
}
