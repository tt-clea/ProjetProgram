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

    public boolean findAbonne(String Nom, String Prenom) throws SQLException {
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

    public boolean findFilm(String titreF) throws SQLException {
        String query = "select * from Film where titreF= ?";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, titreF);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String id_Film = resultSet.getString("id");
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


    public int get_id_abonne(String Nom, String Prenom) throws SQLException {
        if(findAbonne(Nom,Prenom))
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
    public Map<List<String>,Integer> findAllAbonnes() throws SQLException {
        Map<List<String>,Integer> allAbonne= new HashMap<>();
        String query="select * from Abonnes;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            //[nom,prenom] [fourchette]
            List<String> chaqueActor=new ArrayList<>();
            int id_abonne = resultSet.getInt("id");
            String prenom= resultSet.getString("prenomAb");
            String nom=resultSet.getString("nomAb");
            String birth=resultSet.getString("dateNaissanceAb");
            String sexeAb=resultSet.getString("sexeAb");
            int money=resultSet.getInt("fourchetteRevenus");
            chaqueActor.add(prenom);
            chaqueActor.add(nom);
            chaqueActor.add(sexeAb);
            chaqueActor.add(birth);
            allAbonne.put(chaqueActor,money);
        }
        resultSet.close();
        closeBD(preparedStatement);
        return allAbonne;
    }

}
