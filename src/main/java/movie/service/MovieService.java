package movie.service;

import movie.entity.Movie;
import movie.payload.converter.MovieConverter;
import movie.payload.dto.MovieDTO;
import movie.payload.request.MovieRequest;
import movie.payload.response.ResponseObject;
import movie.repository.MovieRepository;
import movie.service.interfaceservice.iMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class MovieService implements iMovieService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MovieRepository movieRepository;
    private MovieConverter _converter;
    private ResponseObject<MovieDTO> _movieResponseOject;

    public MovieService() {
        _converter = new MovieConverter();
        _movieResponseOject = new ResponseObject<MovieDTO>();
    }

    public ResponseObject<MovieDTO> addMovie(MovieRequest request) {
        Movie mv = _converter.addMovie(request);
        movieRepository.save(mv);
        return _movieResponseOject.responseSuccess("Them moi Movie thanh cong", _converter.entityToMovieDTO(mv));
    }

    public ResponseObject<MovieDTO> editMovie(int movieID, MovieRequest request) {
        var checkMovie = movieRepository.findById(movieID);
        if (checkMovie.isEmpty()) {
            return _movieResponseOject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Movie khong ton tai", null);
        } else {
            Movie mv = _converter.editMovie(checkMovie.get(), request);
            movieRepository.save(mv);
            return _movieResponseOject.responseSuccess("Cap nhat Movie thanh cong", _converter.entityToMovieDTO(mv));
        }
    }

    public ResponseObject<MovieDTO> deleteMovie(int movieID) {
        var checkMovie = movieRepository.findById(movieID);
        if (checkMovie.isEmpty()) {
            return _movieResponseOject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Movie khong ton tai", null);
        } else {
            movieRepository.delete(checkMovie.get());
            return _movieResponseOject.responseSuccess("Xoa movie thanh cong", null);
        }
    }

    public List<Map<String, Object>> getPopularMovies() {
        // Tính tổng số lượng vé đã đặt cho mỗi bộ phim và sắp xếp theo số lượng vé giảm dần
        String sql = "SELECT m.Name AS MovieName, COUNT(t.Id) AS NumberOfTickets " +
                "FROM Movie m " +
                "JOIN Schedule s ON m.Id = s.MovieId " +
                "JOIN Ticket t ON s.Id = t.ScheduleId " +
                "GROUP BY m.Id " +
                "ORDER BY NumberOfTickets DESC";

        // Thực hiện truy vấn và trả về kết quả
        return jdbcTemplate.queryForList(sql);
    }

    // Hển thị phim theo rạp, phòng và trạng thái ghế trong phòng
    public List<MovieDTO> getMoviesByCinemaRoomAndSeatStatus(String cinemaName, String roomName, String seatStatus) {
        List<Movie> movies = movieRepository.findMoviesByCinemaAndRoomAndSeatStatus(cinemaName, roomName, seatStatus);
        List<MovieDTO> movieDetailsDTOList = new ArrayList<>();

        for (Movie movie : movies) {
            MovieDTO dto = new MovieDTO();
            dto.setMovieDuration(movie.getMovieDuration());
            dto.setEndTime(movie.getEndTime());
            dto.setPremiereDate(movie.getPremiereDate());
            dto.setDescription(movie.getDescription());
            dto.setDirector(movie.getDirector());
            dto.setImage(movie.getImage());
            dto.setHeroImage(movie.getHeroImage());
            dto.setLanguage(movie.getLanguage());
            dto.setMovieTypeID(movie.getMovieTypeID());
            dto.setName(movie.getName());
            dto.setRateID(movie.getRateID());
            dto.setTrailer(movie.getTrailer());

            movieDetailsDTOList.add(dto);
        }

        return movieDetailsDTOList;
    }
}
