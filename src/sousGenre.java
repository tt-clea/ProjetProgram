public class sousGenre extends Genre{

	/**
	 * @param genreNom
	 */
	public sousGenre(String genreNom) {
		super(genreNom);
		// TODO Auto-generated constructor stub
	}
	
	public String getSousGenre()
	{
		return getGenreNom();
	}

}
