import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Evolues {
    private Abonnes abonnes1;
    private Abonnes abonnes2;
    private static Film films1;
    private static Film films2;

    private List<Genre> genre;

    public Evolues() {

    }

    public Evolues(Abonnes abonnes1,Abonnes abonnes2) {
        this.abonnes1 = abonnes1;
        this.abonnes2=abonnes2;
    }

    public Evolues(Film films1,Film films2) {
        this.films1 = films1;
        this.films2 = films2;
        this.genre=new ArrayList<>();
    }


    /**
     * Déterminer si deux age des abonnes ont similarite ,similarite à 0, sinon 1
     * @return
     */
    public int similarites_Age()
    {
        //[0,1] Les résultats calculés sont similaires à l'intérieur de l'intervalle et dissemblables au-dessus de l'intervalle.


        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDateAb1=LocalDate.parse(abonnes1.getDateNaissanceAb(),formatter);
        LocalDate birthDateAb2=LocalDate.parse(abonnes2.getDateNaissanceAb(),formatter);
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
     *
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

    public int similarite_Sexe()
    {
        if(abonnes1.getSexeAb().equals(abonnes2.getSexeAb()))
        {
            return 0;
        }
        return 1;
    }
    public int similarite_Salaire()
    {
        String NiveauAb1=trancherLevel(abonnes1.getFourchetteRevenus());

        String NiveauAb2=trancherLevel(abonnes2.getFourchetteRevenus());
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

    public static String trancherLevel(int salaire)
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

    public String similarite_Abonnes()
    {
        int res_age=similarites_Age();
        int res_sexe=similarite_Sexe();
        int res_sala=similarite_Salaire();
        System.out.println("age: "+res_age);
        System.out.println("salaire: "+res_sala);
        System.out.println("sexe: "+res_sexe);
        int count=res_age+res_sala+res_sexe;
        return "Le résultat du calcul de la similarité est "+count;
    }

    public int similarite_Genre(Genre g)
    {
        //获得两个节点
        Genre genreF1=films1.getGenre();
        Genre genreF2=films2.getGenre();
        String nomGenreFilm1= genreF1.getGenreNom(); //get the nom of genre.
        String nomGenreFilm2= genreF2.getGenreNom();
//        System.out.println("genreF1 : "+genreF1);
//        System.out.println("genreF2 : "+genreF2);
        int size=0;


        if (genreF1.equals(genreF2))
        {
            size=calculateHeight(g)-1;
//            System.out.println("Size "+size);
            return size;
        }
        else{

            //判断子节点是否为同一个父节点
//
            List<String> path1=new ArrayList<>();
            List<String> path2=new ArrayList<>();
            List<String> pathdeGenre1=SearchNode(g,nomGenreFilm1,path1);
            List<String> pathdeGenre2=SearchNode(g,nomGenreFilm2,path2);
//            System.out.println("path1  :"+pathdeGenre1);
//            System.out.println("path2  :"+pathdeGenre2);
            List<String> path_save_same_genre=pathdeGenre1;
            path_save_same_genre.retainAll(pathdeGenre2);
//            System.out.println("same genre  :"+path_save_same_genre);
            size=path_save_same_genre.size()-1;

//            System.out.println("Size :"+size);
            return size;
        }
    }

    public  List<String> SearchNode(Genre g,String f,List<String> path)
    {

        if(g == null)
        {
            return null;

        }
        //将首节点添加到路径中
        path.add(g.getGenreNom());
//        System.out.println("path : "+path);
        if (g.getGenreNom().equals(f))
        {
            return path;
        }
        //如果没找到，在子节点急需寻找
        for(Genre sub:g.getSubGenre())
        {
            List<String> res=  SearchNode(sub,f,new ArrayList<>(path));
            if(res!=null)
            {
                return res;
            }
        }

        //如果首节点及其子节点都没有找到目标节点，返回空结果
        return null;

    }


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







    public String similarite_Film(Genre g)
    {
        String nomGenre=g.getGenreNom();
//        System.out.println(nomGenre);
        List<Genre> subGenre=g.getSubGenre();
//        System.out.println(subGenre);
        int res_genre=similarite_Genre(g);
        return "Le similarité de Genre est "+res_genre;
    }

}


