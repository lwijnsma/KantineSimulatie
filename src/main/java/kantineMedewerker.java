public class kantineMedewerker extends Persoon{

    private int medewerkerNR;
    private boolean achterKassa; //geeft aan of de medewerker achter de kassa mag

    public kantineMedewerker(){} //lege constructor

    public kantineMedewerker(long BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int medewerkerNR, boolean achterKassa) {
        super(BSN, voornaam, achternaam, geboortedatum, geslacht);
        this.medewerkerNR = medewerkerNR;
        this.achterKassa = achterKassa;
    }

  @Override
  public String toString() {
    return "kantineMedewerker{" +
      "medewerkerNR=" + medewerkerNR +
      ", achterKassa=" + achterKassa +
      '}';
  }

  public int getMedewerkerNR() {
        return medewerkerNR;
    }

    public void setMedewerkerNR(int medewerkerNR) {
        this.medewerkerNR = medewerkerNR;
    }

    public boolean isAchterKassa() {
        return achterKassa;
    }

    public void setAchterKassa(boolean achterKassa) {
        this.achterKassa = achterKassa;
    }
}
