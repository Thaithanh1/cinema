package movie.repository;

import movie.entity.ConfirmEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmEmailRepository extends JpaRepository<ConfirmEmail, Integer> {
    ConfirmEmail findByConfirmCode(String confirmCode);
}
