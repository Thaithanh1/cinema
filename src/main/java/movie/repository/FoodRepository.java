package movie.repository;

import movie.entity.Food;
import movie.payload.dto.FoodSalesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    @Query(value = "SELECT f.nameOfFood, SUM(bf.quantity) AS total_quantity " +
            "FROM Food f " +
            "JOIN BillFood bf ON f.id = bf.foodId " +
            "JOIN Bill b ON bf.billId = b.id " +
            "WHERE b.update_time >= CURDATE() - INTERVAL 7 DAY " +
            "GROUP BY f.nameOfFood " +
            "ORDER BY total_quantity DESC " +
            "LIMIT 1", nativeQuery = true)
    List<Object[]> findTopFoodByQuantityLastWeek();
}
