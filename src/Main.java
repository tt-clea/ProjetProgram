import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        /**instead of your own path.**/
        String path = "jdbc:sqlite:DBProjetJava.db";
        /*construire object de BdConnector*/
        BdConnector bd = new BdConnector(path);

        /*construire Objet de Founction*/
        Fonction fc = new Fonction(bd);

        /*construire les arbre de genre*/
        Genre tree = new Genre("genre");
        Genre action = new Genre("action");
        Genre comedie = new Genre("comédie");
        Genre aventure = new Genre("aventure");
        Genre western = new Genre("western");
        Genre musique = new Genre("musique");
        Genre romance = new Genre("romance");
        tree.addSubGenre(action);
        tree.addSubGenre(comedie);
        action.addSubGenre(aventure);
        action.addSubGenre(western);
        comedie.addSubGenre(musique);
        comedie.addSubGenre(romance);
//        System.out.println("Genre de tree est :"+tree); // print the structure tree of Genre

        /**insert les donness dans BD de Genre**/
//        fc.addGenreBD(tree);

        /*construire Objet de Acteur*/
        Acteurs a1 = new Acteurs("aNom1", "aPrenom1");
        Acteurs a2 = new Acteurs("aNom2", "aPrenom2");
        Acteurs a3 = new Acteurs("aNom3", "aPrenom3");
        Acteurs a4 = new Acteurs("aNom4", "aPrenom4");
        Acteurs a5 = new Acteurs("aNom5", "aPrenom5");
        Acteurs a6 = new Acteurs("aNom6", "aPrenom6");

        /*insert les acteur dans BD de Acteurs*/
       /* fc.addActorBD(a1);
        fc.addActorBD(a2);
        fc.addActorBD(a3);
        fc.addActorBD(a4);
        fc.addActorBD(a5);
        fc.addActorBD(a6);
*/

        /*construire Objet de Film*/
        Film f1 = new Film("Forrest Cump", true, "western", 20);
        Film f2 = new Film("La Liste de Schindler", true, "romance", 20);
        Film f3 = new Film("La Ligne verte", true, "romance", 20);
        Film f4 = new Film("Lalaland", true, "musique", 20);
        Film f5 = new Film("Le Parrain", true, "western", 20);
        Film f6 = new Film("Les Evadés", true, "aventure", 20);

        /*insert les films dans BD de Film*/
        /*fc.addFilmBD(f1);
        fc.addFilmBD(f2);
        fc.addFilmBD(f3);
        fc.addFilmBD(f4);
        fc.addFilmBD(f5);
        fc.addFilmBD(f6);*/

        /*insert id_film and id_actor dans BD de Avoir_Acteur_Film*/
        /*fc.addFilmActor(f1,a1);
        fc.addFilmActor(f1,a2);
        fc.addFilmActor(f2,a1);
        fc.addFilmActor(f2,a3);
        fc.addFilmActor(f4,a4);
        fc.addFilmActor(f4,a2);
        fc.addFilmActor(f5,a5);
        fc.addFilmActor(f5,a3);
        fc.addFilmActor(f6,a6);*/


        /*construire Objet de Coffret*/
        Coffret c1 = new Coffret("Coffret1", true, "action");
        Coffret c2 = new Coffret("Coffret2", true, "action");
        Coffret c3 = new Coffret("Coffret3", true, "action");
        Coffret c4 = new Coffret("Coffret4", true, "action");
        Coffret c5 = new Coffret("Coffret5", true, "action");
        Coffret c6 = new Coffret("Coffret6", true, "action");


        /*insert les coffret dans BD de Coffret*/

       /* fc.addCoffretBD(c1);
        fc.addCoffretBD(c2);
        fc.addCoffretBD(c3);
        fc.addCoffretBD(c4);
        fc.addCoffretBD(c5);
        fc.addCoffretBD(c6);*/

        /*construire Objet de Abonnes*/
        Abonnes ab1 = new Abonnes("AbNom1", "AbPreNom1", "1987-12-04", "m", 2500);
        Abonnes ab2 = new Abonnes("AbNom2", "AbPreNom2", "2000-05-09", "f", 10000);
        Abonnes ab3 = new Abonnes("AbNom3", "AbPreNom3", "1997-05-28", "f", 1000);
        Abonnes ab4 = new Abonnes("AbNom4", "AbPreNom4", "2003-01-10", "m", 15000);
        Abonnes ab5 = new Abonnes("AbNom5", "AbPreNom5", "1970-11-04", "f", 6000);
        Abonnes ab6 = new Abonnes("AbNom6", "AbPreNom6", "1999-01-04", "m", 5030);

        /*insert les Abonnes dans BD de Abonnes*/
        /*fc.addAbonnesBD(ab1);
        fc.addAbonnesBD(ab2);
        fc.addAbonnesBD(ab3);
        fc.addAbonnesBD(ab4);
        fc.addAbonnesBD(ab5);
        fc.addAbonnesBD(ab6);*/


        /*constuire Objet de DateLocation*/
        DateLocation dl1 = new DateLocation("2023-11-11");
        DateLocation dl2 = new DateLocation("2023-10-10");
        DateLocation dl3 = new DateLocation("2023-07-23");
        DateLocation dl4 = new DateLocation("2023-01-01");
        DateLocation dl5 = new DateLocation("2023-09-28");
        DateLocation dl6 = new DateLocation("2023-04-13");

        /*insert date film et abonnes dans BD de Historique*/
       /* fc.enregistrerPret(ab1,f1,dl1);
        fc.enregistrerPret(ab2,f2,dl2);
        fc.enregistrerPret(ab3,f3,dl3);
        fc.enregistrerPret(ab4,f4,dl4);
        fc.enregistrerPret(ab5,f5,dl5);
        fc.enregistrerPret(ab6,f6,dl6);*/


        /*insert id_film et id_coffret dans BD de Avoir_Film_Coffret*/
      /*  fc.addFilmCoffret(c1,f1);
        fc.addFilmCoffret(c2,f2);
        fc.addFilmCoffret(c3,f3);
        fc.addFilmCoffret(c4,f5);*/
        /*fc.addFilmCoffret(c1,f2);
        fc.addFilmCoffret(c1,f5);
        fc.addFilmCoffret(c2,f1);
        fc.addFilmCoffret(c2,f6);
        fc.addFilmCoffret(c2,f5);
        fc.addFilmCoffret(c3,f1);
        fc.addFilmCoffret(c3,f6);
        fc.addFilmCoffret(c4,f1);
        fc.addFilmCoffret(c4,f6);
        fc.addFilmCoffret(c5,f1);
        fc.addFilmCoffret(c5,f4);
        fc.addFilmCoffret(c5,f6);
        fc.addFilmCoffret(c6,f1);
        fc.addFilmCoffret(c6,f2);
        fc.addFilmCoffret(c6,f4);
*/



        System.out.println("-------------------founction-------------------");
        //////////////////////////////founction//////////////////////////////////////////
        /*Ajouter un abonné dans une liste d'abonnés est également ajouter un abonne dans BD de Abonnes*/

        /*Retrouver dans la liste des abonnés un abonné à partir de son nom*/

        System.out.println(bd.findAllAbonnes());
        System.out.println("------------------------------------------------");

        /*Ajouter un film à une liste de films est également ajouter un film dans BD de Film*/
        /*Enregistrer un prêt : un abonné loue un film est également ajouter les donnees dans BD de Historique*/

        /*•	Extraire le genre de films le plus populaire (le plus loué)*/
        System.out.println("Le genre de films le plus populaire est :" + fc.PlusLoueGenre().getKey() + "\n" + "Nombre de location est " + fc.PlusLoueGenre().getValue());
        System.out.println("------------------------------------------------");

        /*•	Extraire de la liste des films les films les plus similaires à un film ayant le titre « ? »*/
        System.out.println("la liste des films les films les plus similaires à un film ayant le titre « ? »" + "\n" + fc.KeyWordFilm("n"));
        System.out.println("------------------------------------------------");

        /*Extraire de la liste des abonnés, les abonnés « les plus curieux » : les films loués par ces abonnés se ressemblent le moins possible.*/
        System.out.println("la liste des abonnés, les abonnés « les plus curieux » : les films loués par ces abonnés se ressemblent le moins possible :" + "\n" + fc.findSimilaireFilm(tree));
        System.out.println("------------------------------------------------");

        /*Extraire de la liste des films les films ayant un public « typé » : les abonnés louant ces films sont similaires (on fournira en paramètre le seuil de similarité entre abonnés).*/
        Map<Integer, List<Integer>> list_abonne = fc.AbonnesSimilarite();
        Map<Integer, List<List<String>>> list_film = fc.get_film_titre_selon_abonne(list_abonne);
        System.out.println("la liste des films les films ayant un public <type> :les abonnes louant ces films sont similarires :" + "\n" + list_film);
        System.out.println("------------------------------------------------");

        /*Extraire de la liste des abonnés, les abonnés les plus proches d'un profil type.*/
        Map<Integer, List<Integer>> list_abonne_id = fc.AbonnesSimilarite();  //get id of abonnes
        Map<Integer, List<List<String>>> abonnes = fc.get_Abonnes_selon_id(list_abonne_id);
        System.out.println("la liste des abonnés, les abonnés les plus proches d'un profil type :" + "\n" + abonnes);
        System.out.println("------------------------------------------------");

        /*•	Extraire de la liste des produits vidéos, pour chaque produit, le ou les films les plus similaires.*/
        System.out.println("la liste des produits vidéos, pour chaque produit, le ou les films les plus similaires :" + "\n" + fc.extraireCoffret(tree));
        System.out.println("------------------------------------------------");
    }
}