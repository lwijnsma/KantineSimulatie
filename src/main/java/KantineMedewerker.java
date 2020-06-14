public class KantineMedewerker extends Persoon implements KortingskaartHouder{

    //speciale KantineMedewerker eigenschappen
    private int medewerkerNR;
    private boolean achterKassa; //geeft aan of de medewerker achter de kassa mag

    public KantineMedewerker(){} //lege constructor

    // consturctor voor KantineMedewerke die alle dingen van persoon plus die van KantineMedewerker initieert
    public KantineMedewerker(long BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int medewerkerNR, boolean achterKassa) {
          super(BSN, voornaam, achternaam, geboortedatum, geslacht);
          this.medewerkerNR = medewerkerNR;
          this.achterKassa = achterKassa;
      }

  @Override
  public double geefKortingsPercentage() {
    return 0.35;
  }

  @Override
  public boolean heeftMaximum() {
    return false;
  }

  @Override
  public double geefMaximum() {
    return 0;
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
