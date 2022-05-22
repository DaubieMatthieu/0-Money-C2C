package isep.endoftrackproject._0money_c2c.services;

import isep.endoftrackproject._0money_c2c.model.Item;
import isep.endoftrackproject._0money_c2c.model.Search;
import isep.endoftrackproject._0money_c2c.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    private ItemRepository itemRepository;

    public Iterable<Item> search(Search search) {
        //Retrieval and partial Filtering
        Iterable<Item> searchResults = itemRepository.
                search(search.getPriceMin(), search.getPriceMax(), search.getLatitude(), search.getLongitude(), search.getMaxDistance(), search.getAvailable(), search.getAcceptedQualities());
        //TODO Ranking -> return an ordered list
        return searchResults;
    }
}
