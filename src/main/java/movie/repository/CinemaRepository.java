package movie.repository;

import movie.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    @Query(value = "SELECT c.nameofcinema AS cinemaName, " +
            "COALESCE(SUM(b.totalMoney), 0) AS totalSales " +
            "FROM cinema c " +
            "LEFT JOIN room r ON c.id = r.cinemaId " +
            "LEFT JOIN schedule s ON r.id = s.roomId " +
            "LEFT JOIN ticket t ON s.id = t.scheduleId " +
            "LEFT JOIN billticket bt ON t.id = bt.ticketId " +
            "LEFT JOIN bill b ON bt.billId = b.id AND b.isActive = true " +
            "AND b.update_time BETWEEN :startDate AND :endDate " +
            "GROUP BY c.nameofcinema " +
            "ORDER BY totalSales DESC",
            nativeQuery = true)
    List<Object[]> getCinemaSalesReport(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
