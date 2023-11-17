import java.io.Serializable;
import java.util.*;
/**
 * Represents a film.
 * Allows the creation and manipulation of film objects.
 */
public class Film implements Serializable {
	private static final long serialVersionUID = 4L;
	private String titreF;
	private boolean couleurF;
	private int NbStockage;
	private List<Acteurs> acteursList;

	private String genre;


	public Film()
	{
	}
	
	/**
     * Constructor to initialize a film with the specified title, color, genre, and stock quantity.
     *
     * @param titreF     The title of the film.
     * @param couleurF   The color of the film.
     * @param genre      The genre of the film.
     * @param nbStockage The stock quantity of the film.
     */
	public Film(String titreF, boolean couleurF, String  genre,int nbStockage) {
		this.titreF = titreF;
		this.couleurF = couleurF;
		this.acteursList = new ArrayList<>();

		this.NbStockage=nbStockage;
		this.genre=genre;
	}
	/**
     * Constructor to initialize a film with the specified title, color, genre, actor, and stock quantity.
     *
     * @param titreF     The title of the film.
     * @param couleurF   The color of the film.
     * @param genre      The genre of the film.
     * @param acteurs    The actor in the film.
     * @param nbStockage The stock quantity of the film.
     */
	public Film(String titreF, boolean couleurF,String genre, Acteurs acteurs,int nbStockage) {
		this.titreF = titreF;
		this.couleurF = couleurF;
		this.acteursList = new ArrayList<>();
		this.genre = genre;
		this.NbStockage=nbStockage;
	}

	/**
     * Gets the title of the film.
     *
     * @return The title of the film.
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
     * Gets the stock quantity of the film.
     *
     * @return The stock quantity of the film.
     */
	public int getNbStockage() {
		return NbStockage;
	}

	/**
     * Sets the stock quantity of the film.
     *
     * @param nbStockage The stock quantity to set.
     */
	public void setNbStockage(int nbStockage) {
		NbStockage = nbStockage;
	}

	/**
     * Gets the genre of the film.
     *
     * @return The genre of the film.
     */

	public String getGenre() {
		return genre;
	}

	/**
     * Sets the genre of the film.
     *
     * @param genre The genre to set.
     */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/**
     * Checks if two films are equal based on their attributes.
     *
     * @param o Object to compare.
     * @return True if the films are equal, false otherwise.
     */

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Film film = (Film) o;
		return couleurF == film.couleurF && NbStockage == film.NbStockage && Objects.equals(titreF, film.titreF) && Objects.equals(acteursList, film.acteursList) && Objects.equals(genre, film.genre);
	}

	
	/**
     * Generates a hash code for the film based on its attributes.
     *
     * @return The hash code of the film.
     */
	@Override
	public int hashCode() {
		return Objects.hash(titreF, couleurF, NbStockage, acteursList, genre);
	}

	/**
     * Returns a string representation of the film.
     *
     * @return A string containing the title, color, stock quantity, list of actors, and genre of the film.
     */
	
	
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