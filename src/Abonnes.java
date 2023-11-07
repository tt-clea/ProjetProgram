import java.util.List;




/**
 *Allows to build a subscriber 
 */

public class Abonnes {
	private String nomAb;
	private String dateNaissanceAb;
	private String sexeAb;
	private String fourchetteRevenus;
	private final List<DateLocation> dateLocation;
	
	


	
	
	/**
	 * Constructor of the class of 'Abonnes'
	 * @param nomAb
	 * @param dateNaissanceAb
	 * @param sexeAb
	 * @param fourchetteRevenus
	 * @param dateLocation
	 */
	public Abonnes(String aNomAb, String aDateNaissanceAb, String aSexeAb, String aFourchetteRevenus,
			List<DateLocation> aDateLocation) {
		super();
		this.nomAb = aNomAb;
		this.dateNaissanceAb = aDateNaissanceAb;
		this.sexeAb = aSexeAb;
		this.fourchetteRevenus = aFourchetteRevenus;
		this.dateLocation = aDateLocation;
	}




	/**
	 * @return the dateLocation
	 */
	public List<DateLocation> getDateLocation() {
		return dateLocation;
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
	 * @param fourchetteRevenus the fourchetteRevenus to set
	 */
	public void setFourchetteRevenus(String aFourchetteRevenus) {
		this.fourchetteRevenus = aFourchetteRevenus;
	}
	
	
	
}
