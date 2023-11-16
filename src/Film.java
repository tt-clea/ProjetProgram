import java.util.*;

public class Film {
	private String titreF;
	private boolean couleurF;
	private int NbStockage;
	private List<Acteurs> acteursList;

	private String genre;


	public Film()
	{
	}

	/**
	 * @param titreF
	 * @param couleurF
	 * @param genre
	 */
	public Film(String titreF, boolean couleurF, String  genre,int nbStockage) {
		this.titreF = titreF;
		this.couleurF = couleurF;
		this.acteursList = new ArrayList<>();

		this.NbStockage=nbStockage;
		this.genre=genre;
	}

	public Film(String titreF, boolean couleurF,String genre, Acteurs acteurs,int nbStockage) {
		this.titreF = titreF;
		this.couleurF = couleurF;
		this.acteursList = new ArrayList<>();
		this.genre = genre;
		this.NbStockage=nbStockage;
	}

	/**
	 * @return the titreF
	 */
	public String getTitreF() {
		return titreF;
	}
	/**
	 * @return the couleurF
	 */
	public boolean isCouleurF() {
		return couleurF;
	}
	/**
	 * @return the list of acteurs
	 */
	public List<Acteurs> getActeursList()
	{
		return acteursList;
	}
	/**
	 * @return the nbStockage
	 */
	public int getNbStockage() {
		return NbStockage;
	}

	/**
	 * @param nbStockage the nbStockage to set
	 */
	public void setNbStockage(int nbStockage) {
		NbStockage = nbStockage;
	}



	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Film film = (Film) o;
		return couleurF == film.couleurF && NbStockage == film.NbStockage && Objects.equals(titreF, film.titreF) && Objects.equals(acteursList, film.acteursList) && Objects.equals(genre, film.genre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(titreF, couleurF, NbStockage, acteursList, genre);
	}

	@Override
	public String toString() {
		return "Film{" +
				"titreF='" + titreF + '\'' +
				", couleurF=" + couleurF +
				", NbStockage=" + NbStockage +
				", acteursList=" + acteursList +
				", genre='" + genre + '\'' +
				'}';
	}
}