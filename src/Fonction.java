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
@SuppressWarnings("unused")
public class Fonction {

	private BdConnector bd;
	private Evolues evolues;
	private Abonnes abonnes;
	private Film films;


	public Fonction(BdConnector bd) {
		this.bd=bd;
		this.abonnes=abonnes;
		this.evolues=new Evolues(bd);
		this.films = new Film();
	}



	///////////////////////////// ajouter les donnes dans BD/////////////////////////////


	/**
	 * Method to add a subscriber to the list, avoiding duplicates.
	 * @param aAbonne
	 * @throws SQLException
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

	/**
	 * Method to add coffret into BD
	 * @param coffret
	 * @throws SQLException
	 */
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

	/**
	 * method to add movies into BD
	 * @param aFilm
	 * @throws SQLException
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

	/**
	 * method to add genre into BD
	 * @param genre
	 * @throws SQLException
	 */
	public void addGenreBD(Genre genre) throws SQLException {
		String genreF;
		String genreS;
		for(Genre g:genre.getSubGenre())
		{
			System.out.println(g);
//			System.out.println(g.getGenreNom()); //action
//			System.out.println(g.getSubGenre()); //[aventure:[],western:[]]
			if (!g.getSubGenre().isEmpty()) // not null
			{
				genreF=g.getGenreNom();//action
				for (Genre sub:g.getSubGenre())
				{
					//System.out.println(sub.getGenreNom()); //aventure , western
					genreS= sub.getGenreNom();
					Boolean isInsertGenre=bd.insert_genre(genreF,genreS);
					if (isInsertGenre)
					{
						System.out.println("genre est:"+genreF+" subgenre est:"+genreS+" ajouté avec succès");
					}
					else {
						System.out.println("genre est:"+genreF+" subgenre est:"+genreS+" ajouté pas");
					}
				}
			}
			else {
				genreF=g.getGenreNom();
				genreS=null;
				Boolean isInsertGenre=bd.insert_genre(genreF,genreS);
				if (isInsertGenre)
				{
					System.out.println("genre est:"+genreF+" subgenre est:"+genreS+" ajouté avec succès");
				}
				else {
					System.out.println("genre est:"+genreF+" subgenre est:"+genreS+" ajouté pas");
				}
			}
			System.out.println("-------------------------");
		}

	}

	/**
	 * method to add actor into BD
	 * @param acteurs
	 * @throws SQLException
	 */
	public void addActorBD(Acteurs acteurs) throws SQLException {
		String prenomA= acteurs.getPrenomA();
		String nomA=acteurs.getNomA();
		Boolean isInsertActor=bd.insert_actor(nomA,prenomA);
		if(isInsertActor)
		{
			System.out.println("Acteur :"+prenomA +" "+ nomA+" ajouté avec succès");
		}
		else {
			System.out.println("Acteur :"+prenomA +" "+ nomA+" ajouté pas");
		}
	}

	/**
	 * method to add coffret and film into BD
	 * @param coffret
	 * @param film
	 * @throws SQLException
	 */
	public void addFilmCoffret(Coffret coffret,Film film) throws SQLException {
		String titreF= film.getTitreF();
		String titreC= coffret.getTitreC();
		//get id of film
		int id_film=bd.get_id_film(titreF);
		//get id of coffret
		int id_coffret= bd.get_id_coffret(titreC);
		Boolean isInsert=bd.insert_Avoir_Film_Coffret(id_coffret,id_film);
		if (isInsert)
		{
			System.out.println("ID :"+id_film+" de film ajouté dans le id "+id_coffret+ "de coffret");
		}
		else
		{
			System.out.println("Film pas ajouté dans le coffret");
		}
	}

	/**
	 * method to add film and actor into BD
	 * @param film
	 * @param acteurs
	 * @throws SQLException
	 */
	public void addFilmActor(Film film,Acteurs acteurs) throws SQLException {
		String titreF= film.getTitreF();
		String nomA= acteurs.getNomA();
		String prenomA= acteurs.getPrenomA();
		//get id of film
		int id_film= bd.get_id_film(titreF);
		//get id of actor
		int id_actor=bd.get_id_actor(nomA,prenomA);
		Boolean isInsert=bd.insert_avoir_film_actor(id_film,id_actor);
		if (isInsert)
		{
			System.out.println("acteur id :"+id_actor +" ajouté avec success dans film id "+id_film);
		}
		else {
			System.out.println("ajouter pas");
		}
	}





	//////////////////////////////////////fonction////////////////////////////////

	/**
	 * find subscriber from BD
	 * @param aNom
	 * @param aPrenom
	 * @throws SQLException
	 */
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
	 * @return
	 * @throws SQLException
	 */
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



	/**
	 * Extraire le genre de films le plus populaire (le plus loué)
	 * @return
	 * @throws SQLException
	 */
	public Entry<String,Integer> PlusLoueGenre() throws SQLException {
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

		return max;
//		System.out.println("Le genre de films le plus populaire est :"+max.getKey() +"\n"+"Nombre de location est "+max.getValue());
	}


