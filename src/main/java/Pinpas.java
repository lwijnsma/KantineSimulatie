public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     *
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet=kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */
    public boolean betaal(double tebetalen) {
      if(saldo > tebetalen){
       if(tebetalen <= kredietlimiet){
          setSaldo(saldo-tebetalen);
          return true;
        } else return false;

      } else return false;
    }
}
