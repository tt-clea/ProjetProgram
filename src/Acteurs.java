import java.io.Serializable;

/**
 * A class to represent the "Acteurs"
 */


public class Acteurs implements Serializable {
	
	private static final long serialVersionUID = 2L;
	private String prenomA;
	private String nomA;
	
	/**
	 * Constructor of the class of 'Acteurs'
	 * @param aPrenomA
	 * @param aNomA
	 */
	 public Acteurs(String aPrenomA, String aNomA) {
	        this.prenomA = aPrenomA;
	        this.nomA = aNomA;
	        
	    }
	
	
	
	/**
	 * @return the prenomA
	 */
	public String getPrenomA() {
		return prenomA;
	}
	/**
	 * @param aPrenomA the prenomA to set
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
	 * @param aNomA the nomA to set
	 */
	public void setNomA(String aNomA) {
		this.nomA = aNomA;
	}

	@Override
	public String toString() {
		return "Acteurs [prenomA=" + prenomA + ", nomA=" + nomA + "]";
	}

}
