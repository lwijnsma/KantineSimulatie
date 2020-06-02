public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
      if (saldo > tebetalen) {
        setSaldo(saldo - tebetalen);
      } else throw new TeWeinigGeldException("saldo is telaag");
    }
}
