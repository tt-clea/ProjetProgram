import java.time.LocalDate;
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
    private List<Film> listeFilms;
	
    private Map<String, Abonnes> abonne;
	private Map<String, Film> film;
	private Map<String, Coffret> coffret;
	

	
	
	
	public Fonction() {
		abonne = new HashMap<>();
		film = new HashMap<>();
		coffret = new HashMap<>();
		

		
		
	}
	
	// Methods to add subscribers and films to their respective lists

    
    /**
     * Method to add a subscriber to the list, avoiding duplicates.
     * @param subscriber The subscriber to be added
     */
    public void addListAbonnes(Abonnes aAbonnes) {
        // Check if the subscriber is not already in the list
        if (!listeAbonnes.contains(aAbonnes)) {
        	listeAbonnes.add(aAbonnes);
            System.out.println("Abonné ajouté avec succès.");
        } else {
            System.out.println("Abonné est déjà dans la liste.");
        }
    }
    
    
    /**
     * Method to add a film to the list, avoiding duplicates.
     * @param film The film to be added
     */
    public void addListeFilm(Film aFilm) {
        // Check if the film is not already in the list
        if (!listeFilms.contains(aFilm)) {
        	listeFilms.add(aFilm);
            System.out.println("Film ajouté avec succès.");
        } else {
            System.out.println("Film est déjà dans la liste.");
        }
    }
    
    /**
     * Method to find a subscriber by their first name and last name.
     * @param firstName The first name of the subscriber
     * @param lastName The last name of the subscriber
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
    
    /**
     * Method to register a loan: a subscriber rents a film.
     * @param abonne The subscriber who is renting the film
     * @param film The film being rented
     * @param dateDebut The start date of the rental
     * @param dateFin The end date of the rental
     */
    public void enregistrerPret(Abonnes aAbonne, Film aFilm, CharSequence aDateDebut, CharSequence aDateFin) {
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
    
    
    /**
    * Extracts the subscribers within the same income bracket.
    * @param incomeBracket The income bracket to filter the subscribers
    * @return List of subscribers within the specified income bracket
    */
    
    
    public List<Abonnes> extraireAbonneMemeRevenu(String aFourchetteRevenus) {
        List<Abonnes> abonnesMemeRevenu = new ArrayList<>();

        for (Abonnes abonne : listeAbonnes) {
            if (abonne.getFourchetteRevenus().equals(aFourchetteRevenus)) {
                abonnesMemeRevenu.add(abonne);
            }
        }

        return abonnesMemeRevenu;
    }
    
    
    
    	
    	 public String extraireGenrePlusLoue() {
    	        Map<String, Integer> comptesGenre = new HashMap<>();
    	        //Calculate the frequency of each type of film rented

    	        for (Film film : listeFilms) {  // Iterate through each film in the film list
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
    	 
    	 /**
    	     * Extracts a list of films that are similar to the given title.
    	     * @param title The title of the film to search for.
    	     * @return A list of films with similar titles.
    	     */
    	
    	 public List<Film> extraireListeFilmSimilaire(Film film) { //browse the list of movies and add similar movies to the list 'filmSimilaires'
    		    List<Film> filmsSimilaires = new ArrayList<>();
    		    for (Film f : listeFilms) {
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
    	 
    	 
    	 
    	 
    	 /**
    	     * Extracts films with a "typed" audience, where subscribers renting these films are similar.
    	     * @param similarityThreshold The threshold of similarity between subscribers.
    	     * @return A list of films with a "typed" audience.
    	     */
    	    public List<Film> extraireListeFilmsSimilaireAbonne(double similariteSeuil) {
    	        List<Film> similaireAudienceFilms = new ArrayList<>();
    	        for (Film film : listeFilms) {
    	            boolean similaireAudience = true; // Check if the film has a similar audience
    	            List<Abonnes> rentingSubscribers = film.getRentingSubscribers();
    	            if (rentingSubscribers.size() > 1) { // If there are more than one subscriber for the film
    	                Abonnes firstSubscriber = rentingSubscribers.get(0); // Get the first subscriber to compare
    	                for (Abonnes subscriber : rentingSubscribers) { // Compare each subscriber to the first subscriber
    	                    if (!abonneSimilaire(firstSubscriber, subscriber, similariteSeuil)) { // If the subscribers are not similar, set similaireAudience to false and break the loop
    	                        similaireAudience = false;
    	                        break;
    	                    }
    	                }
    	            }
    	            if (similaireAudience) { // If the film has a similar audience, add it to the list
    	                similaireAudienceFilms.add(film);
    	            }
    	        }
    	        return similaireAudienceFilms;
    	    }
    	    
    	    /**
    	     * Checks if two subscribers are similar based on specified criteria.
    	     * @param subscriber1 The first subscriber.
    	     * @param subscriber2 The second subscriber.
    	     * @param similarityThreshold The threshold of similarity between subscribers.
    	     * @return True if the subscribers are similar, otherwise false.
    	     */
    	    private boolean abonneSimilaire(Abonnes aAbonne1, Abonnes aAbonne2, double similariteSeuil) {
    	        if (!aAbonne1.getSexeAb().equals(aAbonne2.getSexeAb())) { // Check if the subscribers have the same gender
    	            return false;
    	        }  // Check if the subscribers have the same income bracket
    	        if (!aAbonne1.getFourchetteRevenus().equals(aAbonne2.getFourchetteRevenus())) {
    	            return false;
    	        } // Calculate the difference in age between the subscribers
    	        int dateDifference = Math.abs(Integer.parseInt(aAbonne1.getDateNaissanceAb()) - Integer.parseInt(aAbonne2.getDateNaissanceAb()));
    	        return dateDifference <= 10 && (dateDifference / 10) <= similariteSeuil; // Check if the age difference is within the threshold for similarity
    	    }
    	    
    	    
    	    /**
    	     * Extracts subscribers who are closest to a typical profile based on the genre of films they rent.
    	     * @param targetGenre The target genre for comparison.
    	     * @return A list of subscribers closest to the typical profile.
    	     */
    	    public List<Abonnes> extraireListeAbonneProcheProfil(Genre targetGenre) {
    	        List<Abonnes> abonnesProchesProfil = new ArrayList<>();
    	        for (Abonnes abonne : listeAbonnes) {
    	            List<Film> filmsLoues = abonne.getLocationFilm();
    	            for (Film film : filmsLoues) {
    	                if (film.getGenre().equals(targetGenre)) {
    	                    abonnesProchesProfil.add(abonne);
    	                    break;
    	                }
    	            }
    	        }
    	        return abonnesProchesProfil;
    	    }
    	

    	 
    	 
    	 
    	 
    	 
    	 

 
	
	
	
	
	

}
