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

    public Coffret(String titreC, boolean bonus) {
        this.titreC = titreC;
        this.bonus = bonus;
        this.filmlist=new ArrayList<>();
        coffretList=new HashMap<>();
        this.couleurC=collection_color();
        this.genre=collection_genre();
    }


    /**
     * set color of coffret as same as film ;
     */
    private boolean collection_color()
    {
        if (!filmlist.isEmpty())
        {

            return filmlist.get(0).isCouleurF();
        }
        return false;
    }

    /**
     * set genre of coffret as same as film;
     */
    private Genre collection_genre()
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

    public Genre getGenre() {
        return collection_genre();
    }

    public boolean isCouleurC() {
        return collection_color();
    }


    public List<Film> getFilmlist() {
        return filmlist;
    }

    public Film getFilm() {
        return film;
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



//    public Map<String,List<Acteurs>> getActeursForFilm()
//    {
//        for(Film f:filmlist)
//        {
//            List<Acteurs> acteursList = f.getActeursList();
//            coffretList.put(f.getTitreF(),acteursList);
//        }
//        return coffretList;
//    }

    @Override
    public String toString() {
        return "Coffret{" +
                "titreC='" + titreC + '\'' +

                ", filmlist=" + filmlist +
                ", film=" + film +

                ", bonus=" + bonus +
                ", coffretList=" + coffretList +
                '}';
    }




}
