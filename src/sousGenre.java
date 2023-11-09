public class SousGenre extends Genre{


	/**
	 * Constructor for the subclass 'sousGenre' that initializes the genre name.
	 * @param genreNom The name of the genre.
	 */
	public SousGenre(String genreNom) {
		super(genreNom);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Returns the name of the sub-genre.
	 * @return The name of the sub-genre.
	 */
	public String getSousGenre()
	{
		return getGenreNom();
	}

}
