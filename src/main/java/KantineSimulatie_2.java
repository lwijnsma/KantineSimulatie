import java.text.DecimalFormat;
import java.util.*;

public class KantineSimulatie_2 {

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // omzet array
    private double[] omzet;

    //aantal artikelen array
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
    private static final int MIN_ARTIKELEN_PER_SOORT = 10;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20;

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
     * @param lengte lengte van de gewenste array
     * @param min minimum getal in de array
     * @param max maximum getal in de array
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
     * @param min minimum waarde
     * @param max maximum waarde
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de bijhorende array
     * van artikelnamen te maken
     *
     * @param indexen artikel index / artikkel nummer
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
     * @param dagen het aantal dagen dat de simmulatie runt
     */
    public void simuleer(int dagen) {
        omzet = new double[dagen];
        aantal = new int[dagen];
        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {
            System.out.println("------ dag "+(i+1)+" ------");
            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

            // laat de personen maar komen...
            for (int j = 0; j < aantalpersonen; j++) {

                // maak persoon en dienblad aan, koppel ze
                // en bedenk hoeveel artikelen worden gepakt
              int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);

                //random int generator
                int kans = random.nextInt(100);

                Persoon persoon;
                /*
                *bereken de kans die elk type persoon heeft om binnen te lopen
                *
                * Student 89 op 100
                * Docent 10 op 100
                * Medewerker 1 op 100
                 */
                if (kans >= 0 && kans <= 89) { persoon = new Student(1, null, null, null,'M', "HBO-ICT", getRandomValue(100000,999999)); }
                else if (kans >= 90 && kans < 99) { persoon = new Docent(1, null, null, null,'M', "DOC","SCMI");
                }
                else if (kans == 99) { persoon = new KantineMedewerker(1, null, null, null,'M', getRandomValue(100000,999999),false); }
                else persoon = new Persoon();
                Betaalwijze betaalwijze = new Contant();

                //TODO tijdelijk !!
                //set betaalwijze voor persoon
                persoon.setBetaalwijze(betaalwijze);
                persoon.getBetaalwijze().setSaldo(5);
                persoon.setVoornaam("John");
                persoon.setAchternaam("Doe");
                persoon.setGeslacht('M');
                Datum datum = new Datum(1,1,2001);
                persoon.setGeboortedatum(datum);
                //TODO tijdelijk !!

                    //koppel dienblad aan persoon
                    Dienblad dienblad = new Dienblad(persoon);

                    System.out.println(persoon + " komt de kantine binnen");

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
            System.out.println("------ Dag totalen ------");
            System.out.println("Aantalpersonen = " + aantalpersonen);
            System.out.println("Aantal artikelen = " + dagVerkopen);
            System.out.println("Hoeveelheid geld in kassa = " + df.format(dagOmzet));
            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();

        }

        //print de gemiddelden over de gesimmuleerde periode
      System.out.println("---------- Gemiddelden ----------");
      System.out.println("Gemiddelde omzet = " + df.format(Administratie.berekenGemiddeldeOmzet(omzet)));
      System.out.println("Gemiddeld aantal artikelen per dag = " + Administratie.berekenGemiddeldAantal(aantal));
      System.out.println("Omzet totalen per dag:");
      for(int i = 0; i< Administratie.berekenDagOmzet(omzet).length; i++){
        String dag;
        // geef een naam aan elke dag
        switch(i){
          case 0:dag = "Maandag ";break;
          case 1:dag = "Dinsdag ";break;
          case 2:dag = "Woendsdag ";break;
          case 3:dag = "Donderdag ";break;
          case 4:dag = "Vrijdag ";break;
          case 5:dag = "Zaterdag ";break;
          case 6:dag = "Zondag ";break;
          default:dag = "";break;
        }
        //print dagnaam + dagtotaal
        System.out.println(dag + df.format(Administratie.berekenDagOmzet(omzet)[i]));
      }
    }


    public static void main(String[] args) {
        // aantal dagen dat de simulatie runt
        int dagen;
        //maak nieuwe instance van de simulator

        KantineSimulatie_2 kantineSimulatie = new KantineSimulatie_2();
        //kijken of er een cli argument gegeven word

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }
        //start simulatie
        kantineSimulatie.simuleer(dagen);

    }
}

