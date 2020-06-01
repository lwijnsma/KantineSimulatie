public class Administratie {

    private static final int DAYS_IN_WEEK = 7;

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public double berekenGemiddeldAantal(int[] aantal) {
        int aantalGetallen = aantal.length;
        int totaal = 0;
        for(int getal : aantal){
            totaal = totaal + getal;
        }
        if (totaal != 0 && aantalGetallen != 0) {
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
        if (totaal != 0 && aantalGetallen != 0) {
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
        double[] temp = new double[7];
        for(int i = 0; i < DAYS_IN_WEEK; i++) {
            int j = 0;
            while (j % 7 == 0) {
                temp[i] += omzet[i + DAYS_IN_WEEK * j];
                j++;
            }
        }
        return temp;
    }
}
