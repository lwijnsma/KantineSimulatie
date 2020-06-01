public class Docent extends Persoon implements KortingskaartHouder{

    //speciale Docent eigenschappen
    private String afkorting;
    private String afdeling;

    public Docent(){} //lege constructor

    // consturctor voor Docent die alle dingen van persoon plus die van Docent initieert
    public Docent(long BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, String afkorting, String afdeling) {
        super(BSN, voornaam, achternaam, geboortedatum, geslacht);
        this.afkorting = afkorting;
        this.afdeling = afdeling;
    }

  @Override
  public double geefKortingsPercentage() {
    return 0;
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
    return "Docent{" +
      "afkorting='" + afkorting + '\'' +
      ", afdeling='" + afdeling + '\'' +
      '}';
  }

  public String getAfkorting() {
        return afkorting;
    }

    public void setAfkorting(String afkorting) {
        this.afkorting = afkorting;
    }

    public String getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }
}
