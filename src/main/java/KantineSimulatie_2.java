import java.text.DecimalFormat;
import java.util.*;

public class KantineSimulatie_2 {

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    private double[] omzet;

    private int[] aantal;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // aantqal dagen
    private static final int DAGEN = 10;

    // deze code maakt een template aan waardoor getallen tot 2 achter de comma uitgeprint kunnen worden
    private DecimalFormat df = new DecimalFormat("#######.00");

    // artikelen
    private static final String[] artikelnamen =
            new String[] {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[] {1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10000;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    /**
     * Constructor
     *
     */
    public KantineSimulatie_2() {
        kantine = new Kantine();
        random = new Random();
        int[] hoeveelheden =
                getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max van de gegeven lengte te
     * genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for (int i = 0; i < lengte; i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de bijhorende array
     * van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        omzet = new double[dagen];
        aantal = new int[dagen];
        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {

            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = 100;

            // laat de personen maar komen...
            for (int j = 0; j < aantalpersonen; j++) {

                // maak persoon en dienblad aan, koppel ze
                // en bedenk hoeveel artikelen worden gepakt
                int aantalartikelen = 3 ;
                
                int kans = getRandomValue(1, 100);

                if (kans >= 1 && kans <= 89) {
                    Persoon student = new Student();
                    Dienblad dienblad = new Dienblad(student);

                    System.out.println(student + " komt de kantine binnen");

                    // genereer de "artikelnummers", dit zijn indexen
                    // van de artikelnamen
                    int[] tepakken = getRandomArray(
                    aantalartikelen, 0, AANTAL_ARTIKELEN-1);
    
                    // vind de artikelnamen op basis van
                    // de indexen hierboven
                    String[] artikelen = geefArtikelNamen(tepakken);
    
                    // loop de kantine binnen, pak de gewenste
                    // artikelen, sluit aan
                    kantine.loopPakSluitAan(dienblad, artikelen);

                } else if (kans >= 90 && kans <= 99) {
                    Persoon docent = new Docent();
                    Dienblad dienblad = new Dienblad(docent);

                    System.out.println(docent + " komt de kantine binnen");

                    // genereer de "artikelnummers", dit zijn indexen
                    // van de artikelnamen
                    int[] tepakken = getRandomArray(
                    aantalartikelen, 0, AANTAL_ARTIKELEN-1);
    
                    // vind de artikelnamen op basis van
                    // de indexen hierboven
                    String[] artikelen = geefArtikelNamen(tepakken);
    
                    // loop de kantine binnen, pak de gewenste
                    // artikelen, sluit aan
                    kantine.loopPakSluitAan(dienblad, artikelen);

                } else {
                    Persoon kantinemMedewerker = new KantineMedewerker();
                    Dienblad dienblad = new Dienblad(kantinemMedewerker);

                    System.out.println(kantinemMedewerker + " komt de kantine binnen");

                    // genereer de "artikelnummers", dit zijn indexen
                    // van de artikelnamen
                    int[] tepakken = getRandomArray(
                    aantalartikelen, 0, AANTAL_ARTIKELEN-1);
    
                    // vind de artikelnamen op basis van
                    // de indexen hierboven
                    String[] artikelen = geefArtikelNamen(tepakken);
    
                    // loop de kantine binnen, pak de gewenste
                    // artikelen, sluit aan
                    kantine.loopPakSluitAan(dienblad, artikelen);
                }
            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            // hier worden variabelen aangemaakt om later te printen en in een array te zetten
            double dagOmzet = kantine.getKassa().hoeveelheidGeldInKassa();
            int dagVerkopen = kantine.getKassa().aantalArtikelen();
            //hier worden de hierboven gemaakte variabelen in een array gezet
            omzet[i] = dagOmzet;
            aantal[i] = dagVerkopen;
            // druk de dagtotalen af en hoeveel personen binnen
            // zijn gekomen
            System.out.println("------ dag "+(i+1)+" ------");
            System.out.println("Aantalpersonen = " + aantalpersonen);
            System.out.println("Aantal artikelen = " + dagVerkopen);
            System.out.println("Hoeveelheid geld in kassa = " + df.format(dagOmzet));
            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();

        }

        //print de gemiddelden over de gesimmuleerde periode
      Administratie administratie = new Administratie();
      System.out.println("---------- Gemiddelden ----------");
      System.out.println("Gemiddelde omzet = " + df.format(administratie.berekenGemiddeldeOmzet(omzet)));
      System.out.println("Gemiddeld aantal artikelen per dag = " + administratie.berekenGemiddeldAantal(aantal));

      //TODO werkt nog niet helemaal print random characters
      //System.out.println("Dag omzet = " + Administratie.berekenDagOmzet(omzet));
    }

    public static void main(String[] args) {
        int dagen;
        KantineSimulatie_2 kantineSimulatie = new KantineSimulatie_2();
        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }
        kantineSimulatie.simuleer(dagen);

    }
}

