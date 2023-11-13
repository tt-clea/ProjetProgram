import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * 
 */

/**
 * 
 */
public class Fonction {
	
	// Mettre les MAP()
	
	private List<Abonnes> listeAbonnes;
    private List<Film> filmlist;
	
    private Map<String, Abonnes> abonne;
	private Map<String, Film> film;
	private Map<String, Coffret> coffret;
//	private Connection bd;
	private BdConnector bd;
	

	
	
	
	public Fonction(BdConnector bd) {
		this.bd=bd;
        abonne = new HashMap<>();
		film = new HashMap<>();
		coffret = new HashMap<>();
		this.listeAbonnes=new ArrayList<>();
		this.filmlist=new ArrayList<>();
	}



	//•	Ajouter un abonné dans une liste d'abonnés.
	// Methods to add subscribers and films to their respective lists

    
    /**
     * Method to add a subscriber to the list, avoiding duplicates.
     * @param aAbonne The subscriber to be added
     */
    public void addAbonnesBD(Abonnes aAbonne) throws SQLException {
        // Check if the subscriber is not already in the list
		boolean find=bd.findAbonne(aAbonne.getNomAb(),aAbonne.getPrenomAb());
		if(find)
		{
			//prenomAb,nomAb,dateNaissanceAb,sexeAb,fourchetteRevenus
			//insert les information to abonnes
			boolean insert=bd.insert_abonne(aAbonne.getPrenomAb(),aAbonne.getNomAb(),aAbonne.getDateNaissanceAb(),aAbonne.getSexeAb(),aAbonne.getFourchetteRevenus());
			if(insert)
			{
				System.out.println("L'abonné a été ajouté avec succès");
			}
			else {
				System.out.println("L'abonné a été pas ajouté");
			}
		}
		else {
			System.out.println("Ajouter fail ! Abonné est déjà dans la liste.");
		}
    }
    
    //•	Ajouter un film à une liste de films.
    /**
     * Method to add a film to the list, avoiding duplicates.
     * @param aFilm The film to be added
     */
    public void addListeFilm(Film aFilm) {
        // Check if the film is not already in the list
        if (!filmlist.contains(aFilm)) {
        	filmlist.add(aFilm);
            System.out.println("Film ajouté avec succès.");
        } else {
            System.out.println("Film est déjà dans la liste.");
        }
    }
    //•	Retrouver dans la liste des abonnés un abonné à partir de son nom et prénom.
    /**
     * Method to find a subscriber by their first name and last name.
     * @param aNom The first name of the subscriber
     * @param aPrenom The last name of the subscriber
     * @return The subscriber if found, otherwise null
     */
    public Abonnes trouverAbonne(String aPrenom, String aNom) {
        for (Abonnes abonne : listeAbonnes) {
            if (abonne.getNomAb().equals(aNom) && abonne.getPrenomAb().equals(aPrenom)) {
                return abonne;
            }
        }
        return null; // Return null if the subscriber is not found
    }
    //•	Enregistrer un prêt : un abonné loue un film.

	/**
	 * Method to register a loan: a subscriber rents a film.
	 * @param aAbonne
	 * @param aFilm
	 * @param aDateDebut
	 * @param aDateFin
	 */
	public void enregistrerPret(Abonnes aAbonne, Film aFilm, String aDateDebut, String aDateFin) {
        // Check if the film is available for rental
        if (aFilm.getNbStockage() > 0) {
            DateLocation dateLocation = new DateLocation(); // Create a new DateLocation object for the rental
            dateLocation.setDateDebut(aDateDebut); // Set the start date of the rental
            dateLocation.setDateFin(aDateFin); // Set the end date of the rental
            
            // Add the rental record to the subscriber's rental history
            aAbonne.getLocationFilm().add(aFilm);
            
            // Register the rental with the film
            aFilm.addLocation(aAbonne, dateLocation);
            System.out.println("Prêt enregistrée avec succès.");
        } else {
            System.out.println("Le film n'est pas disponible actuellement.");
        }
    }
    
    //•	Extraire les abonnés dans la même fourchette de revenu.
    
    /**
    * Extracts the subscribers within the same income bracket.
    * @param aFourchetteRevenus The income bracket to filter the subscribers
    * @return List of subscribers within the specified income bracket
    */
    
    
    public List<Abonnes> extraireAbonneMemeRevenu(int aFourchetteRevenus) {
        List<Abonnes> abonnesMemeRevenu = new ArrayList<>();

        for (Abonnes abonne : listeAbonnes) {
            if (abonne.getFourchetteRevenus()==aFourchetteRevenus) {
                abonnesMemeRevenu.add(abonne);
            }
        }

        return abonnesMemeRevenu;
    }
    
