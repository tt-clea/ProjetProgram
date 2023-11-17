import java.sql.*;
import java.sql.PreparedStatement;
import java.util.*;

//Declaration of the class BdConnector
public class BdConnector {
    private static String path;
 // Private static variable to store the path for the database connection
    public BdConnector(String path) {
        this.path = path;
    }

 // Getter method to retrieve the database path
    public static String getPath() {
        return path;
    }
 // Static block for class initialization, responsible for loading the SQLite JDBC driver
    
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
        	// Throw a runtime exception if the SQLite JDBC driver is not found
            throw new RuntimeException("Failed to connect SQLite JDBC driver");
        }
    }
 // Method to establish a database connection

    public static Connection connect() {
        try {// Attempt to establish a connection to the database using the stored path
         //System.out.println("connection successful!");
            return DriverManager.getConnection(getPath());
        } catch (SQLException e) {
        	// Print a connection failure message and stack trace in case of an exception
            System.out.println("connection failure!");
            e.printStackTrace();
            return null;
        }
    }
 // Method to close the PreparedStatement and Connection objects

    public static void closeBD(PreparedStatement preparedStatement) {
        try {
        	// Close the PreparedStatement if it is not null
            if (preparedStatement != null) preparedStatement.close();
            if (connect() != null) connect().close();
        } catch (SQLException e) {
        	// Print the stack trace in case of an exception during closing
            e.printStackTrace();
        }
    }
    // Method to insert a new record into the 'Coffret' table with the provided title, genre, and bonus information

    public boolean insert_coffret(String titreC,String genreC,boolean bonus) throws SQLException {
    	// SQL query to insert values into the 'Coffret' table
        String query="insert into Coffret(titreC, genreC, bonus) VALUES (?,?,?)";
     // Creating a prepared statement using the database connection
        PreparedStatement preparedStatement = connect().prepareStatement(query);
     // Setting attributes for the prepared statement
        preparedStatement.setString(1, titreC);
        preparedStatement.setString(2, genreC);
        preparedStatement.setBoolean(3, bonus);
     // Executing the SQL update and storing the number of rows affected
        int rowsInserted = preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.executeQuery();
     // Closing the prepared statement and returning true if the insertion was successful, otherwise, returning false
        if (rowsInserted > 0) {

            closeBD(preparedStatement);
            return true;
        } else {

            closeBD(preparedStatement);
            return false;
        }
    }
 // Method to insert a new record into the 'Genre' table with the provided genre names
    
    public boolean insert_genre(String genreFather,String genreSon) throws SQLException {
        String query="insert into Genre(genreNom, SubGenre) VALUES (?,?);";
        // SQL query to insert values into the 'Genre' table
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
 // Method to insert a new record into the 'Acteurs' table with the provided first name and last name of an actor
    

    public boolean insert_actor(String nomA,String prenomA) throws SQLException {
        String query="insert into Acteurs(prenomA, nomA) VALUES (?,?);";  
        // SQL query to insert values into the 'Acteurs' table
        

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
 // Method to insert a new record into the 'avoir_film_coffret' association with the provided IDs of a coffret and a film
    
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
 // Method to insert a new record into the 'avoir_acteur_film' association with the provided IDs of a film and an actor
    
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




 // Method to insert a new record into the 'Abonnes' table with the provided information about a subscriber
    
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
 // Method to insert a new record into the 'Film' table with the provided information about a film
    
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
 // Method to find a subscriber by name in the 'Abonnes' table
    public boolean findAbonneParNom(String Nom, String Prenom) throws SQLException {
    	 // SQL query to select records from 'Abonnes' where the last name and first name match the provided values
        String query = "SELECT * FROM Abonnes where nomAb=? and prenomAb=?;";
     // Creating a prepared statement using the database connection
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, Nom);
        preparedStatement.setString(2, Prenom);
     // Executing the SQL query and storing the result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // Executing the SQL query and storing the result set
        if (resultSet.next()) {
        	// Extracting information from the result set
            String firstname = resultSet.getString("prenomAb");
            String lastname = resultSet.getString("nomAb");
         // Printing a message indicating the existence of the subscriber

            System.out.println("L'abonné " + firstname + " " + lastname + " existe");
         // Closing the result set and prepared statement, and returning true
            resultSet.close();
            closeBD(preparedStatement);
            return true;
        } else { // Closing the result set and prepared statement, and returning false
            resultSet.close();
            closeBD(preparedStatement);
            return false;

        }
    }
 // Method to find a subscriber by ID in the 'Abonnes' table and retrieve their name and last name
    
    public List<String> findAbonneParId(int id) throws SQLException {
    	// SQL query to select the name and last name of a subscriber by ID
        String query = "select nomAb,prenomAb from Abonnes where id=?;";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setInt(1, id);
     // Creating a list to store the name and last name of the subscriber
        List<String> profil=new ArrayList<>();
     // Executing the SQL query and storing the result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // Iterating through the result set and adding the name and last name to the list
        
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




 // Method to find a film by title in the 'Film' table

    public boolean findFilm(String titreF) throws SQLException {
        String query = "select * from Film where titreF= ?";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // set attribut
        preparedStatement.setString(1, titreF);
        ResultSet resultSet = preparedStatement.executeQuery();
     // Checking if a record exists with the provided title
        if (resultSet.next()) {
        	// Extracting information from the result set
            int id_Film = resultSet.getInt("id");
            String titre_Film = resultSet.getString("titreF");
            String couleur_Film = resultSet.getString("couleurF");
            int NbStrockage_Film = resultSet.getInt("NbStockage");
            String genre_Film = resultSet.getString("genreF");
         // Printing details of the film
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
 // Method to find film details by title and return a list of film attributes
    
    public List<Object> findFilm2(String titreF) throws SQLException {
    	// SQL query to select records from 'Film' where the title matches the provided value
        String query = "select * from Film where titreF= ?";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // Setting attributes for the prepared statement
        preparedStatement.setString(1, titreF);
        // Executing the SQL query and storing the result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // Creating a list to store film details
        List<Object> film = new ArrayList<>();
        // Iterating through the result set and adding film details to the list
        while (resultSet.next()) {
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
        // Closing the result set and prepared statement, and returning the list of film details
        resultSet.close();
        closeBD(preparedStatement);
        return film;
    }

 // Method to find the number of available stock for a film based on the film title
    public int findNbFilm(String titreF) throws SQLException {
        // SQL query to select the number of available stock for a film
        String query = "select NbStockage from Film where titreF=?;";
        // Creating a prepared statement using the database connection
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // Setting attributes for the prepared statement
        preparedStatement.setString(1, titreF);
        // Executing the SQL query and storing the result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // Checking if the result set has a record
        if (resultSet.next()) {
            // Extracting the number of available stock from the result set
            int nb = resultSet.getInt("NbStockage");
            // Printing the number of available stock
            System.out.println("Number of stock is: " + nb);
            // Closing the result set and prepared statement, and returning the number of available stock
            resultSet.close();
            closeBD(preparedStatement);
            return nb;
        } else {
            // Closing the result set and prepared statement, and returning -1 to indicate no record found
            resultSet.close();
            closeBD(preparedStatement);
            return -1;
        }
    }

 // Method to find all subscribers and their respective income brackets
    public Map<List<Object>, Integer> findAllAbonnes() throws SQLException {
        // SQL query to select all records from 'Abonnes'
        String query = "select * from Abonnes;";
        // Creating a prepared statement using the database connection
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // Executing the SQL query and storing the result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // Creating a map to store each subscriber's information along with their income bracket
        Map<List<Object>, Integer> allAbonne = new HashMap<>();
        // Iterating through the result set and adding subscriber details and income bracket to the map
        while (resultSet.next()) {
            List<Object> chaqueAbonnes = new ArrayList<>();
            int id_abonne = resultSet.getInt("id");
            String prenom = resultSet.getString("prenomAb");
            String nom = resultSet.getString("nomAb");
            String birth = resultSet.getString("dateNaissanceAb");
            String sexeAb = resultSet.getString("sexeAb");
            int money = resultSet.getInt("fourchetteRevenus");
            chaqueAbonnes.add(id_abonne);
            chaqueAbonnes.add(prenom);
            chaqueAbonnes.add(nom);
            chaqueAbonnes.add(sexeAb);
            chaqueAbonnes.add(birth);
            allAbonne.put(chaqueAbonnes, money);
        }
        // Closing the result set and prepared statement, and returning the map
        resultSet.close();
        closeBD(preparedStatement);
        return allAbonne;
    }

 // Method to find the count of films rented in each genre
    public Map<String, Integer> findFilmLoue() throws SQLException {
        // SQL query to select the count of films rented in each genre
        String query = "select genreF, count(*) from Historique, Film where Historique.id_film = Film.id group by genreF;";
        // Creating a prepared statement using the database connection
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // Executing the SQL query and storing the result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // Creating a map to store each genre and the count of films rented in that genre
        Map<String, Integer> listoffilm = new HashMap<>();
        // Iterating through the result set and adding genre and count to the map
        while (resultSet.next()) {
            String titreF = resultSet.getString("genreF");
            int count = resultSet.getInt("count(*)");
            listoffilm.put(titreF, count);
        }
        // Closing the result set and prepared statement, and returning the map
        resultSet.close();
        closeBD(preparedStatement);
        return listoffilm;
    }

 // Method to find films based on a keyword in the title
    public List<List<Object>> findFilmByKeyWord(String kw) throws SQLException {
        // Creating a list to store films based on the keyword in the title
        List<List<Object>> map_film = new ArrayList<>();
        // Formatting the keyword to search for partial matches
        String keyword = "%" + kw + "%";
        // SQL query to select records from 'Film' where the title contains the provided keyword
        String query = "SELECT * FROM Film WHERE titreF LIKE ?;";
        // Creating a prepared statement using the database connection
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // Setting attributes for the prepared statement
        preparedStatement.setString(1, keyword);
        // Executing the SQL query and storing the result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // Iterating through the result set and adding film details to the list
        while (resultSet.next()) {
            List<Object> chaquefilm = new ArrayList<>();
            String titreF = resultSet.getString("titreF");
            String couleurF = resultSet.getString("couleurF");
            int NbStockage = resultSet.getInt("NbStockage");
            String genreF = resultSet.getString("genreF");
            chaquefilm.add(titreF);
            chaquefilm.add(couleurF);
            chaquefilm.add(NbStockage);
            chaquefilm.add(genreF);
            map_film.add(chaquefilm);
        }
        // Closing the result set and prepared statement, and returning the list of films
        resultSet.close();
        closeBD(preparedStatement);
        return map_film;
    }

 // Method to return details of all films in the 'Film' table
    public List<List<Object>> ReturnFilm() throws SQLException {
        // Creating a list to store details of all films
        List<List<Object>> list_film = new ArrayList<>();
        // SQL query to select all records from 'Film'
        String query = "select * from Film;";
        // Creating a prepared statement using the database connection
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // Executing the SQL query and storing the result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // Iterating through the result set and adding details of each film to the list
        while (resultSet.next()) {
            List<Object> chaque_film = new ArrayList<>();
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
        // Closing the result set and prepared statement, and returning the list of films
        resultSet.close();
        closeBD(preparedStatement);
        return list_film;
    }

 // Method to find the history of films rented by a subscriber based on their ID
    public List<List<String>> findHistorique(int id) throws SQLException {
        // SQL query to select the name and last name of subscribers who rented a film based on film ID
        String query = "select nomAb, prenomAb from Abonnes, Historique where id=Historique.id_abonne and id_film=?;";
        // Creating a prepared statement using the database connection
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // Setting attributes for the prepared statement
        preparedStatement.setInt(1, id);
        // Creating a list to store the name and last name of subscribers
        List<List<String>> abonne_list = new ArrayList<>();
        // Executing the SQL query and storing the result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // Iterating through the result set and adding the name and last name to the list
        while (resultSet.next()) {
            List<String> abonne = new ArrayList<>();
            String firstname = resultSet.getString("prenomAb");
            String lastname = resultSet.getString("nomAb");
            abonne.add(firstname);
            abonne.add(lastname);
            abonne_list.add(abonne);
        }
        // Closing the result set and prepared statement, and returning the list of subscribers
        resultSet.close();
        closeBD(preparedStatement);
        return abonne_list;
    }


 // Method to find the titles of films rented by a subscriber based on their ID
    public List<String> findTitreFilmSelonAbonnes(int id_abonne) throws SQLException {
        // Creating a list to store the titles of films rented by a subscriber
        List<String> listoffilm = new ArrayList<>();
        // SQL query to select the title of films rented by a subscriber based on subscriber ID
        String query = "select titreF from Film, Historique where id=id_film and id_abonne=?;";
        // Creating a prepared statement using the database connection
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        // Setting attributes for the prepared statement
        preparedStatement.setInt(1, id_abonne);
        // Executing the SQL query and storing the result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // Iterating through the result set and adding the titles to the list
        while (resultSet.next()) {
            String titreF = resultSet.getString("titreF");
            listoffilm.add(titreF);
        }
        // Closing the result set and prepared statement, and returning the list of film titles
        resultSet.close();
        closeBD(preparedStatement);
        return listoffilm;
    }


    //list films par chaque coffret
    //Map<Coffret:Nom , List[Films]>
    
    /**
     * Method to retrieve a map of films grouped by coffret titles from the 'Film', 'avoir_film_coffret', and 'Coffret' tables.
     * @return Map containing coffret titles and a list of films associated with each coffret.
     * @throws SQLException If a database access error occurs.
     */
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
 

 
    /**
     * Method to find actors associated with each film from the 'Film', 'avoir_acteur_film', and 'Acteurs' tables.
     * @return Map containing film titles and a list of actors associated with each film.
     * @throws SQLException If a database access error occurs.
     */
    public Map<String,List<String>> findActorFromFilm() throws SQLException {
        String query="select titreF, nomA,prenomA from Film,avoir_acteur_film,Acteurs where id_film=Film.id and id_acteur=Acteurs.id;";
        PreparedStatement preparedStatement= connect().prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        Map<String,List<String>> actor_list=new HashMap<>();
        while (resultSet.next())
        {
            String titreF=resultSet.getString("titreF");
            String nomA=resultSet.getString("nomA");
            String prenomA=resultSet.getString("prenomA");
            String fullnom=nomA+" "+prenomA;
            if (actor_list.containsKey(titreF))
            {

                actor_list.get(titreF).add(fullnom);
            }
            else {
                actor_list.put(titreF,new ArrayList<>());
                actor_list.get(titreF).add(fullnom);
            }
        }
        return actor_list;
    }




    /////////////////////// return id /////////////////////////////////////////
    
    /**
    * Method to get the ID of a subscriber based on their name and surname.
    * @param Nom Last name of the subscriber.
    * @param Prenom First name of the subscriber.
    * @return Subscriber ID if found, -1 otherwise.
    * @throws SQLException If a database access error occurs.
    */
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
    
    
    /**
     * Method to get the ID of a film based on its title.
     * @param titreF Title of the film.
     * @return Film ID if found, -1 otherwise.
     * @throws SQLException If a database access error occurs.
     */
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
    
    /**
     * Method to get the ID of a coffret based on its title.
     * @param titreC Title of the coffret.
     * @return Coffret ID if found, -1 otherwise.
     * @throws SQLException If a database access error occurs.
     */
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

    
    /**
     * Method to get the ID of an actor based on their name and surname.
     * @param nomA Last name of the actor.
     * @param prenomA First name of the actor.
     * @return Actor ID if found, -1 otherwise.
     * @throws SQLException If a database access error occurs.
     */
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


    ///////////////////////// function  ////////////////////////////
    
    /**
     * Method to log the rental history of a film for a subscriber.
     * @param id_film Film ID.
     * @param id_abonne Subscriber ID.
     * @param DateDebut Start date of the rental.
     * @return True if logging is successful, false otherwise.
     * @throws SQLException If a database access error occurs.
     */
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

    
    /**
     * Method to update the number of available copies of a film in the 'Film' table.
     * @param titreF Title of the film.
     * @return True if the update is successful, false otherwise.
     * @throws SQLException If a database access error occurs.
     */
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