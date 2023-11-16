import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Evolues {
    private Abonnes abonnes1;
    private Abonnes abonnes2;
    private Film films1;
    private Film films2;


    private Coffret coffret;

    private List<Genre> genre;

    public Evolues() {


    }

    /**
     *
     * @param coffret
     */
    public Evolues(Coffret coffret)
    {
        this.coffret=coffret;
    }

    /**
     *
     * @param abonnes1
     * @param abonnes2
     * @param films1
     * @param films2
     * @param coffret
     * @param genre
     */
    public Evolues(Abonnes abonnes1, Abonnes abonnes2, Film films1, Film films2, Coffret coffret, List<Genre> genre) {
        this.abonnes1 = abonnes1;
        this.abonnes2 = abonnes2;
        this.films1 = films1;
        this.films2 = films2;
        this.coffret = coffret;
        this.genre = genre;
    }

    /**
     *
     * @param abonnes1
     * @param abonnes2
     */
    public Evolues(Abonnes abonnes1, Abonnes abonnes2) {
        this.abonnes1 = abonnes1;
        this.abonnes2=abonnes2;
    }

    /**
     *
     * @param films1
     * @param films2
     */
    public Evolues(Film films1,Film films2) {
        this.films1 = films1;
        this.films2 = films2;
        this.genre=new ArrayList<>();
    }


    /**
     * Déterminer si deux age des abonnes ont similarite ,similarite à 0, sinon 1
     * @return
     */
    public int similarites_Age(Abonnes a1,Abonnes a2)
    {
        //[0,1] Les résultats calculés sont similaires à l'intérieur de l'intervalle et dissemblables au-dessus de l'intervalle.


        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDateAb1=LocalDate.parse(a1.getDateNaissanceAb(),formatter);
        LocalDate birthDateAb2=LocalDate.parse(a2.getDateNaissanceAb(),formatter);
        int ageAb1=calculateAge(birthDateAb1,currentDate);
        int ageAb2=calculateAge(birthDateAb2,currentDate);
        int fc=Math.abs(ageAb1-ageAb2)/10;
//        System.out.println("distence: "+fc);
        if ( fc >= 0 && fc <=1)
        {
            return 0;
        }
        else {
            return 1;
        }

    }

    /**
     * calculte similary of age of abonne
     * @param birthDate
     * @param currentDate
     * @return calculate de Age pour chaque abonne.
     */
    public static int calculateAge(LocalDate birthDate,LocalDate currentDate)
    {
        int age=currentDate.getYear()-birthDate.getYear();
        //check to see if the birthday has passed
        if(currentDate.getMonthValue()<birthDate.getMonthValue())
        {
            age--;
        }
//        System.out.println(age);
        return age;
    }

    /**
     * calculte similary of sexe
     * @param a1
     * @param a2
     * @return
     */
    public int similarite_Sexe(Abonnes a1,Abonnes a2)
    {
        if(a1.getSexeAb().equals(a2.getSexeAb()))
        {
            return 0;
        }
        return 1;
    }

    /**
     * calculate similary of salary
     * @param a1
     * @param a2
     * @return
     */
    public int similarite_Salaire(Abonnes a1,Abonnes a2)
    {
        String NiveauAb1=trancherLevel(a1.getFourchetteRevenus());

        String NiveauAb2=trancherLevel(a2.getFourchetteRevenus());
//        System.out.println("level 1 :"+NiveauAb1);
//        System.out.println("level 2 :"+NiveauAb2);
        if(NiveauAb1.equals(NiveauAb2))
        {
            return 0;
        }
        else{
            return 1;
        }

    }

    /**
     * separte level of salary
     * @param salaire
     * @return
     */
    public String trancherLevel(int salaire)
    {
        //[1500, 3000, 5000, 7000, 10000]
        if(salaire<=1500)
        {
            return "L1";
        } else if (salaire>1500 && salaire<3000) {
            return "L2";
        } else if (salaire>=3000 && salaire<5000) {
            return "L3";
        } else if (salaire>=5000 && salaire<7000) {
            return "L4";
        } else if (salaire>=7000 && salaire<10000) {
            return "L5";
        } else{
            return "L6";
        }
    }

    /**
     * return result of similary of two abonnes
     * @param abonnes1
     * @param abonnes2
     * @return
     */
    public int similarite_Abonnes(Abonnes abonnes1,Abonnes abonnes2)
    {
        int res_age=similarites_Age(abonnes1,abonnes2);
        int res_sexe=similarite_Sexe(abonnes1,abonnes2);
        int res_sala=similarite_Salaire(abonnes1,abonnes2);
//        System.out.println("age: "+res_age);
//        System.out.println("salaire: "+res_sala);
//        System.out.println("sexe: "+res_sexe);
        int count=res_age+res_sala+res_sexe;
        return count;
    }


    //////////////////////////////film/////////////////////////////////

    /**
     * calculte similary of genre of film
     * @param g
     * @param nomGenreFilm1
     * @param nomGenreFilm2
     * @return
     */
    public int similarite_Genre_Film(Genre g,String nomGenreFilm1,String nomGenreFilm2)
    {
        if (nomGenreFilm1.equals(nomGenreFilm2))
        {
            return 0;

        }
        else{

            //if the same father root
            List<String> path1=new ArrayList<>();
            List<String> path2=new ArrayList<>();
            List<String> pathdeGenre1=SearchNode(g,nomGenreFilm1,path1);
            List<String> pathdeGenre2=SearchNode(g,nomGenreFilm2,path2);
//            System.out.println(nomGenreFilm1+"path1  :"+pathdeGenre1);
//            System.out.println(nomGenreFilm2+"path2  :"+pathdeGenre2);
            List<String> path_save_same_genre=new ArrayList<>(pathdeGenre1);
            // keep value belongs to path_save_same_genre delete value not belongs to PathGenre2
            path_save_same_genre.retainAll(pathdeGenre2);
//            System.out.println("path1  :"+pathdeGenre1);
//            System.out.println("path2  :"+pathdeGenre2);
//            System.out.println("same genre  :"+path_save_same_genre);
            if (path_save_same_genre.size()==1)
            {
                return 2;
            } else if (path_save_same_genre.size()==2) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    /**
     * searching node in tree
     * @param g
     * @param f
     * @param path
     * @return
     */
    public  List<String> SearchNode(Genre g,String f,List<String> path)
    {

        if(g == null)
        {
            return null;

        }
        //add node to path
        path.add(g.getGenreNom());
//        System.out.println("path : "+path);
        if (g.getGenreNom().equals(f))
        {
            return path;
        }
        //if not find
        for(Genre sub:g.getSubGenre())
        {
            List<String> res=  SearchNode(sub,f,new ArrayList<>(path));
            if(res!=null)
            {
                return res;
            }
        }

        //if not find return null
        return null;

    }

    /**
     * calculate height of tree
     * @param g
     * @return
     */
    public static int calculateHeight(Genre g)
    {
        if (g==null)
        {
            return 0;
        }
        int maxChildDepth=0;
        for (Genre sub:g.getSubGenre())
        {
            int childDepth=calculateHeight(sub);
            maxChildDepth=Math.max(maxChildDepth,childDepth);
        }
        return maxChildDepth+1;
    }


    /**
     * calculate similary of color
     * @param film1_couleur
     * @param film2_couleur
     * @return
     */
    public int similarite_Couleur_Film(boolean film1_couleur,boolean film2_couleur)
    {
        if(film1_couleur== film2_couleur)
        {
            return 0;
        }
        else {
            return 1;
        }
    }

    /**
     * calculte similary of actor common
     * @param bd
     * @param titreF2
     * @param titreF1
     * @return
     */
    public int similarite_Casting_Film(BdConnector bd,String titreF1,String titreF2) throws SQLException {
        // list all actor from BD
        Map<String,List<String>> list_of_actor_BD=bd.findActorFromFilm();
        List<String> list_of_actor1=new ArrayList<>();
        List<String> list_of_actor2=new ArrayList<>();
        for(Map.Entry<String,List<String>> entry:list_of_actor_BD.entrySet())
        {
            if (entry.getKey().equals(titreF1))
            {
                for (String actor: entry.getValue()) {
                    list_of_actor1.add(actor);
                }
            }
            else if (entry.getKey().equals(titreF2))
            {
                for (String actor: entry.getValue()) {
                    list_of_actor2.add(actor);
                }
            }

        }

//        System.out.println(list_of_actor1);
//        System.out.println(list_of_actor2);
        List<String> same_actor=new ArrayList<>(list_of_actor1);
        same_actor.retainAll(list_of_actor2);
        if (!same_actor.isEmpty())
        {
            return 0;
        }
        return 1;
    }

    /**
     * return result of similary of two films
     * @param tree
     * @param films1
     * @param films2
     * @return
     */
    public int similarite_Film(BdConnector bd,Genre tree,Film films1,Film films2) throws SQLException {

        int res_genre=similarite_Genre_Film(tree,films1.getGenre(),films2.getGenre());
        int res_couleur=similarite_Couleur_Film(films1.isCouleurF(), films2.isCouleurF());
        int res_actor=similarite_Casting_Film(bd, films1.getTitreF(),films2.getTitreF());
        int count=res_genre+res_couleur+res_actor;
//        System.out.println(res_actor);
        return count;
    }



    ////////////////////////coffret////////////////////////////////

    /**
     * calculte max similary in all movies
     * @param g
     * @return
     */
    public int similarite_Coffret(BdConnector bd,Genre g) throws SQLException {
//        int res_genre=similarite_Genre_Film(g);
//        int res_actor=similarite_Casting_Film();

        int[] max_similarite = new int[100];
        int tag=0;
        for(Film f1:coffret.getFilmlist())
        {
            for (Film f2: coffret.getFilmlist())
            {
                if(f1.equals(f2))
                {
                    continue;
                }
                
                int score=similarite_Film(bd,g,f1,f2);
                max_similarite[tag]=score;
//                System.out.println(score);
//                System.out.println(max_similarite[tag]);
                tag++;
            }
        }
        if(max_similarite==null || max_similarite.length==0)
        {
            throw new IllegalArgumentException("wrong");
        }
        int max=max_similarite[0];
        for (int i =1;i<max_similarite.length;i++)
        {
            if(max_similarite[i]>max)
            {
                max=max_similarite[i];
            }
        }
        return max;
    }

    /**
     * calculte number of movies in coffret
     * @return
     */
    public int Nombre_Film_Coffret()
    {
        int nombre_film=0;
        for (Film film: coffret.getFilmlist())
        {
            nombre_film+=1;
        }
        return nombre_film;
    }

    /**
     * return if the coffret has bonus.
     * @return
     */
    public String YaBonus()
    {
        if(coffret.isBonus())
        {
            return "Yes";
        }
        return "No";
    }
}
