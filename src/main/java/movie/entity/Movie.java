package movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int movieID;

    @Column(name = "movieduration")
    private int movieDuration;

    @Column(name = "endtime")
    private Date endTime;

    @Column(name = "premieredate")
    private Date premiereDate;

    @Column(name = "description")
    private String description;

    @Column(name = "director")
    private String director;

    @Column(name = "image")
    private String image;

    @Column(name = "heroimage")
    private String heroImage;

    @Column(name = "language")
    private String language;

    @Column(name = "name")
    private String name;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "isactive", columnDefinition = "bit")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "movietypeid", insertable = false, updatable = false)
    @JsonBackReference
    private MovieType movieType;
    @Column(name = "movietypeid")
    private int movieTypeID;

    @ManyToOne
    @JoinColumn(name = "rateid", insertable = false, updatable = false)
    @JsonBackReference
    private Rate rate;
    @Column(name = "rateid")
    private int rateID;

    @OneToMany(mappedBy = "movie")
    @JsonManagedReference
    private List<Schedule> schedules;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(Date premiereDate) {
        this.premiereDate = premiereDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(String heroImage) {
        this.heroImage = heroImage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String languge) {
        this.language = languge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public int getMovieTypeID() {
        return movieTypeID;
    }

    public void setMovieTypeID(int movieTypeID) {
        this.movieTypeID = movieTypeID;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public int getRateID() {
        return rateID;
    }

    public void setRateID(int rateID) {
        this.rateID = rateID;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
