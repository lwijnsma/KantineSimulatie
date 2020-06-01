public class Administratie {

    private static final int DAYS_IN_WEEK = 7;

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public double berekenGemiddeldAantal(int[] aantal) {
        //cast ints naar double
        double aantalGetallen = aantal.length;
        double totaal = 0;
        //tel alle aantallen in de array bijelkaar op
        for(int getal : aantal){
            totaal = totaal + getal;
        }
        if (totaal != 0 && aantalGetallen != 0) {
          //bereken gemiddelde

          return totaal / aantalGetallen;
        }
        else
            return 0;
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */
    public double berekenGemiddeldeOmzet(double[] omzet) {
        int aantalGetallen = omzet.length;
        double totaal = 0;
        for(double prijs : omzet){
            totaal += prijs;
        }
      //tel alle prijzen in de array bijelkaar op
        if (totaal != 0 && aantalGetallen != 0) {
            //bereken gemiddelde
            return totaal / aantalGetallen;
        }
        else
            return 0;
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp = new double[DAYS_IN_WEEK];
        for(int i = 0; i < DAYS_IN_WEEK; i++) {
            int j = 0;
            while (omzet.length >i + j*DAYS_IN_WEEK) {
                //voeg omzet toe
                temp[i] += omzet[i + DAYS_IN_WEEK * j];
                //naar nieuwe week
                j++;
            }

        }
        return temp;
    }
}