	/**
	 * Extraire de la liste des films les films les plus similaires à un film ayant le titre « ? »
	 * @param keyword
	 * @return
	 * @throws SQLException
	 */
	public List<List<Object>> KeyWordFilm(String keyword) throws SQLException {
		List<List<Object>> list_of_film=bd.findFilmByKeyWord(keyword);
//		System.out.println(list_of_film);
		return list_of_film;
	}


	/**
	 * Extraire de la liste des abonnés, les abonnés « les plus curieux » : les films loués par ces abonnés se ressemblent le moins possible.
	 * @param tree
	 * @return
	 * @throws SQLException
	 */
	public List<List<String>> findSimilaireFilm(Genre tree) throws SQLException {
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
					int similarite_film=evolues.similarite_Film(bd,tree,f1,f2);
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
		return abonne_list;
	}




	/**
	 * Extraire de la liste des films les films ayant un public « typé » : les abonnés louant ces films sont similaires (on fournira en paramètre le seuil de similarité entre abonnés).
	 * @return
	 * @throws SQLException
	 */
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
//		System.out.println(group_abonne_similarite);
		return group_abonne_similarite;
	}

	/**
	 * return id of film according abonnes
	 * @param group_abonne_similarite
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * return abonnes according id
	 * @param list_abonne_id
	 * @return
	 * @throws SQLException
	 */
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
		return abonnes;
	}

	/**
	 * removeing duplicate value in map list
	 * @param map
	 */
	private static void removeDuplicateValuesInMapList(Map<Integer, List<Integer>> map) {
		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			List<Integer> originalList = entry.getValue();
			Set<Integer> uniqueValues = new LinkedHashSet<>(originalList);

			// create new list ,and contains unique value
			List<Integer> uniqueList = new ArrayList<>(uniqueValues);

			// instead of original List
			entry.setValue(uniqueList);
		}
	}


	/**
	 * extraire de la liste des coffret, pour chaque coffret, le ou les films le plus similaires
	 * @param genre
	 * @return
	 * @throws SQLException
	 */
	public Map<String,Map<String,List<String>>> extraireCoffret(Genre genre) throws SQLException {
		// find all coffret and films
		Map<String,List<String>> coffret_films=bd.FilmsParCoffret();
		System.out.println("Liste de films pour chaque coffret :"+coffret_films);
		// Coffret1 : {Film1: [film2.....], Film2:[film1....], Film3:[Film1.....]} Map<String,Map<String,List<String>> for stocking movies in same coffret.
		Map<String,Map<String,List<String>>> lesFilmParCoffret_isSimialrite=new HashMap<>();
		//calcule les simialrite dans cette  coffret
		for(Map.Entry<String,List<String>> entry:coffret_films.entrySet())
		{
			Map<String,List<String>> films_isSimilarite=new HashMap<>();
			int tag_similarite=3;
			for (String titreF1: entry.getValue())
			{
				for (String titreF2:entry.getValue())
				{
					if (!titreF1.equals(titreF2))
					{
						//find information of film and build Object film for Evolues.
						List<Object> film1=bd.findFilm2(titreF1);
						List<Object> film2=bd.findFilm2(titreF2);
						Boolean couleur1=true;
						if (film1.get(1).equals("0"))
						{
							couleur1=false;
						}
						Boolean couleur2=true;
						if (film1.get(1).equals("0"))
						{
							couleur2=false;
						}
						Film f1=new Film((String) film1.get(0), couleur1,(String)film1.get(3),(int)film1.get(2));
						Film f2=new Film((String) film2.get(0), couleur2,(String)film2.get(3),(int)film2.get(2));
						int res_film=evolues.similarite_Film(bd,genre,f1,f2);
//						System.out.println(res_film);
						if(films_isSimilarite.containsKey(titreF1))
						{
							if(res_film<=tag_similarite)
							{
								films_isSimilarite.get(titreF1).clear();
								tag_similarite=res_film;
								films_isSimilarite.get(titreF1).add(titreF2);
							}
							else {
								films_isSimilarite.get(titreF1).add(titreF2);
							}
						}
						else {
							films_isSimilarite.put(titreF1,new ArrayList<>());
							if(res_film<=tag_similarite)
							{
								films_isSimilarite.get(titreF1).clear();
								tag_similarite=res_film;
								films_isSimilarite.get(titreF1).add(titreF2);
							}
							else {
								films_isSimilarite.get(titreF1).add(titreF2);
							}
						}
					}
				}
//				System.out.println("----------");
			}
			lesFilmParCoffret_isSimialrite.put(entry.getKey(), films_isSimilarite);
		}
//		System.out.println(lesFilmParCoffret_isSimialrite);
		return lesFilmParCoffret_isSimialrite;
	}

}