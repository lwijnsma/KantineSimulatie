import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Artikel implements Serializable {

    @Column(name = "Artikel_naam")
    private String naam;
    @Column(name = "artikel_prijs")
    private double prijs;
    @Column(name = "artikel_korting")
    private double korting;

    public Artikel(String naam, double prijs, double korting) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = korting;
    }

    public Artikel(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = 0.00;
    }

    public Artikel() {

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

    public double getPrijs() { return prijs; }

    public void setPrijs(double prijs) { this.prijs = prijs; }

    public double getKorting() { return korting; }

    public void setKorting(double korting) { this.korting = korting; }
}