    //•	Extraire le genre de films le plus populaire (le plus loué).
    
    	
    	 public String extraireGenrePlusLoue() {
    	        Map<String, Integer> comptesGenre = new HashMap<>();
    	        //Calculate the frequency of each type of film rented

    	        for (Film film : filmlist) {  // Iterate through each film in the film list
    	            String genre = film.getGenre().toString(); // Get the genre of the film as a string
    	            comptesGenre.put(genre, comptesGenre.getOrDefault(genre, 0) + 1); // Update the count for the genre
    	        }
    	      //Find the most popular movie genre (most praised)

    	        String genrePlusPopulaire = null; // Initialize the most popular genre as null
    	        int compteMax = 0; // Initialize the maximum count as 0

    	        for (String genre : comptesGenre.keySet()) { // Iterate through each genre in the genre counts map
    	            int compte = comptesGenre.get(genre); // Get the count for the current genre
    	            if (compte > compteMax) { // Check if the count is greater than the current maximum count
    	                compteMax = compte; // If true, update the maximum count
    	                genrePlusPopulaire = genre;  //Also update the most popular genre
    	            }
    	        }

    	        return genrePlusPopulaire; // Return the most rented film genre
    	    }

    
   /** public Genre extraireGenrePlusLoue() {
    	
    	
    	
    	
    	
    	
    	
        Map<Genre, Integer> genreFrequences = new HashMap<>();

        // Calculer la fréquence de chaque genre de film loué
        for (Film film : listeFilms) {
            Genre genre = film.getGenre();
            if (genreFrequences.containsKey(genre)) {
                genreFrequences.put(genre, genreFrequences.get(genre) + 1);
            } else {
                genreFrequences.put(genre, 1);
            }
        }

        // Trouver le genre de film le plus populaire (le plus loué)
        Genre genrePlusLoue = null;
        int maxFrequence = 0;
        for (Map.Entry<Genre, Integer> entry : genreFrequences.entrySet()) {
            if (entry.getValue() > maxFrequence) {
                genrePlusLoue = entry.getKey();
                maxFrequence = entry.getValue();
            }
        }

        return genrePlusLoue;
        
        
        */
    	 
    	 //•Extraire de la liste des films les films les plus similaires à un film ayant le titre « ? ».
    	 
    	 /**
    	     * Extracts a list of films that are similar to the given title.
    	     * @param film The title of the film to search for.
    	     * @return A list of films with similar titles.
    	     */
    	
    	 public List<Film> extraireListeFilmSimilaire(Film film) { //browse the list of movies and add similar movies to the list 'filmSimilaires'
    		    List<Film> filmsSimilaires = new ArrayList<>();
    		    for (Film f : filmlist) {
    		        if (f.getTitreF().equals(film.getTitreF()) && isFilmSimilaire(f, film)) {
    		            filmsSimilaires.add(f);
    		        }
    		    }
    		    return filmsSimilaires;
    		}

    	 private boolean isFilmSimilaire(Film film1, Film film2) { // used to compare similarity criteria such as genre, actors and color between two films.
    		    // Compare the genres of the films
    		    if (!film1.getGenre().equals(film2.getGenre())) {
    		        return false;
    		    }

    		    // Comparer the actors
    		    List<Acteurs> acteurs1 = film1.getActeursList();
    		    List<Acteurs> acteurs2 = film2.getActeursList();
    		    boolean acteurSimilaire = false;
    		    for (Acteurs a1 : acteurs1) {
    		        for (Acteurs a2 : acteurs2) {
    		            if (a1.equals(a2)) {
    		                acteurSimilaire = true;
    		                break;
    		            }
    		        }
    		    }
    		    if (!acteurSimilaire) {
    		        return false;
    		    }

    		    // Comparer the colors
    		    if (film1.isCouleurF() != film2.isCouleurF()) {
    		        return false;
    		    }

    		    return true; // returns true If all similarity criteria are met 
    		}
    	 
    	 //•	Extraire de la liste des abonnés, les abonnés « les plus curieux » : les films loués par ces abonnés se ressemblent le moins possible.
    	 
