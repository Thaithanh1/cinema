package movie.repository;

import movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT m FROM Movie m " +
            "JOIN m.schedules sc " +
            "JOIN sc.room r " +
            "JOIN r.cinema c " +
            "JOIN r.seats stt " +
            "JOIN stt.seatStatus ss " +
            "WHERE c.nameOfCinema = :cinemaName " +
            "OR r.name = :roomName " +
            "OR ss.nameStatus = :seatStatus")
    List<Movie> findMoviesByCinemaAndRoomAndSeatStatus(
            @Param("cinemaName") String cinemaName,
            @Param("roomName") String roomName,
            @Param("seatStatus") String seatStatus
    );
}
