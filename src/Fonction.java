import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 */

/**
 * 
 */
public class Fonction {
	
	// Mettre les MAP()
	
//	private List<Abonnes> listeAbonnes;
//    private List<Film> filmlist;
//
//    private Map<String, Abonnes> abonne;
//	private Map<String, Film> film;
//	private Map<String, Coffret> coffret;
//	private Connection bd;
	private BdConnector bd;
	private Evolues evolues;
	private Abonnes abonnes;
	private Film films;


	public Fonction(BdConnector bd) {
		this.bd=bd;
		this.abonnes=abonnes;
		this.evolues=new Evolues();
		this.films = new Film();
//        abonne = new HashMap<>();
//		film = new HashMap<>();
//		coffret = new HashMap<>();
//		this.listeAbonnes=new ArrayList<>();
//		this.filmlist=new ArrayList<>();
//		this.evolues=new Evolues();
	}



	//•	Ajouter un abonné dans une liste d'abonnés.
	// Methods to add subscribers and films to their respective lists

    
    /**
     * Method to add a subscriber to the list, avoiding duplicates.
     * @param aAbonne The subscriber to be added
     */
    public void addAbonnesBD(Abonnes aAbonne) throws SQLException {
        // Check if the subscriber is not already in the list

		boolean find=bd.findAbonneParNom(aAbonne.getNomAb(),aAbonne.getPrenomAb());
		if(!find)
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
    public void addFilmBD(Film aFilm) throws SQLException {
        // Check if the film is not already in the list
		boolean find=bd.findFilm(aFilm.getTitreF());
		// if not find film in DB
		if(!find)
		{
			//insert film
			boolean insert=bd.insert_film(aFilm.getTitreF(),aFilm.isCouleurF(),aFilm.getNbStockage(),aFilm.getGenre());
			if (insert)
			{
				System.out.println("Film ajouté avec succès.");
			}
			else {
				System.out.println("Film ajouté pas.");
			}
		}
		else {
			System.out.println("Film est déjà dans la liste.");
		}
    }





	public void findAbonnes(String aNom,String aPrenom) throws SQLException {
		if(bd.findAbonneParNom(aNom,aPrenom))
		{
			System.out.println("l'abonne existe");
		}
		else {
			System.out.println("l'abonne existe pas");
		}

	}
    //•	Retrouver dans la liste des abonnés un abonné à partir de son nom et prénom.

	//



    //•	Enregistrer un prêt : un abonné loue un film.

	/**
	 * Method to register a loan: a subscriber rents a film.
	 * @param aAbonne
	 * @param aFilm
	 * @param dateLocation
	 * @throws SQLException
	 */
	public void enregistrerPret(Abonnes aAbonne, Film aFilm, DateLocation dateLocation) throws SQLException {

		//check if the film is exist
		boolean isFilm= bd.findFilm(aFilm.getTitreF());
		if(isFilm)
		{
			//Check stock quantities
			int isNb=bd.findNbFilm(aFilm.getTitreF());
			if (isNb>0)
			{
				//insert into BD historique
				int id_abonne= bd.get_id_abonne(aAbonne.getNomAb(),aAbonne.getPrenomAb());
				int id_film=bd.get_id_film(aFilm.getTitreF());
				boolean isHistorique=bd.historique(id_film,id_abonne,dateLocation.getDateDebut());
				//check if recording success
				if (isHistorique)
				{
					System.out.println("L'abonné "+aAbonne.getNomAb()+" s'abonne avec succès au film "+aFilm.getTitreF());
					//mis a jour des stockage de film
					boolean isMisAJour=bd.mis_a_jour_Nb(aFilm.getTitreF());
					if(isMisAJour)
					{
						System.out.println("Stock levels updated successfully");
					}
					else {
						System.out.println("Stock levels updated failed");
					}
				}
				else {
					System.out.println("Recording failed");
				}
			}
			else {
				System.out.println("Le film n'est pas disponible actuellement.");
			}
		}
    }

	/**
	 * Extracts the subscribers within the same income bracket.
	 * **/
	public Map<String,List<String>> extraireAbonneMemeRevenu() throws SQLException {
		//find all of actors in DB

		Map<List<Object>,Integer> allAbonne=bd.findAllAbonnes();
		Map<String,List<String>> abonneMeme=new HashMap<>();
		abonneMeme.put("L1",new ArrayList<>());
		abonneMeme.put("L2",new ArrayList<>());
		abonneMeme.put("L3",new ArrayList<>());
		abonneMeme.put("L4",new ArrayList<>());
		abonneMeme.put("L5",new ArrayList<>());
		abonneMeme.put("L6",new ArrayList<>());
		for (Map.Entry<List<Object>,Integer> entry1: allAbonne.entrySet())
		{
			int salary=entry1.getValue();
			String trancher=evolues.trancherLevel(salary);
			List<String> valueList=abonneMeme.get(trancher);
			valueList.add(entry1.getKey().toString());
		}
		return abonneMeme;

	}




	public void PlusLoueGenre() throws SQLException {
		//Get the number and names of movies being rented
		Map<String,Integer> genre_count=bd.findFilmLoue();
		Entry<String,Integer> max=null;
		for (Entry<String,Integer> entry:genre_count.entrySet())
		{
			if(max==null||entry.getValue().compareTo(max.getValue())>0)
			{
				max=entry;
			}
		}

		System.out.println("Le genre de films le plus populaire est :"+max.getKey() +"\n"+"Nombre de location est "+max.getValue());
	}

	public void KeyWordFilm(String keyword) throws SQLException {
		List<List<Object>> list_of_film=bd.findFilmByKeyWord(keyword);
		System.out.println(list_of_film);
	}



	public void findSimilaireFilm(Genre tree) throws SQLException {
		//return list_of_film
		List<List<Object>> list_of_film=bd.ReturnFilm();
		Map<List<Integer>,Integer> evolu_films=new HashMap<>();
		for (List<Object> lists1:list_of_film)
		{
			for (List<Object> lists2:list_of_film)
			{
				if (!lists1.equals(lists2))
				{
					//similarite_Film(Genre g,Film films1,Film films2)

					String titre1= (String) lists1.get(1);
					boolean couleur1=true;
					if (lists1.get(2).equals("1"))
					{
						couleur1= true;

					}
					else {
						couleur1= false;
					}
					int Nb1= (int) lists1.get(3);
					String genre1= (String) lists1.get(4);
					Film f1=new Film(titre1,couleur1,genre1,Nb1);

					String titre2= (String) lists2.get(1);
					boolean couleur2=true;
					if (lists1.get(2).equals("1"))
					{
						couleur2= true;

					}
					else {
						couleur2= false;
					}
					int Nb2= (int) lists2.get(3);
					String genre2= (String) lists2.get(4);
					Film f2=new Film(titre2,couleur2,genre2,Nb2);
					//Map<<[id1 id2]>,int>
					List<Integer> id_film_abonne=new ArrayList<>();
					id_film_abonne.add((Integer) lists1.get(0));
					id_film_abonne.add((Integer) lists2.get(0));
					int similarite_film=evolues.similarite_Film(tree,f1,f2);
					evolu_films.put(id_film_abonne,similarite_film);
				}
            }
		}
		System.out.println(evolu_films);

		// convert Map.Entry to List
		List<Map.Entry<List<Integer>, Integer>> entryList = new ArrayList<>(evolu_films.entrySet());

		// use Comparator to define rules in ascending order
		Collections.sort(entryList, Comparator.comparing(Map.Entry::getValue));

		List<List<String>> abonne_list=new ArrayList<>();
		// print results
		for (Map.Entry<List<Integer>, Integer> entry : entryList) {
			List<Integer> key = entry.getKey();

			//convert 2 films
			for (Integer element : key) {
				//find historique in bd;
				if (bd.findHistorique(element)!=null)
				{
					abonne_list=bd.findHistorique(element);
					break;
				}
			}
		}
		System.out.println(abonne_list);
	}


	//les abonnes sont similaires, on fournira en paramètre le seuil de similarite entre abonnés
	public Map<Integer,List<Integer>> AbonnesSimilarite() throws SQLException {
		// find all of abonnes in BD
		Map<List<Object>,Integer> all_abonne=bd.findAllAbonnes();

		//group of the similarite of abonnes , {0:[],1:[],2:[]}
		Map<Integer,List<Integer>> group_abonne_similarite=new HashMap<>();
		//calculate les abonnes similarite mettre ensemble
		for(Map.Entry<List<Object>,Integer> entry1: all_abonne.entrySet())
		{
			for(Map.Entry<List<Object>,Integer> entry2: all_abonne.entrySet())
			{
				if(!entry1.equals(entry2))
				{

					int id_abonne1= (int) entry1.getKey().get(0);
					String prenom1= (String) entry1.getKey().get(1);
					String nom1 = (String) entry1.getKey().get(2);
					String sexe1= (String) entry1.getKey().get(3);
					String birth1 = (String) entry1.getKey().get(4);
					int money1=entry1.getValue();
					Abonnes abonnes1=new Abonnes(nom1,prenom1,birth1,sexe1,money1);

					int id_abonne2= (int) entry2.getKey().get(0);
					String prenom2= (String) entry2.getKey().get(1);
					String nom2 = (String) entry2.getKey().get(2);
					String sexe2= (String) entry2.getKey().get(3);
					String birth2 = (String) entry2.getKey().get(4);
					int money2=entry2.getValue();
					Abonnes abonnes2=new Abonnes(nom2,prenom2,birth2,sexe2,money2);
					int result=evolues.similarites_Age(abonnes1,abonnes2)+evolues.similarite_Salaire(abonnes1,abonnes2)+evolues.similarite_Sexe(abonnes1,abonnes2);
//					int result=evolues.similarite_Abonnes(abonnes1,abonnes2);

					if (group_abonne_similarite.containsKey(result)) // if contains key in map
					{
						group_abonne_similarite.get(result).add(id_abonne1);
						group_abonne_similarite.get(result).add(id_abonne2);

					}
					else {
						group_abonne_similarite.put(result,new ArrayList<>());
						group_abonne_similarite.get(result).add(id_abonne1);
						group_abonne_similarite.get(result).add(id_abonne2);
					}
//					System.out.println(entry1);
//					System.out.println(entry2);
//					System.out.println(result);
//					System.out.println("---------------");
				}
			}
		}

//		System.out.println(group_abonne_similarite);
		//removing duplicate values
		removeDuplicateValuesInMapList(group_abonne_similarite);
		System.out.println(group_abonne_similarite);
		return group_abonne_similarite;
	}

	public Map<Integer,List<List<String>>> get_film_titre_selon_abonne(Map<Integer,List<Integer>> group_abonne_similarite) throws SQLException {
		//group_film_silimarite   key:result de silimarite value: films
		Map<Integer,List<List<String>>> group_film_similarite=new HashMap<>();
		//find film according id of abonnes
		for(Map.Entry<Integer,List<Integer>> entry :group_abonne_similarite.entrySet())
		{
			group_film_similarite.put(entry.getKey(), new ArrayList<>());
			for(int value: entry.getValue())
			{
				List<String> titre=bd.findTitreFilmSelonAbonnes(value);
				group_film_similarite.get(entry.getKey()).add(titre);
			}
		}
		return group_film_similarite;
	}

	public Map<Integer,List<List<String>>> get_Abonnes_selon_id(Map<Integer,List<Integer>> list_abonne_id) throws SQLException {
		Map<Integer,List<List<String>>> abonnes =new HashMap<>();
		//entry: {0:[1,2,3],1:{2,4}}
		for(Map.Entry<Integer,List<Integer>> entry: list_abonne_id.entrySet())
		{
			for (int id: entry.getValue())
			{
				List<String> person=bd.findAbonneParId(id);
				if(abonnes.containsKey(entry.getKey()))
				{
					abonnes.get(entry.getKey()).add(person);
				}
				else {
					abonnes.put(entry.getKey(),new ArrayList<>());
					abonnes.get(entry.getKey()).add(person);
				}
			}
		}
		System.out.println(abonnes);
		return abonnes;
	}
	private static void removeDuplicateValuesInMapList(Map<Integer, List<Integer>> map) {
		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			List<Integer> originalList = entry.getValue();
			Set<Integer> uniqueValues = new LinkedHashSet<>(originalList);

			// 创建一个新的List，其中包含唯一的值
			List<Integer> uniqueList = new ArrayList<>(uniqueValues);

			// 替换原始List
			entry.setValue(uniqueList);
		}
	}

