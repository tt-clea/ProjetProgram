public class Main {
    public static void main(String[] args) {
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
        System.out.println(f1.getNbStockage());
        System.out.println(f1.getTitreF()+f1.getActeursList());

        Coffret c1=new Coffret("Coffret1",g1,true,true);
        c1.addFilmList(f1);
        c1.addFilmList(f2);
        System.out.println(c1.getFilmlist());
        System.out.println(c1.getActeursForFilm());


    }
}