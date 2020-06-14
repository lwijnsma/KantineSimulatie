public class Persoon {

    private long BSN;
    private String voornaam;
    private String achternaam;
    private Datum geboortedatum;
    private char geslacht;
    private Betaalwijze betaalwijze;


  public Persoon() {} // Lege constructor

  /*
  *Consturctor van de klasse Persoon
   */
    public Persoon(long BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht) {
        this.BSN = BSN;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        setGeslacht(geslacht);
    }

    public String toString() {
    return "Persoon{" +
      "BSN=" + BSN +
      ", voornaam='" + voornaam + '\'' +
      ", achternaam='" + achternaam + '\'' +
      ", geboortedatum=" + geboortedatum +
      ", geslacht=" + geslacht +
      '}';
  }

  public void setBSN(long BSN) {
        this.BSN = BSN;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Datum geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setGeslacht(char geslacht) {
        if (geslacht == 'M' || geslacht == 'V') {
            this.geslacht = geslacht;
        } else this.geslacht = 'O';
    }

    public long getBSN() {
        return BSN;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getGeboortedatum() {
        return geboortedatum.getDatumAsString();
    }

    public String getGeslacht() {
        if (geslacht == 'M') return "Man";
        else if (geslacht == 'V') return "Vrouw";
        else return "Onbekend";
    }
    public Betaalwijze getBetaalwijze() {
      return betaalwijze;
    }

    public void setBetaalwijze(Betaalwijze betaalwijze) {
      this.betaalwijze = betaalwijze;
    }
}
