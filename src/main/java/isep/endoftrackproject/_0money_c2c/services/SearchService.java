package isep.endoftrackproject._0money_c2c.services;

import isep.endoftrackproject._0money_c2c.model.Item;
import isep.endoftrackproject._0money_c2c.model.Search;
import isep.endoftrackproject._0money_c2c.repositories.ItemRepository;

import java.util.ArrayList;
import java.util.List;

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

        //using ArrayList to order the items
        List<Item> temp = new ArrayList<Item>();
        searchResults.forEach(temp::add);

        
        //Classement dans l'ordre décroissant du score

        for(int i = 0; i< temp.size(); i++)
        { 
            for(int j = 0; j > temp.size()-1; j++)
            {
                if(temp.get(j+1).getscore()<temp.get(j).getscore())
                {
                    Item tempItem =temp.get(j+1);
                    temp.set(j+1, temp.get(j));
                    temp.set(j, tempItem);  
                }
            }
        }
        //On applique la list trié par score décroissant au resultat final
        searchResults = temp;
        return searchResults;
    }
}
