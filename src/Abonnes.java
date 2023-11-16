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

	/**
	 *
	 * @param nomAb
	 * @param prenomAb
	 * @param dateNaissanceAb
	 * @param sexeAb
	 * @param fourchetteRevenus
	 */
	public Abonnes(String nomAb, String prenomAb, String dateNaissanceAb, String sexeAb, int fourchetteRevenus) {
		this.nomAb = nomAb;
		this.prenomAb = prenomAb;
		this.dateNaissanceAb = dateNaissanceAb;
		this.sexeAb = sexeAb;
		this.fourchetteRevenus = fourchetteRevenus;
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
	 * @param aNomAb the nomAb to set
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
	 * @param aDateNaissanceAb the dateNaissanceAb to set
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
	 * @param aSexeAb the sexeAb to set
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
	 * @param aFourchetteRevenus the fourchetteRevenus to set
	 *
	 *Method to set the income bracket based on the income value
	 */
    public void setFourchetteRevenus(int aFourchetteRevenus) {
        this.fourchetteRevenus=aFourchetteRevenus;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Abonnes abonnes = (Abonnes) o;
		return fourchetteRevenus == abonnes.fourchetteRevenus && Objects.equals(nomAb, abonnes.nomAb) && Objects.equals(prenomAb, abonnes.prenomAb) && Objects.equals(dateNaissanceAb, abonnes.dateNaissanceAb) && Objects.equals(sexeAb, abonnes.sexeAb);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomAb, prenomAb, dateNaissanceAb, sexeAb, fourchetteRevenus);
	}

	@Override
	public String toString() {
		return "Abonnes{" +
				"nomAb='" + nomAb + '\'' +
				", prenomAb='" + prenomAb + '\'' +
				", dateNaissanceAb='" + dateNaissanceAb + '\'' +
				", sexeAb='" + sexeAb + '\'' +
				", fourchetteRevenus=" + fourchetteRevenus +
				'}';
	}
}
