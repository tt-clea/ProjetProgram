import java.util.*;

public class Coffret {
    private String titreC;
    private String genre;
    private List<Film> filmlist;

    private boolean bonus;// 1 est bonus , 0 est non bonus



    public Coffret(String titreC, boolean bonus) {
        this.titreC = titreC;
        this.bonus = bonus;
        this.filmlist=new ArrayList<>();
        this.genre=collection_genre();
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


    public String getTitreC() {
        return titreC;
    }

    public String getGenre() {
        return collection_genre();
    }


    public List<Film> getFilmlist() {
        return filmlist;
    }


    public boolean isBonus() {
        return bonus;
    }

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
