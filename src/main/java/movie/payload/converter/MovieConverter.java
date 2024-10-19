package movie.payload.converter;

import movie.entity.Movie;
import movie.payload.dto.MovieDTO;
import movie.payload.request.MovieRequest;

public class MovieConverter {
    public MovieDTO entityToMovieDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setMovieDuration(movie.getMovieDuration());
        dto.setEndTime(movie.getEndTime());
        dto.setPremiereDate(movie.getPremiereDate());
        dto.setDescription(movie.getDescription());
        dto.setDirector(movie.getDirector());
        dto.setImage(movie.getImage());
        dto.setHeroImage(movie.getHeroImage());
        dto.setLanguage(movie.getLanguage());
        dto.setName(movie.getName());
        dto.setTrailer(movie.getTrailer());
        dto.setIsActive(movie.isActive());
        dto.setMovieTypeID(movie.getMovieTypeID());
        dto.setRateID(movie.getRateID());
        return dto;
    }

    public Movie addMovie(MovieRequest request) {
        Movie movie = new Movie();
        movie.setMovieDuration(request.getMovieDuration());
        movie.setEndTime(request.getEndTime());
        movie.setPremiereDate(request.getPremiereDate());
        movie.setDescription(request.getDescription());
        movie.setDirector(request.getDirector());
        movie.setImage(request.getImage());
        movie.setHeroImage(request.getHeroImage());
        movie.setLanguage(request.getLanguage());
        movie.setName(request.getName());
        movie.setTrailer(request.getTrailer());
        movie.setIsActive(request.isActive());
        movie.setMovieTypeID(request.getMovieTypeID());
        movie.setRateID(request.getRateID());
        return movie;
    }

    public Movie editMovie(Movie movie, MovieRequest request) {
        movie.setMovieDuration(request.getMovieDuration());
        movie.setEndTime(request.getEndTime());
        movie.setPremiereDate(request.getPremiereDate());
        movie.setDescription(request.getDescription());
        movie.setDirector(request.getDirector());
        movie.setImage(request.getImage());
        movie.setHeroImage(request.getHeroImage());
        movie.setLanguage(request.getLanguage());
        movie.setName(request.getName());
        movie.setTrailer(request.getTrailer());
        movie.setIsActive(request.isActive());
        movie.setMovieTypeID(request.getMovieTypeID());
        movie.setRateID(request.getRateID());
        return movie;
    }
}
