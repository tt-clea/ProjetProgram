import java.util.*;

public class Coffret {
    private String titreC;
    private String genre;
    private List<Film> filmlist;

    private boolean bonus;// 1 est bonus , 0 est non bonus



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
     * return titre of Coffret
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffret coffret = (Coffret) o;
        return bonus == coffret.bonus && Objects.equals(titreC, coffret.titreC) && Objects.equals(genre, coffret.genre) && Objects.equals(filmlist, coffret.filmlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titreC, genre, filmlist, bonus);
    }

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
