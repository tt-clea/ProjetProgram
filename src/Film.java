import java.util.*;

public class Film {
	private String titreF;
	private boolean couleurF;
	private int NbStockage = 10;
	private List<Acteurs> acteursList;
	private Map<Abonnes,DateLocation> location;

	private Genre genre;

	private Acteurs acteurs;
	private Map<String,List<List<String>>> historique;


	/**
	 *
	 **/
	public Film()
	{
		this.location=new HashMap<>(NbStockage);
		this.historique=new HashMap<>();
	}


	/**
	 * @param titreF
	 * @param couleurF
	 * @param genre
	 */
	public Film(String titreF, boolean couleurF, Genre genre) {
		this.titreF = titreF;
		this.couleurF = couleurF;
		this.acteursList = new ArrayList<>();
		location = new HashMap<>(NbStockage);

		this.genre = genre;
		historique=new HashMap<>();

		this.setGenre(genre);

	}

	public Film(String titreF, boolean couleurF,Genre genre, Acteurs acteurs) {
		this.titreF = titreF;
		this.couleurF = couleurF;
		this.acteursList = new ArrayList<>();
		location = new HashMap<>(NbStockage);
		this.genre = genre;
		this.acteurs = acteurs;
		historique=new HashMap<>();
	}
	

//	/**
//	 * @param titreF
//	 * @param couleurF
//	 */
//	public Film(String titreF, boolean couleurF) {
//		this.titreF = titreF;
//		this.couleurF = couleurF;
////		this(titreF,couleurF,new ArrayList<>());
//	}
//
//	/**
//	 * @param titreF
//	 * @param acteurs
//	 */
//	public Film(String titreF) {
//		this.titreF = titreF;
//		this.acteurs = new ArrayList<>();
//	}

	/**
	 * @add the location
	 */
	public boolean addLocation(final Abonnes abonnes,final DateLocation datelocation)
	{
		if(NbStockage>0)
		{
			NbStockage--;
			location.put(abonnes, datelocation);
			historiqueLocation(abonnes,datelocation);
			System.out.println("ok"+titreF);
			return true;
		}
		else
		{
			System.out.println("non stockage");
			return false;
		}
		
	}
	/**
	 * @remove the location
	 */
	public void removeLocation(final Abonnes abonnes,final DateLocation datelocation)
	{
		NbStockage++;
		location.remove(abonnes);
		System.out.println("ok"+titreF);
	}

	public void historiqueLocation(Abonnes ab,DateLocation dateLocation) {
		//Map<Abonnes,List<List<String>>> historique;
		//List<String>= []  String film,date
		// tiantian ,  [[film ,2020-2-9],[film2 , 2023-03-12]]
		List<List<String>> listofList = new ArrayList<List<String>>();

		List<String> innerList=new ArrayList<>();
		innerList.add(getTitreF()); //film
		innerList.add(dateLocation.getDateDebut()); //2020-2-9
		listofList.add(innerList);//[[film,2020-2-9]]
		historique.put(ab.getNomAb(),listofList);

	}

	/**
	 * @return the titreF
	 */
	public String getTitreF() {
		return titreF;
	}
	/**
	 * @return the couleurF
	 */
	public boolean isCouleurF() {
		return couleurF;
	}
	/**
	 * @return the list of acteurs
	 */
	public List<Acteurs> getActeursList()
	{
		return acteursList;
	}
	/**
	 * @return the nbStockage
	 */
	public int getNbStockage() {
		return NbStockage;
	}

	/**
	 * @param nbStockage the nbStockage to set
	 */
	public void setNbStockage(int nbStockage) {
		NbStockage = nbStockage;
	}

	public Acteurs getActeurs() {
		return acteurs;
	}

	/**
	 *
	 * @param aActeurs ajouter les acteurs de ce film
	 */

	public void addActeurs(Acteurs aActeurs)
	{
		acteursList.add(aActeurs);
	}

	/**
	 *
	 * @param aActeurs supprimer les acteurs de ce film
	 */
	public void removeActeurs(Acteurs aActeurs)
	{
		acteursList.remove(aActeurs);
	}


	public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	private List<Abonnes> listeAbonnes;

    // Autres attributs et méthodes de la classe Film

    /**
     * Récupère la liste des abonnés qui louent le film.
     * @return La liste des abonnés qui louent le film.
     */
    public List<Abonnes> getRentingSubscribers() {
        return listeAbonnes;
    }


	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}