	public void addCoffretBD(Coffret coffret) throws SQLException {
		String titreC= coffret.getTitreC();
		String genreC=coffret.getGenre();
		boolean bonus= coffret.isBonus();
		if(bd.insert_coffret(titreC,genreC,bonus))
		{
			System.out.println("Coffret inséré avec succès dans la base de données");
		}
		else {
			System.out.println("Coffret n'a pas été inséré dans la base de données");
		}
	}

	public void addGenreBD(Genre genre)
	{
		String genreF= genre.getGenreNom();
		List<Genre> genreS=genre.getSubGenre(); //list
		if (genreS.isEmpty()) // if there is no sub genre
		{
			String query=


		}
	}



	public Map<Integer,List<String>> extraireCoffret()
	{

	}



}










//	public Genre extraireGenrePlusLoue() {
//
//
//
//
//
//
//
//        Map<Genre, Integer> genreFrequences = new HashMap<>();
//
//        // Calculer la fréquence de chaque genre de film loué
//        for (Film film : listeFilms) {
//            Genre genre = film.getGenre();
//            if (genreFrequences.containsKey(genre)) {
//                genreFrequences.put(genre, genreFrequences.get(genre) + 1);
//            } else {
//                genreFrequences.put(genre, 1);
//            }
//        }
//
//        // Trouver le genre de film le plus populaire (le plus loué)
//        Genre genrePlusLoue = null;
//        int maxFrequence = 0;
//        for (Map.Entry<Genre, Integer> entry : genreFrequences.entrySet()) {
//            if (entry.getValue() > maxFrequence) {
//                genrePlusLoue = entry.getKey();
//                maxFrequence = entry.getValue();
//            }
//        }
//
//        return genrePlusLoue;
//
//
//        */
//
//    	 //•Extraire de la liste des films les films les plus similaires à un film ayant le titre « ? ».
//
//    	 /**
//    	     * Extracts a list of films that are similar to the given title.
//    	     * @param film The title of the film to search for.
//    	     * @return A list of films with similar titles.
//    	     */
//
//    	 public List<Film> extraireListeFilmSimilaire(Film film) { //browse the list of movies and add similar movies to the list 'filmSimilaires'
//    		    List<Film> filmsSimilaires = new ArrayList<>();
//    		    for (Film f : filmlist) {
//    		        if (f.getTitreF().equals(film.getTitreF()) && isFilmSimilaire(f, film)) {
//    		            filmsSimilaires.add(f);
//    		        }
//    		    }
//    		    return filmsSimilaires;
//    		}
//    	 private boolean isFilmSimilaire(Film film1, Film film2) { // used to compare similarity criteria such as genre, actors and color between two films.
//    		    // Compare the genres of the films
//    		    if (!film1.getGenre().equals(film2.getGenre())) {
//    		        return false;
//    		    }
//
//    		    // Comparer the actors
//    		    List<Acteurs> acteurs1 = film1.getActeursList();
//    		    List<Acteurs> acteurs2 = film2.getActeursList();
//    		    boolean acteurSimilaire = false;
//    		    for (Acteurs a1 : acteurs1) {
//    		        for (Acteurs a2 : acteurs2) {
//    		            if (a1.equals(a2)) {
//    		                acteurSimilaire = true;
//    		                break;
//    		            }
//    		        }
//    		    }
//    		    if (!acteurSimilaire) {
//    		        return false;
//    		    }
//
//    		    // Comparer the colors
//    		    if (film1.isCouleurF() != film2.isCouleurF()) {
//    		        return false;
//    		    }
//
//    		    return true; // returns true If all similarity criteria are met
//    		}
//
//    	 //•	Extraire de la liste des abonnés, les abonnés « les plus curieux » : les films loués par ces abonnés se ressemblent le moins possible.
//
//    	 /**
//    	  * Extracts a list of subscribers who do not watch similar films.
//    	  * @return A list of subscribers who watch dissimilar films.
//    	  */
//
//
//    	 public List<Abonnes> extraireListeAbonnesSimilaireLoue() {
//    		    List<Abonnes> LoueNonSimilaires = new ArrayList<>(); // Initialize a list to store subscribers who watch dissimilar films
//    		    for (Abonnes abonne : listeAbonnes) { // Iterate through each subscriber in the list
//    		        boolean estCurieux = true; // Initialize if the subscriber is curious or not
//    		        List<Film> filmsLoues = abonne.getLocationFilm(); // Get the list of films rented by the subscriber
//    		        for (int i = 0; i < filmsLoues.size(); i++) { // Loop through each film in the rented films list
//    		            for (int j = i + 1; j < filmsLoues.size(); j++) { // Loop through the remaining films in the rented films list
//    		                if (isFilmSimilaire(filmsLoues.get(i), filmsLoues.get(j))) { // Check if the films are similar
//    		                    estCurieux = false; // If similar films are found, set the flag to false
//    		                    break; // Break out of the inner loop
//    		                }
//    		            }
//    		            if (!estCurieux) { // If the subscriber is found to not be curious, break out of the outer loop break;
//    		                break;
//    		            }
//    		        }
//    		        if (estCurieux) { // If the subscriber is curious, add them to the list of LoueNonSimilaires
//    		            LoueNonSimilaires.add(abonne);
//    		        }
//    		    }
//    		    return LoueNonSimilaires; // Return the list of subscribers who watch dissimilar films
//    		}
//
//    	 //--------------------------------------------------A VOIR --------------------------
//
//
////
////    	 //•	Extraire de la liste des films les films ayant un public « typé » : les abonnés louant ces films sont similaires (on fournira en paramètre le seuil de similarité entre abonnés).
////    	 /**
////    	     * Extracts films with a "typed" audience, where subscribers renting these films are similar.
////    	     * @param similariteSeuil The threshold of similarity between subscribers.
////    	     * @return A list of films with a "typed" audience.
////    	     */
////    	    public List<Film> extraireListeFilmsSimilaireAbonne(double similariteSeuil) {
////    	        List<Film> similaireAudienceFilms = new ArrayList<>();
////    	        for (Film film : filmlist) {
////    	            boolean similaireAudience = true; // Check if the film has a similar audience
////    	            List<Abonnes> rentingSubscribers = film.getRentingSubscribers();
////    	            if (rentingSubscribers.size() > 1) { // If there are more than one subscriber for the film
////    	                Abonnes firstSubscriber = rentingSubscribers.get(0); // Get the first subscriber to compare
////    	                for (Abonnes subscriber : rentingSubscribers) { // Compare each subscriber to the first subscriber
////    	                    if (!abonneSimilaire(firstSubscriber, subscriber, similariteSeuil)) { // If the subscribers are not similar, set similaireAudience to false and break the loop
////    	                        similaireAudience = false;
////    	                        break;
////    	                    }
////    	                }
////    	            }
////    	            if (similaireAudience) { // If the film has a similar audience, add it to the list
////    	                similaireAudienceFilms.add(film);
////    	            }
////    	        }
////    	        return similaireAudienceFilms;
////    	    }
////
////
//
//
////
////		// Calculate the age of the subscriber based on the date of birth
////    	   /** private int calculateAge(String dateNaissanceAb) {
////    	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");// Define the date formatter for the subscriber's date of birth
////    	        LocalDate birthDate = LocalDate.parse(dateNaissanceAb, formatter);// Parse the subscriber's date of birth into a LocalDate object
////    	        LocalDate currentDate = LocalDate.now(); // Get the current date
////    	        return Period.between(birthDate, currentDate).getYears();// Calculate the age based on the difference between the current date and the date of birth
////
////    	    }*/
////
////
////    	    /**
////    	     * Extracts films with a "typed" audience based on subscriber similarity.
////    	     * @param similarityThreshold The threshold of similarity between subscribers.
////    	     * @return A list of films with a "typed" audience.
////    	     */
////    	    public List<Film> extractTypedAudienceFilms(double similarityThreshold) {
////    	        List<Film> typedAudienceFilms = new ArrayList<>();
////
////    	        // Iterate through each film
////    	        for (Film film : filmlist) {
////    	            // Calculate the total number of subscribers for the film
////    	            int totalSubscribers = film.getRentingSubscribers().size();
////
////    	            // Check if the film has more than one subscriber
////    	            if (totalSubscribers > 1) {
////    	                // Calculate the similarity percentage for each subscriber
////    	                Map<Abonnes, Double> similarityMap = new HashMap<>();
////    	                for (Abonnes subscriber : film.getRentingSubscribers()) {
////    	                    double similarity = abonneSimilaire(subscriber, film, subscriber);
////    	                    similarityMap.put(subscriber, similarity);
////    	                }
////
////    	                // Check if there are subscribers with similarity percentage greater than or equal to 70%
////    	                for (Map.Entry<Abonnes, Double> entry : similarityMap.entrySet()) {
////    	                    if (entry.getValue() >= similarityThreshold) {
////    	                        typedAudienceFilms.add(film);
////    	                        break;
////    	                    }
////    	                }
////    	            }
////    	        }
////
////    	        return typedAudienceFilms;
////    	    }
//
//
//
//
//
//
//
//
////
////
////    	    /**
////    	     * Calculate the similarity percentage between a subscriber and a film.
////    	     * @param subscriber The subscriber.
////    	     * @param film The film.
////    	     * @return The similarity percentage.
////    	     */
////    	    private double abonneSimilaire(Abonnes subscriber, Film film,Abonnes subscriber2 ) {
////    	        double similarityPercentage = 0.0;
////
////    	        // Check if they have the same gender
////    	        if (subscriber.getSexeAb().equals(film.getGenre().getGenreNom())) {
////    	            similarityPercentage += 40.0;
////    	        }
////
////    	        // Check if they are in the same age bracket (not more than 10 years apart)
////    	        int ageDifference = Math.abs(calculateAge(subscriber.getDateNaissanceAb()) - calculateAge(subscriber2.getDateNaissanceAb()));
////    	        if (ageDifference <= 10) {
////    	            similarityPercentage += 30.0;
////    	        } else if (ageDifference <= 20) {
////    	            similarityPercentage += 20.0;
////    	        } else if (ageDifference <= 30) {
////    	            similarityPercentage += 10.0;
////    	        }
////
////    	        // Check if they are in the same income bracket
////    	        if (subscriber.getFourchetteRevenus().equals(film.getGenre().getGenreNom())) {
////    	            similarityPercentage += 30.0;
////    	        }
////
////    	        return similarityPercentage;
////    	    }
////    	 // Calculate the age of the subscriber based on the date of birth
////    	    private int calculateAge(String dateNaissanceAb) {
////    	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");// Define the date formatter for the subscriber's date of birth
////    	        LocalDate birthDate = LocalDate.parse(dateNaissanceAb, formatter);// Parse the subscriber's date of birth into a LocalDate object
////    	        LocalDate currentDate = LocalDate.now(); // Get the current date
////    	        return Period.between(birthDate, currentDate).getYears();// Calculate the age based on the difference between the current date and the date of birth
////
////    	    }
////
////
////
//
//    	    //---------------------------------------------------------------------
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    	    //•	Extraire de la liste des abonnés, les abonnés les plus proches d'un profil type.
//
//    	    /**
//    	     * Extracts subscribers who are closest to a typical profile based on the genre and sub-genre of films they rent.
//    	     * @param targetGenre The target genre for comparison.
//    	     * @return A list of subscribers closest to the typical profile.
//    	     */
//    	    public List<Abonnes> extraireListeAbonneProcheProfil(Genre targetGenre) {
//    	        List<Abonnes> abonnesProchesProfil = new ArrayList<>();
//    	        for (Abonnes abonne : listeAbonnes) {
//    	            List<Film> filmsLoues = abonne.getLocationFilm();
//    	            for (Film film : filmsLoues) {
//    	                // Check if the genre of the film is the same as the target genre
//    	                if (film.getGenre().equals(targetGenre)) {
//    	                    abonnesProchesProfil.add(abonne);
//    	                    break;
//    	                }
//    	                // Check if the genre of the film is an instance of a sub-genre
//    	                if (film.getGenre().getSubGenre() != null && !film.getGenre().getSubGenre().isEmpty()) {
//    	                    for (Genre subGenre : film.getGenre().getSubGenre()) {
//    	                        // Check if the sub-genre of the film is the same as the target genre
//    	                        if (subGenre.getGenreNom().equals(targetGenre.getGenreNom())) {
//    	                            abonnesProchesProfil.add(abonne);
//    	                            break;
//    	                        }
//    	                    }
//    	                }
//    	            }
//    	        }
//    	        return abonnesProchesProfil;
//    	    }
//
//
//
//
//

 

