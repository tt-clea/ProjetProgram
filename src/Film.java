import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Film {
	private String titreF;
	private boolean couleurF;
	private int NbStockage = 10;
	private List<Acteurs> acteurs;
	private Map<Abonnes,DateLocation> location;

	private Genre genre;



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
		this.setGenre(genre);
	}

	
	/**
	 * @param location
	**/
	public Film()
	{
		this.location=new HashMap<>(NbStockage);
	}
	
	/**
	 * @param titreF
	 * @param couleurF
	 * @param acteurs
	 * @param location
	 */
	public Film(String titreF, boolean couleurF, List<Acteurs> acteurs) {
		this.titreF = titreF;
		this.couleurF = couleurF;
		this.acteurs = acteurs;
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
	 * @param nbStockage the nbStockage to set
	 */
	public void setNbStockage(int nbStockage) {
		NbStockage = nbStockage;
	}

	/**
	 *
	 * @param aActeurs ajouter les acteurs de ce film
	 */

	public void addActeurs(Acteurs aActeurs)
	{
		acteurs.add(aActeurs);
	}

	/**
	 *
	 * @param aActeurs supprimer les acteurs de ce film
	 */
	public void removeActeurs(Acteurs aActeurs)
	{
		acteurs.remove(aActeurs);
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




	
	


}