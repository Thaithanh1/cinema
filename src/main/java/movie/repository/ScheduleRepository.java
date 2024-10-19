package movie.repository;

import movie.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    boolean existsByRoomIDAndStartAtLessThanEqualAndEndAtGreaterThanEqual(
            int roomID, Date startAt, Date endAt
    );

    void deleteByScheduleID(Integer integer);
}
