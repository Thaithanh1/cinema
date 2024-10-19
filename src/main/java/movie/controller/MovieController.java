package movie.controller;

import movie.payload.dto.MovieDTO;
import movie.payload.request.MovieRequest;
import movie.payload.response.ResponseObject;
import movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "addmovie", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<MovieDTO> addMovie(@RequestBody MovieRequest request) {
        return movieService.addMovie(request);
    }

    @RequestMapping(value = "editmoive", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<MovieDTO> editMovie(@RequestParam int movieID, @RequestBody MovieRequest request) {
        return movieService.editMovie(movieID, request);
    }

    @RequestMapping(value = "deletemovie", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<MovieDTO> deleteMovie(@RequestParam int movieID) {
        return movieService.deleteMovie(movieID);
    }

    @GetMapping("popular")
    public ResponseEntity<List<Map<String, Object>>> getPopularMovies() {
        try {
            // Gọi service để lấy danh sách các bộ phim nổi bật
            List<Map<String, Object>> popularMovies = movieService.getPopularMovies();
            return ResponseEntity.ok(popularMovies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> searchMovies(
            @RequestParam String cinemaName,
            @RequestParam String roomName,
            @RequestParam String seatStatus) {

        List<MovieDTO> movieDetails = movieService.getMoviesByCinemaRoomAndSeatStatus(cinemaName, roomName, seatStatus);
        return new ResponseEntity<>(movieDetails, HttpStatus.OK);
    }
}