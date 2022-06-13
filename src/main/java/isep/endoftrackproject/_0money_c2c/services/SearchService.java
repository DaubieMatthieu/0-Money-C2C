package isep.endoftrackproject._0money_c2c.services;

import isep.endoftrackproject._0money_c2c.model.Item;
import isep.endoftrackproject._0money_c2c.model.Search;
import isep.endoftrackproject._0money_c2c.model.User;
import isep.endoftrackproject._0money_c2c.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class SearchService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserService userService;

    public Iterable<Item> search(Search search) {
        //Retrieval and partial Filtering
        Iterable<Item> searchResults = itemRepository.
                search(search.getPriceMin(), search.getPriceMax(), search.getLatitude(), search.getLongitude(), search.getMaxDistance(), search.getAvailable(), search.getAcceptedQualities());

        //Do not display the items of the current user
        User currentUser = userService.getCurrentUser();
        if (currentUser!=null) {
            searchResults = StreamSupport.stream(searchResults.spliterator(), false).filter(item -> item.getOwner()!=currentUser).collect(Collectors.toSet());
        }

        List<String> querySplit = Arrays.asList(search.getKeywords().split(" "));
        List<Integer> queryVector = new ArrayList<>(Collections.nCopies(querySplit.size(), 1));
        Map<Item, Double> keywordsScore = new HashMap<>();

        for (Item item : searchResults) {
            String description = item.getDescription() + " " + item.getName();
            double score = score(keyWordDetection(querySplit, description), queryVector);
            if (score != 0) {
                keywordsScore.put(item, score);
            }
        }

        List<Map.Entry<Item, Double>> sortedList = new ArrayList<>(keywordsScore.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());
        Map<Item, Double> result = new LinkedHashMap<>();

        for (Map.Entry<Item, Double> entry : sortedList) {
            result.put(entry.getKey(), entry.getValue());
//            System.out.println(entry.getValue()); //for debug
        }

        return result.keySet();
    }

    public List<Integer> keyWordDetection(List<String> query, String description) {
        int querySize = query.size();
        List<Integer> result = new ArrayList<>(Collections.nCopies(querySize, 0));

        for (int i = 0; i < querySize; i++) {
            if (description.contains(query.get(i))) {
                result.set(i, 1);
            }
        }
        return result;
    }

    public double score(List<Integer> vector1, List<Integer> vector2) {
        int scalaire = 0;
        double normVector1 = 0;
        double normVector2 = 0;

        for (int i = 0; i < vector1.size(); i++) {
            scalaire += vector1.get(i) * vector2.get(i);
            normVector1 += vector1.get(i);
            normVector2 += vector2.get(i);
        }
        double normProduct = (Math.sqrt(normVector1) * Math.sqrt(normVector2));
        if (normProduct != 0) {
            return Math.cos(scalaire / normProduct);
        } else {
            return 0;
        }
    }
}