    	 /**
    	  * Extracts a list of subscribers who do not watch similar films.
    	  * @return A list of subscribers who watch dissimilar films.
    	  */
    	 
    	 
    	 public List<Abonnes> extraireListeAbonnesSimilaireLoue() {
    		    List<Abonnes> LoueNonSimilaires = new ArrayList<>(); // Initialize a list to store subscribers who watch dissimilar films
    		    for (Abonnes abonne : listeAbonnes) { // Iterate through each subscriber in the list
    		        boolean estCurieux = true; // Initialize if the subscriber is curious or not
    		        List<Film> filmsLoues = abonne.getLocationFilm(); // Get the list of films rented by the subscriber
    		        for (int i = 0; i < filmsLoues.size(); i++) { // Loop through each film in the rented films list
    		            for (int j = i + 1; j < filmsLoues.size(); j++) { // Loop through the remaining films in the rented films list
    		                if (isFilmSimilaire(filmsLoues.get(i), filmsLoues.get(j))) { // Check if the films are similar
    		                    estCurieux = false; // If similar films are found, set the flag to false
    		                    break; // Break out of the inner loop
    		                }
    		            }
    		            if (!estCurieux) { // If the subscriber is found to not be curious, break out of the outer loop break;
    		                break;
    		            }
    		        }
    		        if (estCurieux) { // If the subscriber is curious, add them to the list of LoueNonSimilaires
    		            LoueNonSimilaires.add(abonne);
    		        }
    		    }
    		    return LoueNonSimilaires; // Return the list of subscribers who watch dissimilar films
    		}
    	 
    	 //--------------------------------------------------A VOIR --------------------------
    	 
    	 
//
//    	 //•	Extraire de la liste des films les films ayant un public « typé » : les abonnés louant ces films sont similaires (on fournira en paramètre le seuil de similarité entre abonnés).
//    	 /**
//    	     * Extracts films with a "typed" audience, where subscribers renting these films are similar.
//    	     * @param similariteSeuil The threshold of similarity between subscribers.
//    	     * @return A list of films with a "typed" audience.
//    	     */
//    	    public List<Film> extraireListeFilmsSimilaireAbonne(double similariteSeuil) {
//    	        List<Film> similaireAudienceFilms = new ArrayList<>();
//    	        for (Film film : filmlist) {
//    	            boolean similaireAudience = true; // Check if the film has a similar audience
//    	            List<Abonnes> rentingSubscribers = film.getRentingSubscribers();
//    	            if (rentingSubscribers.size() > 1) { // If there are more than one subscriber for the film
//    	                Abonnes firstSubscriber = rentingSubscribers.get(0); // Get the first subscriber to compare
//    	                for (Abonnes subscriber : rentingSubscribers) { // Compare each subscriber to the first subscriber
//    	                    if (!abonneSimilaire(firstSubscriber, subscriber, similariteSeuil)) { // If the subscribers are not similar, set similaireAudience to false and break the loop
//    	                        similaireAudience = false;
//    	                        break;
//    	                    }
//    	                }
//    	            }
//    	            if (similaireAudience) { // If the film has a similar audience, add it to the list
//    	                similaireAudienceFilms.add(film);
//    	            }
//    	        }
//    	        return similaireAudienceFilms;
//    	    }
//
//

    	   
//
//		// Calculate the age of the subscriber based on the date of birth
//    	   /** private int calculateAge(String dateNaissanceAb) {
//    	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");// Define the date formatter for the subscriber's date of birth
//    	        LocalDate birthDate = LocalDate.parse(dateNaissanceAb, formatter);// Parse the subscriber's date of birth into a LocalDate object
//    	        LocalDate currentDate = LocalDate.now(); // Get the current date
//    	        return Period.between(birthDate, currentDate).getYears();// Calculate the age based on the difference between the current date and the date of birth
//
//    	    }*/
//
//
//    	    /**
//    	     * Extracts films with a "typed" audience based on subscriber similarity.
//    	     * @param similarityThreshold The threshold of similarity between subscribers.
//    	     * @return A list of films with a "typed" audience.
//    	     */
//    	    public List<Film> extractTypedAudienceFilms(double similarityThreshold) {
//    	        List<Film> typedAudienceFilms = new ArrayList<>();
//
//    	        // Iterate through each film
//    	        for (Film film : filmlist) {
//    	            // Calculate the total number of subscribers for the film
//    	            int totalSubscribers = film.getRentingSubscribers().size();
//
//    	            // Check if the film has more than one subscriber
//    	            if (totalSubscribers > 1) {
//    	                // Calculate the similarity percentage for each subscriber
//    	                Map<Abonnes, Double> similarityMap = new HashMap<>();
//    	                for (Abonnes subscriber : film.getRentingSubscribers()) {
//    	                    double similarity = abonneSimilaire(subscriber, film, subscriber);
//    	                    similarityMap.put(subscriber, similarity);
//    	                }
//
//    	                // Check if there are subscribers with similarity percentage greater than or equal to 70%
//    	                for (Map.Entry<Abonnes, Double> entry : similarityMap.entrySet()) {
//    	                    if (entry.getValue() >= similarityThreshold) {
//    	                        typedAudienceFilms.add(film);
//    	                        break;
//    	                    }
//    	                }
//    	            }
//    	        }
//
//    	        return typedAudienceFilms;
//    	    }

    	    
    	    
    	    
    	    
    	    
    	    
    	    
//
//
//    	    /**
//    	     * Calculate the similarity percentage between a subscriber and a film.
//    	     * @param subscriber The subscriber.
//    	     * @param film The film.
//    	     * @return The similarity percentage.
//    	     */
//    	    private double abonneSimilaire(Abonnes subscriber, Film film,Abonnes subscriber2 ) {
//    	        double similarityPercentage = 0.0;
//
//    	        // Check if they have the same gender
//    	        if (subscriber.getSexeAb().equals(film.getGenre().getGenreNom())) {
//    	            similarityPercentage += 40.0;
//    	        }
//
//    	        // Check if they are in the same age bracket (not more than 10 years apart)
//    	        int ageDifference = Math.abs(calculateAge(subscriber.getDateNaissanceAb()) - calculateAge(subscriber2.getDateNaissanceAb()));
//    	        if (ageDifference <= 10) {
//    	            similarityPercentage += 30.0;
//    	        } else if (ageDifference <= 20) {
//    	            similarityPercentage += 20.0;
//    	        } else if (ageDifference <= 30) {
//    	            similarityPercentage += 10.0;
//    	        }
//
//    	        // Check if they are in the same income bracket
//    	        if (subscriber.getFourchetteRevenus().equals(film.getGenre().getGenreNom())) {
//    	            similarityPercentage += 30.0;
//    	        }
//
//    	        return similarityPercentage;
//    	    }
//    	 // Calculate the age of the subscriber based on the date of birth
//    	    private int calculateAge(String dateNaissanceAb) {
//    	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");// Define the date formatter for the subscriber's date of birth
//    	        LocalDate birthDate = LocalDate.parse(dateNaissanceAb, formatter);// Parse the subscriber's date of birth into a LocalDate object
//    	        LocalDate currentDate = LocalDate.now(); // Get the current date
//    	        return Period.between(birthDate, currentDate).getYears();// Calculate the age based on the difference between the current date and the date of birth
//
//    	    }
//
//
//
    	    
