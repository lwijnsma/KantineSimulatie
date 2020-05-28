import java.util.Iterator;
import java.util.Stack;

public class Dienblad {
    private Stack<Artikel> artikelen;
    private Persoon klant;
    
    /**
     * Constructor
     */
    public Dienblad() {
        // method body omitted
    }

    public Dienblad(Persoon klant) {
        this.klant = klant;
    }
    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
        artikelen.push(artikel);
    }

    public Persoon getKlant() {
        return klant;
    }

    public void setKlant(Persoon klant) {
        this.klant = klant;
    }
    
    public Iterator<Artikel> getDienblad() {
        return artikelen.iterator();
    }
}

