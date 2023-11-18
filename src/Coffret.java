import java.io.Serializable;
import java.util.*;

public class Coffret  implements Serializable{
    private static final long serialVersionUID = 3L;
	private String titreC;
    private String genre;
    private List<Film> filmlist;

    private boolean bonus;// 1 = bonus , 0 = no bonus

    /**
     * Represents a collection of films, known as a Coffret.
     * Allows the creation and manipulation of Coffret objects.
     */

    public Coffret(String titreC, boolean bonus,String genre) {
        this.titreC = titreC;
        this.bonus = bonus;
        this.filmlist=new ArrayList<>();
        this.genre=genre;
    }
    /**
     * set genre of coffret as same as film;
     */
    private String collection_genre()
    {
        if(!filmlist.isEmpty())
        {
            return this.genre=filmlist.get(0).getGenre();
        }
        return null;
    }


    /**
     * return title of Coffret
     * @return
     */
    public String getTitreC() {
        return titreC;
    }

    /**
     * get genre of coffret
     * @return
     */
    public String getGenre() {
        return genre;
    }


    /**
     * get list of films
     * @return
     */
    public List<Film> getFilmlist() {
        return filmlist;
    }


    /**
     * if has bonus for coffret
     * @return
     */
    public boolean isBonus() {
        return bonus;
    }

    /**
     * set titre of coffret
     * @param titreC
     */
    public void setTitreC(String titreC) {
        this.titreC = titreC;
    }
    /**
     * Checks if two Coffrets are equal based on their attributes.
     *
     * @param o Object to compare.
     * @return True if the Coffrets are equal, false otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffret coffret = (Coffret) o;
        return bonus == coffret.bonus && Objects.equals(titreC, coffret.titreC) && Objects.equals(genre, coffret.genre) && Objects.equals(filmlist, coffret.filmlist);
    }
    /**
     * Generates a hash code for the Coffret based on its attributes.
     *
     * @return The hash code of the Coffret.
     */

    @Override
    public int hashCode() {
        return Objects.hash(titreC, genre, filmlist, bonus);
    }
    
    /**
     * Returns a string representation of the Coffret.
     *
     * @return A string containing the title, genre, list of films, and bonus status of the Coffret.
     */

    @Override
    public String toString() {
        return "Coffret{" +
                "titreC='" + titreC + '\'' +
                ", genre='" + genre + '\'' +
                ", filmlist=" + filmlist +
                ", bonus=" + bonus +
                '}';
    }
}