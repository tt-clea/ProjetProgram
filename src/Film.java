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
	
	
	
	/**
	 * @param titreF
	 * @param couleurF
	 */
	public Film(String titreF, boolean couleurF) {
		this.titreF = titreF;
		this.couleurF = couleurF;
//		this(titreF,couleurF,new ArrayList<>());
	}
	
	/**
	 * @param titreF
	 * @param acteurs
	 */
	public Film(String titreF, List<Acteurs> acteurs) {
		this.titreF = titreF;
		this.acteurs = acteurs;
		
	}

	/**
	 * @add the location
	 */
	public void addLocation(final Abonnes abonnes,final DateLocation datelocation)
	{
		if(NbStockage>0)
		{
			NbStockage--;
			location.put(abonnes, datelocation);
			System.out.println("ok"+titreF);
		}
		else
		{
			System.out.println("non stockage");
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
	 * @return the acteurs
	 */
	public List<Acteurs> getActeurs() 
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

	public void addActeurs(Acteurs aActeurs)
	{
		acteurs.add(aActeurs);
	}
	public void removeActeurs(Acteurs aActeurs)
	{
		acteurs.remove(aActeurs);
	}
	
	


}
