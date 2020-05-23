public class KantineSimulatie_1 {

    private Kantine kantine;

    public static final int DAGEN = 7;

    /**
     * Constructor
     */
    public KantineSimulatie_1() {
        kantine = new Kantine();
    }

    /**
     * Deze methode simuleert een aantal dagen in het
     * verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {

        // herhaal voor elke dag
        for (int i = 0; true ;i++) {

            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd.

            // for lus voor personen
            for (int j = 0; j < 10 + i; j++) {
                kantine.loopPakSluitAan();
            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            // toon dagtotalen (artikelen en geld in kassa)
            System.out.println(kantine.getKassa().aantalArtikelen());
            System.out.println(kantine.getKassa().hoeveelheidGeldInKassa());
            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
        }
    }

    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
        int dagen;
        KantineSimulatie_1 kantineSimulatie = new KantineSimulatie_1();

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }
        kantineSimulatie.simuleer(dagen);
    }
}
