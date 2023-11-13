import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Objects;

public class BdConnector {
    private static String path;

    public BdConnector(String path) {
        this.path = path;
    }

    public static String getPath() {
        return path;
    }

    static {
        try
        {
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Failed to connect SQLite JDBC driver");
        }
    }
    public static Connection connect()
    {
        try {
            System.out.println("connection successful!");
            return DriverManager.getConnection(getPath());
        }catch (SQLException e)
        {
            System.out.println("connection failure!");
            e.printStackTrace();
            return null;
        }
    }
    public static void closeBD(PreparedStatement preparedStatement)
    {
        try{
            if(preparedStatement !=null) preparedStatement.close();
            if(connect() !=null) connect().close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean insert_abonne(String prenom,String nom,String DateNaissance,String sexe,int Revenus) throws SQLException {
        String query = "INSERT INTO Abonnes(prenomAb,nomAb,dateNaissanceAb,sexeAb,fourchetteRevenus) VALUES(?,?,?,?,?)";
        System.out.println(prenom);
        System.out.println(nom);
        System.out.println(DateNaissance);
        System.out.println(sexe);
        System.out.println(Revenus);

        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1,prenom);
        preparedStatement.setString(2,nom);
        preparedStatement.setString(3,DateNaissance);
        preparedStatement.setString(4,sexe);
        preparedStatement.setInt(5,Revenus);

        int rowsInserted=preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.executeQuery();
        if (rowsInserted >0)
        {

            closeBD(preparedStatement);
            return true;
        }else {

            closeBD(preparedStatement);
            return false;
        }

    }

    public boolean findAbonne(String Nom,String Prenom) throws SQLException {
        String query = "SELECT * FROM Abonnes where nomAb=? and prenomAb=?;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1,Nom);
        preparedStatement.setString(2,Prenom);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String firstname = resultSet.getString("prenomAb");
            String lastname = resultSet.getString("nomAb");
            System.out.println("L'abonné " + firstname + " " + lastname + " existe");
            resultSet.close();
            closeBD(preparedStatement);
            return true;
        }
        else {
            resultSet.close();
            closeBD(preparedStatement);
            return false;

        }


    }


}
