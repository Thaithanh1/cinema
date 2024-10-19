package movie.service.interfaceservice;

import movie.payload.dto.MovieDTO;
import movie.payload.request.MovieRequest;
import movie.payload.response.ResponseObject;

import java.util.List;
import java.util.Map;

public interface iMovieService {
    public ResponseObject<MovieDTO> addMovie(MovieRequest request);
    public ResponseObject<MovieDTO> editMovie(int movieID, MovieRequest request);
    public ResponseObject<MovieDTO> deleteMovie(int movieID);
    public List<Map<String, Object>> getPopularMovies();
    public List<MovieDTO> getMoviesByCinemaRoomAndSeatStatus(String cinemaName, String roomName, String seatStatus);
}
