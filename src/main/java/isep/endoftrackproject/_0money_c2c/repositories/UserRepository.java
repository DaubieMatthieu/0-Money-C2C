package isep.endoftrackproject._0money_c2c.repositories;

import isep.endoftrackproject._0money_c2c.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}

