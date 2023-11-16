import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.util.HashMap;
public class Genre {
	private String genreNom;
	private List<Genre> subGenre;
	public Genre(String genreNom) {
		this.genreNom=genreNom;
		this.subGenre=new ArrayList<>();
	}
	public String getGenreNom() {
		return genreNom;
	}
	public List<Genre> getSubGenre() {
		return subGenre;
	}
	public void addSubGenre(Genre sub)
	{
		subGenre.add(sub);
	}
	public void setGenreNom(String genreNom) {
		this.genreNom = genreNom;
	}
	public void setSubGenre(List<Genre> subGenre) {
		this.subGenre = subGenre;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Genre genre = (Genre) o;
		return Objects.equals(genreNom, genre.genreNom) && Objects.equals(subGenre, genre.subGenre);
	}
	@Override
	public int hashCode() {
		return Objects.hash(genreNom, subGenre);
	}
	@Override
	public String toString() {
		return genreNom+":"+subGenre;
	}
}
