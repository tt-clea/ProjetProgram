/**
 * 
 */

import java.util.List;

/**
 * 
 */
public class Acteurs {
	
	private String prenomA;
	private String nomA;
	private final List<Acteurs> acteurs;
	
	 public Acteurs(String aPrenomA, String aNomA, List<Acteurs> aActeurs) {
	        this.prenomA = aPrenomA;
	        this.nomA = aNomA;
	        this.acteurs= aActeurs;
	    }
	
	
	
	/**
	 * @return the prenomA
	 */
	public String getPrenomA() {
		return prenomA;
	}
	/**
	 * @param prenomA the prenomA to set
	 */
	public void setPrenomA(String aPrenomA) {
		this.prenomA = aPrenomA;
	}
	/**
	 * @return the nomA
	 */
	public String getNomA() {
		return nomA;
	}
	/**
	 * @param nomA the nomA to set
	 */
	public void setNomA(String aNomA) {
		this.nomA = aNomA;
	}



	public List<Acteurs> getActeurs() {
		return acteurs;
	}

}
