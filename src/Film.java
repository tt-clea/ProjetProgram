import java.util.*;

public class Film {
	private String titreF;
	private boolean couleurF;
	private int NbStockage = 10;
	private List<Acteurs> acteurs;
	private Map<Abonnes,DateLocation> location;
	private Genre genre;
	private Map<String,Integer> historique;



	/**
	 * @param titreF
	 * @param couleurF
	 * @param genre
	 */
	public Film(String titreF, boolean couleurF, Genre genre) {
		this.titreF = titreF;
		this.couleurF = couleurF;
		this.acteurs = new ArrayList<>();
		location = new HashMap<>(NbStockage);
		this.genre = genre;
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
//		location.remove(abonnes);
		System.out.println("ok"+titreF);
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
		return acteurs;
	}
	/**
	 * @return the nbStockage
	 */
	public int getNbStockage() {
		return NbStockage;
	}

	/**
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * @set the titre de film
	 */
	public void setTitreF(String titreF) {
		this.titreF = titreF;
	}

	/**
	 * @param nbStockage the nbStockage to set
	 */
	public void setNbStockage(int nbStockage) {
		NbStockage = nbStockage;
	}

	/**
	 *
	 * @param aActeurs ajouter les acteurs de cette film
	 */

	public void addActeurs(Acteurs aActeurs)
	{
		acteurs.add(aActeurs);
	}

	/**
	 *
	 * @param aActeurs supprimer les acteurs de cette film
	 */
	public void removeActeurs(Acteurs aActeurs)
	{
		acteurs.remove(aActeurs);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Film film = (Film) o;
		return couleurF == film.couleurF && NbStockage == film.NbStockage && Objects.equals(titreF, film.titreF) && Objects.equals(acteurs, film.acteurs) && Objects.equals(location, film.location) && Objects.equals(genre, film.genre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(titreF, couleurF, NbStockage, acteurs, location, genre);
	}

	@Override
	public String toString() {
		return "Film{" +
				"titreF='" + titreF + '\'' +
				", couleurF=" + couleurF +
				", NbStockage=" + NbStockage +
				", acteurs=" + acteurs +
				", location=" + location +
				", genre=" + genre +
				'}';
	}
}
