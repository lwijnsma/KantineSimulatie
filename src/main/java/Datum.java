public class Datum {

	private int dag;
	private int maand;
	private int jaar;


	/**
	 * Constructor
	 */

	public Datum(int dag, int maand, int jaar) {
		if(bestaatDatum(dag, maand, jaar)){
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		}
	}

	public boolean bestaatDatum(int dag, int maand, int jaar) {
		if(dag >= 1 && jaar > 1900 && jaar < 2100 && maand > 0 && maand < 13){
			switch(maand){
				case 1: case 3: case 5: case 7: case 8: case 10: case 12:
					return dag <= 31;
				case 4: case 6: case 9: case 11:
					return dag <= 30;
				case 2:
					if((jaar % 400) == 0) {
					return dag <= 29;
					}
					else if((jaar % 4) == 0 && (jaar % 100) != 0) {
					  return dag <= 29;
          }
					else if((jaar % 100) == 0 && ) {
						return dag <= 28;
					}
					else return false;
				default: return false;
				}
		}
		else
			return false;
	}

	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public String getDatumAsString() {
		String datumString;
		String maandString;
		switch (maand) {
			case 1: maandString = "Januari"; break;
			case 2: maandString = "Februari"; break;
			case 3: maandString = "Maart"; break;
			case 4: maandString = "April"; break;
			case 5: maandString = "Mei"; break;
			case 6: maandString = "Juni"; break;
			case 7: maandString = "Juli"; break;
			case 8: maandString = "Augustus"; break;
			case 9: maandString = "September"; break;
			case 10: maandString = "Oktober"; break;
			case 11: maandString = "November"; break;
			case 12: maandString = "December"; break;
			default: maandString = ""; break;
		}
		datumString = dag +" "+ maandString +" "+ jaar;
		return datumString;
	}

	public int getDag() {
		return dag;
	}

	public void setDag(int dag) {
		this.dag = dag;
	}

	public int getMaand() {
		return maand;
	}

	public void setMaand(int maand) {
		this.maand = maand;
	}

	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}
}
