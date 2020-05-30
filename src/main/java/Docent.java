public class Docent extends Persoon{

    private String afkorting;
    private String afdeling;

    public Docent(){} //lege constructor

    public Docent(long BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, String afkorting, String afdeling) {
        super(BSN, voornaam, achternaam, geboortedatum, geslacht);
        this.afkorting = afkorting;
        this.afdeling = afdeling;
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
