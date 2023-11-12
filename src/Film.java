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

	public Genre getGenre() {
		return genre;
	}


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
	 * @param aActeurs ajouter les acteurs de cette film
	 */

	public void addActeurs(Acteurs aActeurs)
	{
		acteursList.add(aActeurs);
	}

	/**
	 *
	 * @param aActeurs supprimer les acteurs de cette film
	 */
	public void removeActeurs(Acteurs aActeurs)
	{
		acteursList.remove(aActeurs);
	}


	@Override
	public String toString() {
		return "Film{" +
				"titreF='" + titreF + '\'' +
				", couleurF=" + couleurF +
				", NbStockage=" + NbStockage +
				", acteursList=" + acteursList +
				", location=" + location +
				", genre=" + genre +
				", acteurs=" + acteurs +
				", historique=" + historique +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Film film = (Film) o;
		return couleurF == film.couleurF && NbStockage == film.NbStockage && Objects.equals(titreF, film.titreF) && Objects.equals(acteursList, film.acteursList) && Objects.equals(location, film.location) && Objects.equals(genre, film.genre) && Objects.equals(acteurs, film.acteurs) && Objects.equals(historique, film.historique);
	}

	@Override
	public int hashCode() {
		return Objects.hash(titreF, couleurF, NbStockage, acteursList, location, genre, acteurs, historique);
	}
}