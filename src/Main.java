import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        //instead of your own path.
        String path="jdbc:sqlite:DBProjetJava.db";
        //construire object de BdConnector
        BdConnector bd=new BdConnector(path);
        //connect base de donnee de SQLite
//        Connection connection=BdConnector.connect();

        // construire les arbre de genre
        Genre tree=new Genre("genre");
        Genre action=new Genre("action");
        Genre comedie=new Genre("comédie");
        Genre aventure=new Genre("aventure");
        Genre western=new Genre("western");
        Genre musique=new Genre("musique");
        Genre romance=new Genre("romance");
        tree.addSubGenre(action);
        tree.addSubGenre(comedie);
        action.addSubGenre(aventure);
        action.addSubGenre(western);
        comedie.addSubGenre(musique);
        comedie.addSubGenre(romance);
        System.out.println("Genre de tree est :"+tree);
        //construire acteurs
        Acteurs a1=new Acteurs("anne","anne");
        Acteurs a2=new Acteurs("tiantian","tiantian");

        // construire film
        Film f1=new Film("Angle",true,"musique",10);
        Film f2=new Film("Angle2",true,"romance",15);
        Film f3=new Film("Runing man",false,"musique",15);
        Film f4=new Film("Runing man2",false,"aventure",15);

        //construire coffret
        Coffret c1=new Coffret("Coffret1",true);
        Coffret c2=new Coffret("Coffret2",false);

        //construire abonnes
        Abonnes ab1=new Abonnes("an","anne","1978-12-14","f",2500);
        Abonnes ab2=new Abonnes("tt","tiantian","2000-05-09","f",10000);
        Abonnes ab3=new Abonnes("11111","22222","2000-01-04","f",3000);

        //DateLocation
        DateLocation dl1=new DateLocation("2023-11-11");

        //construire evolues
        Evolues ev_abonne=new Evolues(ab1,ab2);
        Evolues ev_film=new Evolues(f3,f4);
        Evolues ev_coffret=new Evolues(c1);

        //ajouter les acteurs dans film

        f1.addActeurs(a1);
        f1.addActeurs(a2);
        f2.addActeurs(a1);
        f2.addActeurs(a2);
        f3.addActeurs(a2);
        f3.addActeurs(a1);
        f4.addActeurs(a1);


        //ajouter les films dans coffret;
        c1.addFilmList(f1);
        c1.addFilmList(f2);
        c1.addFilmList(f3);
        c2.addFilmList(f3);
        c2.addFilmList(f4);
        //print coffret c1 et c2
//        System.out.println(c1);
//        System.out.println(c2);


        //tester les evolues
//        System.out.println("Le similarité de Abonnés est "+ev_abonne.similarite_Abonnes());
//        System.out.println("Le similarité de Film est "+ev_film.similarite_Film(tree,f3,f4));//将构造的二叉树传入到evolues_film中
//        System.out.println("Le similarité de Coffret est "+ev_coffret.similarite_Coffret(tree));
//        System.out.println("Le nombre de film dans le coffret est "+ev_coffret.Nombre_Film_Coffret());
//        System.out.println("Il y a un bonus ? " + ev_coffret.YaBonus());


        //class de founction
        //ajouter abonnes dans une liste d'abonnes

//        Fonction add_abonne=new Fonction(bd);
//        add_abonne.addAbonnesBD(ab3);
//        BdConnector check=new BdConnector(path);
//        check.insert_abonne("lucas","wifi","2000-01-04","f",3000);
//        String fina=check.findAbonne("an","an");
        Fonction fonction_test=new Fonction(bd);
//        fonction_test.addAbonnesBD(ab1);
//        fonction_test.findAbonnes("an","an");
//        fonction_test.addFilmBD(f2);
//        fonction_test.enregistrerPret(ab1,f1,dl1);
//        fonction_test.enregistrerPret(ab2,f2,dl1);
//        fonction_test.enregistrerPret(ab3,f4,dl1);
//        fonction_test.enregistrerPret(ab3,f2,dl1);

//        fonction_test.addFilmBD(f2);
//        fonction_test.addFilmBD(f3);
//        fonction_test.addFilmBD(f4);
//        fonction_test.addAbonnesBD(ab1);
//        fonction_test.addAbonnesBD(ab2);
//
//        fonction_test.enregistrerPret(ab1,f4,dl1);
        fonction_test.extraireAbonneMemeRevenu();

        Map<String,List<String>> extraireMemeRevenu=fonction_test.extraireAbonneMemeRevenu();
        System.out.println(extraireMemeRevenu);
        fonction_test.PlusLoueGenre();

        //Extraire de la liste des films les films les plus similaires à un film ayant le titre « ? ».
        fonction_test.KeyWordFilm("An");


        //Extraire de la liste des abonnés, les abonnés « les plus curieux »
        //les films loués par ces abonnés se ressemblent le moins possible.
        fonction_test.findSimilaireFilm(tree);

        //extraire de la liste des films les films ayant un public type
        Map<Integer,List<Integer>> list_abonne=fonction_test.AbonnesSimilarite();
        Map<Integer,List<List<String>>> list_film=fonction_test.get_film_titre_selon_abonne(list_abonne);

        //extraire de la liste des abonnes, les abonnes les plus proches d'un profil type
        Map<Integer,List<Integer>> list_abonne_id=fonction_test.AbonnesSimilarite();  //get id of abonnes
        Map<Integer,List<List<String>>> abonnes=fonction_test.get_Abonnes_selon_id(list_abonne_id);

        //extraire de la liste des produits videos, pour chaque produit , le ou les films les plus similarites;


//        fonction_test.addGenreBD(tree);
        //c1.addFilmList(f1);
        //        c1.addFilmList(f2);
        //        c1.addFilmList(f3);
        //        c2.addFilmList(f3);
        //        c2.addFilmList(f4);
        // ajouter coffret
//        fonction_test.addCoffretBD(c1);
//        fonction_test.addCoffretBD(c2);


        //ajouter les films dans coffret

//
//        fonction_test.addFilmCoffret(c1,f1);
//        fonction_test.addFilmCoffret(c1,f2);
//        fonction_test.addFilmCoffret(c1,f3);
//        fonction_test.addFilmCoffret(c2,f3);
//        fonction_test.addFilmCoffret(c2,f4);


        //ajouter les acteurs dans BD
//        fonction_test.addActorBD(a1);
//        fonction_test.addActorBD(a2);

        //ajouter les acteurs dans film
//        f1.addActeurs(a1);
//        f1.addActeurs(a2);
//        f2.addActeurs(a1);
//        f2.addActeurs(a2);
//        f3.addActeurs(a2);
//        f3.addActeurs(a1);
//        f4.addActeurs(a1);
        fonction_test.addFilmActor(f1,a1);
        fonction_test.addFilmActor(f1,a2);
        fonction_test.addFilmActor(f2,a2);



    }
}