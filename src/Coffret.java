import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coffret {
    private String titreC;
    private Genre genre;
    private List<Film> filmlist;
    private Film film;
    private boolean couleurC;
    private boolean bonus;// 1 est bonus , 0 est non bonus

    private static int NOMBRE_FILMS = 10;
    private Map<String,List<Acteurs>> coffretList;

    public Coffret(String titreC, Genre genre,  boolean couleurC, boolean bonus) {
        this.titreC = titreC;
        this.genre = genre;
        this.couleurC = couleurC;
        this.bonus = bonus;
        this.filmlist=new ArrayList<>();
        coffretList=new HashMap<>();
    }


    public String getTitreC() {
        return titreC;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<Film> getFilmlist() {
        return filmlist;
    }

    public Film getFilm() {
        return film;
    }

    public boolean isCouleurC() {
        return couleurC;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setTitreC(String titreC) {
        this.titreC = titreC;
    }

    /**
     *
     * @param film
     * ajouter les films dans le coffret
     */
    public void addFilmList(Film film)
    {
        filmlist.add(film);
    }



    public Map<String,List<Acteurs>> getActeursForFilm()
    {
        for(Film f:filmlist)
        {
            List<Acteurs> acteursList = f.getActeursList();
            coffretList.put(f.getTitreF(),acteursList);
        }
        return coffretList;
    }

    @Override
    public String toString() {
        return "Coffret{" +
                "titreC='" + titreC + '\'' +
                ", genre=" + genre +
                ", filmlist=" + filmlist +
                ", film=" + film +
                ", couleurC=" + couleurC +
                ", bonus=" + bonus +
                ", coffretList=" + coffretList +
                '}';
    }

}
