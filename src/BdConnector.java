import java.sql.*;
import java.sql.PreparedStatement;
import java.util.*;

public class BdConnector {
    private static String path;

    public BdConnector(String path) {
        this.path = path;
    }

    public static String getPath() {
        return path;
    }

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to connect SQLite JDBC driver");
        }
    }

    public static Connection connect() {
        try {
//            System.out.println("connection successful!");
            return DriverManager.getConnection(getPath());
        } catch (SQLException e) {
            System.out.println("connection failure!");
            e.printStackTrace();
            return null;
        }
    }

    public static void closeBD(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) preparedStatement.close();
            if (connect() != null) connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insert_coffret(String titreC,String genreC,boolean bonus) throws SQLException {
        String query="insert into Coffret(titreC, genreC, bonus) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, titreC);
        preparedStatement.setString(2, genreC);
        preparedStatement.setBoolean(3, bonus);
        int rowsInserted = preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.executeQuery();
        if (rowsInserted > 0) {

            closeBD(preparedStatement);
            return true;
        } else {

            closeBD(preparedStatement);
            return false;
        }
    }
    public boolean insert_genre(String genreFather,String genreSon) throws SQLException {
        String query="insert into Genre(genreNom, SubGenre) VALUES (?,?);";

        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, genreFather);
        preparedStatement.setString(2, genreSon);
        int rowsInserted = preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.executeQuery();
        if (rowsInserted > 0) {

            closeBD(preparedStatement);
            return true;
        } else {

            closeBD(preparedStatement);
            return false;
        }
    }

    public boolean insert_actor(String nomA,String prenomA) throws SQLException {
        String query="insert into Acteurs(prenomA, nomA) VALUES (?,?);";

        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, prenomA);
        preparedStatement.setString(2, nomA);
        int rowsInserted = preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.executeQuery();
        if (rowsInserted > 0) {

            closeBD(preparedStatement);
            return true;
        } else {

            closeBD(preparedStatement);
            return false;
        }
    }

    public boolean insert_Avoir_Film_Coffret(int id_coffret,int id_film) throws SQLException {
        String query="insert into avoir_film_coffret(id_coffret, id_film) VALUES (?,?);";

        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setInt(1, id_coffret);
        preparedStatement.setInt(2, id_film);
        int rowsInserted = preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.executeQuery();
        if (rowsInserted > 0) {

            closeBD(preparedStatement);
            return true;
        } else {

            closeBD(preparedStatement);
            return false;
        }
    }

    public boolean insert_avoir_film_actor(int id_film,int id_actor) throws SQLException {
        String query="insert into avoir_acteur_film(id_film, id_acteur) VALUES (?,?);";

        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setInt(1, id_film);
        preparedStatement.setInt(2, id_actor);
        int rowsInserted = preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.executeQuery();
        if (rowsInserted > 0) {

            closeBD(preparedStatement);
            return true;
        } else {

            closeBD(preparedStatement);
            return false;
        }

    }





    public boolean insert_abonne(String prenom, String nom, String DateNaissance, String sexe, int Revenus) throws SQLException {
        String query = "INSERT INTO Abonnes(prenomAb,nomAb,dateNaissanceAb,sexeAb,fourchetteRevenus) VALUES(?,?,?,?,?)";
//        System.out.println(prenom);
//        System.out.println(nom);
//        System.out.println(DateNaissance);
//        System.out.println(sexe);
//        System.out.println(Revenus);

        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, prenom);
        preparedStatement.setString(2, nom);
        preparedStatement.setString(3, DateNaissance);
        preparedStatement.setString(4, sexe);
        preparedStatement.setInt(5, Revenus);

        int rowsInserted = preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.executeQuery();
        if (rowsInserted > 0) {

            closeBD(preparedStatement);
            return true;
        } else {

            closeBD(preparedStatement);
            return false;
        }

    }
    public boolean insert_film(String titreF, Boolean couleurF, int NbStockage, String genreF) throws SQLException {
        String query = "insert into Film(titreF, couleurF, NbStockage, genreF) VALUES (?,?,?,?)";

        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, titreF);
        preparedStatement.setBoolean(2, couleurF);
        preparedStatement.setInt(3, NbStockage);
        preparedStatement.setString(4, genreF);

        int rowsInserted = preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.executeQuery();
        if (rowsInserted > 0) {

            closeBD(preparedStatement);
            return true;
        } else {

            closeBD(preparedStatement);
            return false;
        }

    }



    /////////////////////////find//////////////////////////////////////////////////

    public boolean findAbonneParNom(String Nom, String Prenom) throws SQLException {
        String query = "SELECT * FROM Abonnes where nomAb=? and prenomAb=?;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, Nom);
        preparedStatement.setString(2, Prenom);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String firstname = resultSet.getString("prenomAb");
            String lastname = resultSet.getString("nomAb");

            System.out.println("L'abonné " + firstname + " " + lastname + " existe");
            resultSet.close();
            closeBD(preparedStatement);
            return true;
        } else {
            resultSet.close();
            closeBD(preparedStatement);
            return false;

        }
    }

    public List<String> findAbonneParId(int id) throws SQLException {
        String query = "select nomAb,prenomAb from Abonnes where id=?;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setInt(1, id);

        List<String> profil=new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            String firstname = resultSet.getString("prenomAb");
            String lastname = resultSet.getString("nomAb");
            profil.add(firstname);
            profil.add(lastname);
        }
        resultSet.close();
        closeBD(preparedStatement);
        return profil;


    }






    public boolean findFilm(String titreF) throws SQLException {
        String query = "select * from Film where titreF= ?";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, titreF);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id_Film = resultSet.getInt("id");
            String titre_Film = resultSet.getString("titreF");
            String couleur_Film = resultSet.getString("couleurF");
            int NbStrockage_Film = resultSet.getInt("NbStockage");
            String genre_Film = resultSet.getString("genreF");
            System.out.println("id:" + id_Film + " titre:" + titre_Film + " couleur:" + couleur_Film + " NbStrockage:" + NbStrockage_Film + " genre:" + genre_Film );
            resultSet.close();
            closeBD(preparedStatement);
            return true;
        } else {
            resultSet.close();
            closeBD(preparedStatement);
            System.out.println("No film");
            return false;
        }
    }

    //find all information in BD film
    public List<Object> findFilm2(String titreF) throws SQLException {
        String query = "select * from Film where titreF= ?";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, titreF);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Object> film=new ArrayList<>();
        while(resultSet.next()) {
            int id_Film = resultSet.getInt("id");
            String titre_Film = resultSet.getString("titreF");
            String couleur_Film = resultSet.getString("couleurF");
            int NbStrockage_Film = resultSet.getInt("NbStockage");
            String genre_Film = resultSet.getString("genreF");
            film.add(titre_Film);
            film.add(couleur_Film);
            film.add(NbStrockage_Film);
            film.add(genre_Film);
        }
        resultSet.close();
        closeBD(preparedStatement);
        return film;
    }

    public int findNbFilm(String titreF) throws SQLException {
        String query="select NbStockage from Film  where titreF=?;";
        PreparedStatement preparedStatement= connect().prepareStatement(query);
        preparedStatement.setString(1,titreF);
        ResultSet resultSet= preparedStatement.executeQuery();
        if(resultSet.next())
        {
            int nb=resultSet.getInt("NbStockage");
            System.out.println("Nombre de stockage est : "+nb);
            resultSet.close();
            closeBD(preparedStatement);
            return nb;
        }
        else
        {
            resultSet.close();
            closeBD(preparedStatement);
            return -1;
        }
    }

    public Map<List<Object>,Integer> findAllAbonnes() throws SQLException {
        Map<List<Object>,Integer> allAbonne= new HashMap<>();
        String query="select * from Abonnes;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            //[nom,prenom] [fourchette]
            List<Object> chaqueAbonnes=new ArrayList<>();
            int id_abonne = resultSet.getInt("id");
            String prenom= resultSet.getString("prenomAb");
            String nom=resultSet.getString("nomAb");
            String birth=resultSet.getString("dateNaissanceAb");
            String sexeAb=resultSet.getString("sexeAb");
            int money=resultSet.getInt("fourchetteRevenus");
            chaqueAbonnes.add(id_abonne);
            chaqueAbonnes.add(prenom);
            chaqueAbonnes.add(nom);
            chaqueAbonnes.add(sexeAb);
            chaqueAbonnes.add(birth);
            allAbonne.put(chaqueAbonnes,money);
        }
        resultSet.close();
        closeBD(preparedStatement);
        return allAbonne;
    }

    public Map<String,Integer> findFilmLoue() throws SQLException {
        Map<String,Integer> listoffilm= new HashMap<>();
        String query="select genreF,count(*) from Historique,Film where Historique.id_film=film.id group by genreF;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            String titreF= resultSet.getString("genreF");
            int count=resultSet.getInt("count(*)");
            listoffilm.put(titreF,count);
        }
        resultSet.close();
        closeBD(preparedStatement);
        return listoffilm;
    }

    public List<List<Object>> findFilmByKeyWord(String kw) throws SQLException {
        List<List<Object>> map_film=new ArrayList<>();
        String keyword="%"+kw+"%";
        String query="SELECT * FROM Film WHERE titreF LIKE ?;";

        PreparedStatement preparedStatement = connect().prepareStatement(query);
        preparedStatement.setString(1,keyword);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            List<Object> chaquefilm=new ArrayList<>();
            String titreF= resultSet.getString("titreF");
            String couleurF=resultSet.getString("couleurF");
            int NbStockage=resultSet.getInt("NbStockage");
            String genreF=resultSet.getString("genreF");
            chaquefilm.add(titreF);
            chaquefilm.add(couleurF);
            chaquefilm.add(NbStockage);
            chaquefilm.add(genreF);
            map_film.add(chaquefilm);
        }
        resultSet.close();
        closeBD(preparedStatement);
        return map_film;
    }
    public List<List<Object>> ReturnFilm() throws SQLException {
        //[[id,titreF,couleurF,NbStockage,genreF][,,,,]]
        List<List<Object>> list_film=new ArrayList<>();
        String query = "select * from Film;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            List<Object> chaque_film=new ArrayList<>();
            int id_Film = resultSet.getInt("id");
            String titre_Film = resultSet.getString("titreF");
            String couleur_Film = resultSet.getString("couleurF");
            int NbStrockage_Film = resultSet.getInt("NbStockage");
            String genre_Film = resultSet.getString("genreF");
            chaque_film.add(id_Film);
            chaque_film.add(titre_Film);
            chaque_film.add(couleur_Film);
            chaque_film.add(NbStrockage_Film);
            chaque_film.add(genre_Film);
            list_film.add(chaque_film);
        }
        resultSet.close();
        closeBD(preparedStatement);
        return list_film;
    }

    public List<List<String>> findHistorique(int id) throws SQLException {
        String query = "select nomAb,prenomAb from Abonnes,Historique where id=Historique.id_abonne and id_film=?;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setInt(1, id);
        List<List<String>> abonne_list=new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            List<String> abonne=new ArrayList<>();
            String firstname = resultSet.getString("prenomAb");
            String lastname = resultSet.getString("nomAb");
            abonne.add(firstname);
            abonne.add(lastname);
            abonne_list.add(abonne);
        }
        resultSet.close();
        closeBD(preparedStatement);
        return abonne_list;
    }


    public List<String> findTitreFilmSelonAbonnes(int id_abonne) throws SQLException {
        List<String> listoffilm=new ArrayList<>();
        String query="select titreF from Film,Historique where id=id_film and id_abonne=?;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        preparedStatement.setInt(1,id_abonne);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            String titreF= resultSet.getString("titreF");
            listoffilm.add(titreF);
        }
        resultSet.close();
        closeBD(preparedStatement);
        return listoffilm;
    }

    //list films par chaque coffret
    //Map<Coffret:Nom , List[Films]>
    public Map<String,List<String>> FilmsParCoffret() throws SQLException {
        String query="select titreC,titreF FROM Film,avoir_film_coffret,Coffret where Film.id=id_film and Coffret.id=avoir_film_coffret.id_coffret;";
        PreparedStatement preparedStatement= connect().prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        Map<String,List<String>> coffret_films=new HashMap<>();
        while(resultSet.next())
        {
            String titreC=resultSet.getString("titreC");
            String titreF=resultSet.getString("titreF");
//            System.out.println(titreC);
//            System.out.println(titreF);
//            System.out.println("---------------");
            //if map already has key value
            if (coffret_films.containsKey(titreC))
            {
               coffret_films.get(titreC).add(titreF);
            }else{
                coffret_films.put(titreC,new ArrayList<>());
                coffret_films.get(titreC).add(titreF);}
        }
        return coffret_films;
    }




    /////////////////////// return id /////////////////////////////////////////
    public int get_id_abonne(String Nom, String Prenom) throws SQLException {
        if(findAbonneParNom(Nom,Prenom))
        {
            String query="select id from Abonnes where nomAb= ? and prenomAb= ?;";
            PreparedStatement preparedStatement = connect().prepareStatement(query);
            // set attribut
            preparedStatement.setString(1, Nom);
            preparedStatement.setString(2, Prenom);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id= resultSet.getInt("id");
                resultSet.close();
                closeBD(preparedStatement);
                return id;
            } else {
                resultSet.close();
                closeBD(preparedStatement);
                return -1;
            }
        }
        else {
            return -1;
        }
    }
    public int get_id_film(String titreF) throws SQLException {
        if(findFilm(titreF))
        {
            String query="select id from Film where titreF= ? ;";
            PreparedStatement preparedStatement = connect().prepareStatement(query);
            // set attribut
            preparedStatement.setString(1, titreF);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id_Film = resultSet.getInt("id");
                resultSet.close();
                closeBD(preparedStatement);
                return id_Film;
            } else {
                resultSet.close();
                closeBD(preparedStatement);
                System.out.println("No film");
                return -1;
            }
        }
        else {
            return -1;
        }
    }
    public int get_id_coffret(String titreC) throws SQLException {
        String query="select id from Coffret where Coffret.titreC=?;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, titreC);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id_Coffret = resultSet.getInt("id");
            resultSet.close();
            closeBD(preparedStatement);
            return id_Coffret;
        } else {
            resultSet.close();
            closeBD(preparedStatement);
            System.out.println("No Coffret");
            return -1;
        }

    }

    public int get_id_actor(String nomA,String prenomA) throws SQLException {
        String query="select id from Acteurs where nomA=? and prenomA = ?;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, nomA);
        preparedStatement.setString(2,prenomA);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id_Actor = resultSet.getInt("id");
            resultSet.close();
            closeBD(preparedStatement);
            return id_Actor;
        } else {
            resultSet.close();
            closeBD(preparedStatement);
            System.out.println("No Coffret");
            return -1;
        }

    }


    ///////////////////////// founction  ////////////////////////////
    public boolean historique(int id_film,int id_abonne,String DateDebut) throws SQLException {
        String query="insert into Historique(id_film, id_abonne, DateDebut, DateFin) VALUES (?,?,?,?);";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setInt(1,id_film);
        preparedStatement.setInt(2,id_abonne);
        preparedStatement.setString(3,DateDebut);
        preparedStatement.setString(4,null);


        int rowsAffected=preparedStatement.executeUpdate();
        if (rowsAffected>0) {
            closeBD(preparedStatement);
            System.out.println("Logging successful");
            return true;
        } else {
            closeBD(preparedStatement);
            System.out.println("Logging failed");
            return false;
        }
    }

    public boolean mis_a_jour_Nb(String titreF) throws SQLException {
        String query="UPDATE Film SET NbStockage = NbStockage - 1 WHERE titreF = ?";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1,titreF);

        int rowsAffected=preparedStatement.executeUpdate();
        if (rowsAffected>0) {
            closeBD(preparedStatement);
            return true;
        } else {
            closeBD(preparedStatement);
            return false;
        }

    }


}
