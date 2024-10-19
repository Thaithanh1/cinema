package movie.repository;

import movie.entity.Role;
import movie.enums.RoleEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleEnums(RoleEnums roleEnums);
}
