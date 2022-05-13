package isep.endoftrackproject._0money_c2c.services;

import isep.endoftrackproject._0money_c2c.model.Item;
import isep.endoftrackproject._0money_c2c.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class SearchService {
    private final ItemRepository itemRepository;

    public Iterable<Item> search(Optional<String> keywords) {
        return itemRepository.searchByName(keywords.orElse(""));
    }

    public SearchService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
