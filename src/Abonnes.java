import java.util.*;


/**
 *Allows to build a subscriber 
 */

public class Abonnes {


	private String nomAb;
	private String prenomAb;
	private String dateNaissanceAb;
	private String sexeAb;
	private int fourchetteRevenus;
	private final List<Film> locationFilm;
	private Map<Film, DateLocation> location; // History of Subscriber Rentals

	public Abonnes(String nomAb, String prenomAb, String dateNaissanceAb, String sexeAb, int fourchetteRevenus) {
		this.nomAb = nomAb;
		this.prenomAb = prenomAb;
		this.dateNaissanceAb = dateNaissanceAb;
		this.sexeAb = sexeAb;
		this.fourchetteRevenus = fourchetteRevenus;
		this.locationFilm = new ArrayList<>();
		this.location = new HashMap<>();
	}




	/**
	 * @return the prenomAb
	 */
	public String getPrenomAb() {
		return prenomAb;
	}




	/**
	 * @param prenomAb the prenomAb to set
	 */
	public void setPrenomAb(String prenomAb) {
		this.prenomAb = prenomAb;
	}

	
	
	
	/**
	 * @return the nomAb
	 */
	public String getNomAb() {
		return nomAb;
	}
	/**
	 * @param nomAb the nomAb to set
	 */
	public void setNomAb(String aNomAb) {
		this.nomAb = aNomAb;
	}
	/**
	 * @return the dateNaissanceAb
	 */
	public String getDateNaissanceAb() {
		return dateNaissanceAb;
	}
	/**
	 * @param dateNaissanceAb the dateNaissanceAb to set
	 */
	public void setDateNaissanceAb(String aDateNaissanceAb) {
		this.dateNaissanceAb = aDateNaissanceAb;
	}
	/**
	 * @return the sexeAb
	 */
	public String getSexeAb() {
		return sexeAb;
	}
	/**
	 * @param sexeAb the sexeAb to set
	 */
	public void setSexeAb(String aSexeAb) {
		this.sexeAb = aSexeAb;
	}
	/**
	 * @return the fourchetteRevenus
	 */
	public int getFourchetteRevenus() {
		return fourchetteRevenus;
	}
	
	/**
	 * @return the locationFilm
	 */
	public List<Film> getLocationFilm() {
		return locationFilm;
	}
	/**
	 * @param fourchetteRevenus the fourchetteRevenus to set
	 *
	 *Method to set the income bracket based on the income value
	 */
    public void setFourchetteRevenus(int aFourchetteRevenus) {
        this.fourchetteRevenus=aFourchetteRevenus;
    }
    
 





	@Override
	public String toString() {
		return "Abonnes [prenomAb=" + prenomAb + ",  nomAb=" + nomAb + ", dateNaissanceAb=" + dateNaissanceAb + ", sexeAb=" + sexeAb
				+ ", fourchetteRevenus=" + fourchetteRevenus + ", locationFilm="
				+ locationFilm + "]";
	}




	// Implementation of the hashCode method to enable hashing of the Abonnes object
	@Override
	public int hashCode() {
	    return Objects.hash(prenomAb,nomAb, dateNaissanceAb, sexeAb, fourchetteRevenus);
	}
	


	
	



	
	
	
	
}
