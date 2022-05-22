package isep.endoftrackproject._0money_c2c.repositories;

import isep.endoftrackproject._0money_c2c.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    // This query is using the Haversine formula to calculate distance in km between two coordinates around the globe
    @Query(value = "SELECT i FROM Item i " +
            "INNER JOIN User u ON u.id = i.owner.id " +
            "INNER JOIN Address a ON u.address.id = a.id " +
            "WHERE ?1 <= i.price AND i.price <= ?2 " +
            "AND acos(sin(radians(?3)) * sin(radians(a.latitude)) + cos(radians(?3)) * cos(radians(a.latitude)) * cos(radians(a.longitude) - (radians(?4)))) * 6371 <= ?5 " +
            "AND i.quality in (?7) " +
            "AND i.available = ?6 ")
    Iterable<Item> search(Double priceMin, Double priceMax, Double latitude, Double longitude, Double maxDistance, Boolean isAvailable, Item.Quality[] acceptedQualities);
}
