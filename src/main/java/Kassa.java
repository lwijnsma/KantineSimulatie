import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Kassa {

    private int aantalTotaal;
    private double geldTotaal;
    private double kortingTotaal;
    private KassaRij kassaRij;
    private  EntityManager manager;

  /**
     * Constructor
     */
    public Kassa(KassaRij kassaRij,EntityManager manager) {
        this.kassaRij = kassaRij;
        this.manager = manager;

    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param dienblad van klant die moet afrekenen
     */
    public void rekenAf(Dienblad dienblad){
        EntityTransaction transaction = null;
        Factuur factuur = new Factuur(dienblad,LocalDate.now());
        double tebetalen = factuur.getTotaal() - factuur.getKorting();
        try{
            dienblad.getKlant().getBetaalwijze().betaal(tebetalen);
            geldTotaal += tebetalen;
            aantalTotaal += factuur.getAantalArtikelen();
            kortingTotaal += factuur.getKorting();

            // database transactie
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(factuur);
            transaction.commit();

        }
        catch (TeWeinigGeldException geld){
            System.out.println(dienblad.getKlant().getVoornaam()+" "+geld);
        }
        catch(Exception ex){
            //rollback als de transactie mislukt is
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }

    }



    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return aantalTotaal;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *keys
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return geldTotaal;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public double totaalekorting(){return kortingTotaal;}

    public void resetKassa() {
        aantalTotaal = 0;
        geldTotaal = 0;
        kortingTotaal = 0;
    }

}
