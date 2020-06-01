public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public boolean betaal(double tebetalen) {
      if (saldo > tebetalen) {
        setSaldo(saldo - tebetalen);
        return true;
      } else return false;
    }
}
