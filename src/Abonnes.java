import java.util.List;
import java.util.Objects;






/**
 *Allows to build a subscriber 
 */

public class Abonnes {


	private String nomAb;
	private String dateNaissanceAb;
	private String sexeAb;
	private String fourchetteRevenus;
	private final List<DateLocation> dateLocation;
	private final List<Film> locationFilm;
	
	


	
	
	/**
	 * Constructor of the class of 'Abonnes'
	 * @param nomAb
	 * @param dateNaissanceAb
	 * @param sexeAb
	 * @param fourchetteRevenus
	 * @param dateLocation
	 * 
	 */
	public Abonnes(String aPrenomAb,String aNomAb, String aDateNaissanceAb, String aSexeAb, String aFourchetteRevenus,
			List<DateLocation> aDateLocation, List<Film> aLocationFilm) {
		super();
		this.prenomAb= aPrenomAb;
		this.nomAb = aNomAb;
		this.dateNaissanceAb = aDateNaissanceAb;
		this.sexeAb = aSexeAb;
		this.fourchetteRevenus = aFourchetteRevenus;
		this.dateLocation = aDateLocation;
		this.locationFilm= aLocationFilm;
	
	}




	/**
	 * @return the dateLocation
	 */
	public List<DateLocation> getDateLocation() {
		return dateLocation;
	}
	
	private String prenomAb;
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
	public String getFourchetteRevenus() {
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
        if (aFourchetteRevenus <= 1000) {
            this.fourchetteRevenus = "revenu faible";
        } else if (aFourchetteRevenus <= 2000) {
            this.fourchetteRevenus = "revenu moyen";
        } else {
            this.fourchetteRevenus = "revenu élevé";
        }
    }
    
    



	





	@Override
	public String toString() {
		return "Abonnes [prenomAb=" + prenomAb + ",  nomAb=" + nomAb + ", dateNaissanceAb=" + dateNaissanceAb + ", sexeAb=" + sexeAb
				+ ", fourchetteRevenus=" + fourchetteRevenus + ", dateLocation=" + dateLocation + ", locationFilm="
				+ locationFilm + "]";
	}




	// Implementation of the hashCode method to enable hashing of the Abonnes object
	@Override
	public int hashCode() {
	    return Objects.hash(prenomAb,nomAb, dateNaissanceAb, sexeAb, fourchetteRevenus);
	}
	
	



	
	
	
	
}
