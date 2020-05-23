import java.util.ArrayList;
import java.util.LinkedList;

public class KassaRij {

    private LinkedList<Dienblad> rij;
    
    /**
     * Constructor
     */
    public KassaRij() {
        
    }

    public KassaRij(LinkedList<Dienblad> rij) {
        this.rij = rij;
    }

    /**
     * Persoon sluit achter in de rij aan
     *
     * @param klant
     */
    public void sluitAchteraan(Dienblad klant) {
        rij.add(klant);
    }

    /**
     * Indien er een rij bestaat, de eerste klant uit de rij verwijderen en retourneren. Als er
     * niemand in de rij staat geeft deze null terug.
     *
     * @return Eerste klant in de rij of null
     */
    public Dienblad eerstePersoonInRij() {
        if (!rij.isEmpty()) {
            return rij.remove(0);
        } else return null;
    }

    /**
     * Methode kijkt of er personen in de rij staan.
     *
     * @return Of er wel of geen rij bestaat
     */
    public boolean erIsEenRij() {
        return !rij.isEmpty();
    }
}
