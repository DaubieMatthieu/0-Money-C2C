package isep.endoftrackproject._0money_c2c.repositories;

import isep.endoftrackproject._0money_c2c.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.StreamSupport;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    @Query(value = "SELECT i FROM Item i WHERE i.name = ?1")
    Optional<Item> findByName(String name);

    @Query(value = "SELECT i FROM Item i WHERE i.name LIKE %?1%")
    Iterable<Item> searchByName(String name);

    @Query(value = "SELECT i FROM Item i " +
            "INNER JOIN User u ON u.id = i.owner.id " +
            "INNER JOIN Address a ON u.address.id = a.id " +
            "WHERE ?2 < i.price AND i.price < ?1 " +
            "AND SQRT(POWER(a.latitude - ?3, 2) + POWER(a.longitude - ?4, 2)) <= ?5 " +
            "AND i.quality in ?6" +
            "AND i.available = ?7")
    Iterable<Item> search(Double priceMin, Double priceMax, Double latitude, Double longitude, Double maxDistance, Item.Quality[] acceptedQualities, Boolean isAvailable);
}
