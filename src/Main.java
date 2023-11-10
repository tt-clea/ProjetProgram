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




        Film f1=new Film("Angle",true,musique);
        Film f2=new Film("Angle2",true,romance);
        Acteurs a1=new Acteurs("anne","anne");
        Acteurs a2=new Acteurs("tiantian","tiantian");
        System.out.println(a1.getNomA()+a1.getPrenomA());
        System.out.println(a2.getNomA()+a2.getPrenomA());
        f1.addActeurs(a1);
        f1.addActeurs(a2);
        f2.addActeurs(a1);
        f2.addActeurs(a2);
        System.out.println(f1.getNbStockage());
        System.out.println(f1.getTitreF()+f1.getActeursList());

        Coffret c1=new Coffret("Coffret1",musique,true,true);
        c1.addFilmList(f1);
        c1.addFilmList(f2);
        System.out.println(c1.getFilmlist());
        System.out.println(c1.getActeursForFilm());

        Abonnes ab1=new Abonnes("an","1978-12-14","f",2500);
        Abonnes ab2=new Abonnes("tt","2000-05-09","f",10000);

        f1.getGenre();


//        System.out.println("action:"+action);
//        System.out.println("comedie:"+comedie);
//        System.out.println("western:"+western);

        System.out.println(tree.getSubGenre());


        Film f3=new Film("Runing man",true,musique);
        Film f4=new Film("Runing man2",true,musique);
        System.out.println("film3 "+f3);

        Evolues ev1=new Evolues(ab1,ab2);


        Evolues ev2=new Evolues(f3,f4);
//        System.out.println(ev1.similarite_Abonnes());

        //将构造的二叉树传入到evolues中
        System.out.println(ev2.similarite_Film(tree));
    }
}