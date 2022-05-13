package isep.endoftrackproject._0money_c2c.repositories;

import isep.endoftrackproject._0money_c2c.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);
}