    	    //---------------------------------------------------------------------
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    //•	Extraire de la liste des abonnés, les abonnés les plus proches d'un profil type.

    	    /**
    	     * Extracts subscribers who are closest to a typical profile based on the genre and sub-genre of films they rent.
    	     * @param targetGenre The target genre for comparison.
    	     * @return A list of subscribers closest to the typical profile.
    	     */
    	    public List<Abonnes> extraireListeAbonneProcheProfil(Genre targetGenre) {
    	        List<Abonnes> abonnesProchesProfil = new ArrayList<>();
    	        for (Abonnes abonne : listeAbonnes) {
    	            List<Film> filmsLoues = abonne.getLocationFilm();
    	            for (Film film : filmsLoues) {
    	                // Check if the genre of the film is the same as the target genre
    	                if (film.getGenre().equals(targetGenre)) {
    	                    abonnesProchesProfil.add(abonne);
    	                    break;
    	                }
    	                // Check if the genre of the film is an instance of a sub-genre
    	                if (film.getGenre().getSubGenre() != null && !film.getGenre().getSubGenre().isEmpty()) {
    	                    for (Genre subGenre : film.getGenre().getSubGenre()) {
    	                        // Check if the sub-genre of the film is the same as the target genre
    	                        if (subGenre.getGenreNom().equals(targetGenre.getGenreNom())) {
    	                            abonnesProchesProfil.add(abonne);
    	                            break;
    	                        }
    	                    }
    	                }
    	            }
    	        }
    	        return abonnesProchesProfil;
    	    }

    	 
    	 
    	 
    	 

 
	
	
	
	
	

}
