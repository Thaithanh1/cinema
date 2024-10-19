package movie.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "movietype")
public class MovieType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int movieTypeID;

    @Column(name = "movietypename")
    private String movieTypeName;

    @Column(name = "isactive", columnDefinition = "bit")
    private boolean isActive;

    @OneToMany(mappedBy = "movieType")
    @JsonManagedReference
    private List<Movie> movies;

    public int getMovieTypeID() {
        return movieTypeID;
    }

    public void setMovieTypeID(int movieTypeID) {
        this.movieTypeID = movieTypeID;
    }

    public String getMovieTypeName() {
        return movieTypeName;
    }

    public void setMovieTypeName(String movieTypeName) {
        this.movieTypeName = movieTypeName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
