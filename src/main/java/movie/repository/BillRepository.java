package movie.repository;

import movie.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query("SELECT c.nameOfCinema, SUM(t.priceTicket) " +
            "FROM Cinema c " +
            "JOIN c.rooms r " +
            "JOIN r.schedules s " +
            "JOIN s.tickets t " +
            "JOIN t.billTickets bt " +
            "JOIN bt.bill b " +
            "WHERE b.createTime BETWEEN :startTime AND :endTime " +
            "GROUP BY c.nameOfCinema")
    List<Object[]> calculateSalesStatisticsByCinemaAndTimeRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query(value = "SELECT t.PriceTicket AS totalMoney " +
            "FROM Ticket t " +
            "LEFT JOIN Schedule s ON t.ScheduleId = s.Id " +
            "LEFT JOIN Room r ON s.RoomId = r.Id " +
            "LEFT JOIN Cinema c ON r.CinemaId = c.Id " +
            "LEFT JOIN Movie m ON s.MovieId = m.Id " +
            "WHERE m.Id = :movieId " +
            "AND c.Id = :cinemaId " +
            "AND r.Id = :roomId " +
            "AND t.Id = :ticketId", nativeQuery = true)
    Double findTicketPrice(@Param("movieId") Integer movieId,
                          @Param("cinemaId") Integer cinemaId,
                          @Param("roomId") Integer roomId,
                          @Param("ticketId") Integer ticketId);

    @Query("SELECT f.price FROM Food f " +
            "WHERE f.foodID = :foodId")
    Double findFoodPrice(@Param("foodId") Integer foodId);

    @Query(value = "SELECT m.Name AS movieName " +
            "FROM Movie m " +
            "WHERE m.Id = :movieId", nativeQuery = true)
    String findMovieNameById(@Param("movieId") Integer movieId);

    Bill findByTradingCode(String tradingCode);

}
