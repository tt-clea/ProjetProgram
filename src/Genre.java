import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.util.HashMap;

/**
 * Represents a genre.
 * Allows the creation and manipulation of genre objects.
 */

public class Genre implements Serializable{
	private static final long serialVersionUID = 5L;
	private String genreNom;
	private List<Genre> subGenre;
	
	/**
     * Constructor to initialize a genre with the specified name.
     *
     * @param genreNom The name of the genre.
     */
	public Genre(String genreNom) {
		this.genreNom=genreNom;
		this.subGenre=new ArrayList<>();
	}
	
	
	
	/**
     * Gets the name of the genre.
     *
     * @return The name of the genre.
     */
	public String getGenreNom() {
		return genreNom;
	}
	
	/**
     * Gets the list of sub-genres.
     *
     * @return The list of sub-genres.
     */
	public List<Genre> getSubGenre() {
		return subGenre;
	}
	
	/**
     * Adds a sub-genre to the list of sub-genres.
     *
     * @param sub The sub-genre to add.
     */
	public void addSubGenre(Genre sub)
	{
		subGenre.add(sub);
	}
	
	/**
     * Sets the name of the genre.
     *
     * @param genreNom The name to set.
     */
	public void setGenreNom(String genreNom) {
		this.genreNom = genreNom;
	}
	
	/**
     * Sets the list of sub-genres.
     *
     * @param subGenre The list of sub-genres to set.
     */
	public void setSubGenre(List<Genre> subGenre) {
		this.subGenre = subGenre;
	}
	
	/**
     * Checks if two genres are equal based on their attributes.
     *
     * @param o Object to compare.
     * @return True if the genres are equal, false otherwise.
     */
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Genre genre = (Genre) o;
		return Objects.equals(genreNom, genre.genreNom) && Objects.equals(subGenre, genre.subGenre);
	}
	
	/**
     * Generates a hash code for the genre based on its attributes.
     *
     * @return The hash code of the genre.
     */
	
	@Override
	public int hashCode() {
		return Objects.hash(genreNom, subGenre);
	}
	
	
	/**
     * Returns a string representation of the genre.
     *
     * @return A string containing the name of the genre and its list of sub-genres.
     */
	
	
	@Override
	public String toString() {
		return genreNom+":"+subGenre;
	}
}