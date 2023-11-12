import java.util.Objects;

public class Genre {
	private final String genreNom;

	/**
	 * @param genreNom
	 */
	public Genre(String genreNom) {
		this.genreNom = genreNom;
	}

	/**
	 * @return the genreNom
	 */
	public String getGenreNom() {
		return genreNom;
	}

	@Override
	public int hashCode() {
		return Objects.hash(genreNom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		return Objects.equals(genreNom, other.genreNom);
	}

	@Override
	public String toString() {
		return "Genre [genreNom=" + genreNom + "]";
	}
	
	
}
