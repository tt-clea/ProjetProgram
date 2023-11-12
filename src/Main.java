public class Main {
    public static void main(String[] args) {
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
        Film f1=new Film("Angle",true,musique);
        Film f2=new Film("Angle2",true,romance);
        Film f3=new Film("Runing man",false,musique);
        Film f4=new Film("Runing man2",false,aventure);

        //construire coffret
        Coffret c1=new Coffret("Coffret1",true);
        Coffret c2=new Coffret("Coffret2",false);

        //construire abonnes
        Abonnes ab1=new Abonnes("an","1978-12-14","f",2500);
        Abonnes ab2=new Abonnes("tt","2000-05-09","f",10000);


        //construire evolues
        Evolues ev_abonne=new Evolues(ab1,ab2);
        Evolues ev_film=new Evolues(f3,f4);
        Evolues ev_coffret=new Evolues(c1);

        //ajouter les acteurs dans film

        //fonction1
        //fonction2
        //fonction3
        Genre g1=new Genre("romance");
        Film f1=new Film("Angle",true,g1);
        Film f2=new Film("Angle2",true,g1);
        Acteurs a1=new Acteurs("anne","anne");
        Acteurs a2=new Acteurs("tiantian","tiantian");
        System.out.println(a1.getNomA()+a1.getPrenomA());
        System.out.println(a2.getNomA()+a2.getPrenomA());

        f1.addActeurs(a1);
        f1.addActeurs(a2);
        f2.addActeurs(a1);
        f2.addActeurs(a2);
        f3.addActeurs(a2);
        f3.addActeurs(a1);
        f4.addActeurs(a1);


        //ajouter les films dans coffret;

        Coffret c1=new Coffret("Coffret1",g1,true,true);

        c1.addFilmList(f1);
        c1.addFilmList(f2);
        c1.addFilmList(f3);
        c2.addFilmList(f3);
        c2.addFilmList(f4);
        //print coffret c1 et c2
        System.out.println(c1);
        System.out.println(c2);


        //tester les evolues
        System.out.println("Le similarité de Abonnés est "+ev_abonne.similarite_Abonnes());
        System.out.println("Le similarité de Film est "+ev_film.similarite_Film(tree,f3,f4));//将构造的二叉树传入到evolues_film中
        System.out.println("Le similarité de Coffret est "+ev_coffret.similarite_Coffret(tree));
        System.out.println("Le nombre de film dans le coffret est "+ev_coffret.Nombre_Film_Coffret());
        System.out.println("Il y a un bonus ? " + ev_coffret.YaBonus());







//        System.out.println(a1.getNomA()+a1.getPrenomA());
//        System.out.println(a2.getNomA()+a2.getPrenomA());

//        System.out.println(f1.getNbStockage());
//        System.out.println(f1.getTitreF()+f1.getActeursList());



//        System.out.println(c1.getFilmlist());
//        System.out.println(c1.getActeursForFilm());



//        System.out.println("action:"+action);
//        System.out.println("comedie:"+comedie);
//        System.out.println("western:"+western);

//        System.out.println(tree.getSubGenre());





//        System.out.println("film3 "+f3);


//
    }
}