public class Artikel {

    public String naam;
    public Double prijs;

    public Artikel(String naam, Double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    public String toString() {
        return naam + " " + prijs;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }


}
