package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.Item;
import isep.endoftrackproject._0money_c2c.model.Search;
import isep.endoftrackproject._0money_c2c.repositories.ItemRepository;
import isep.endoftrackproject._0money_c2c.services.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/items")
public class ItemController {
    final ItemRepository repository;
    final SearchService searchService;

    @GetMapping("/s")
    public String searchItem(@RequestParam("name") Optional<String> name, Model model) {
        Iterable<Item> searchResults = searchService.search(name);
        model.addAttribute("results", searchResults);
        return "/index";
    }

    @GetMapping("/q")
    public ModelAndView index() {
        return new ModelAndView("index", "search", new Search());
    }

    @PostMapping("/q")
    public String advancedSearch(@Validated @RequestParam("search") Search search, Model model) {
        Iterable<Item> searchResults = repository.search(search.getPriceMin(), search.getPriceMax(), search.getLatitude(), search.getLongitude(), search.getMaxDistance(), search.getAcceptedQualities(), search.getAvailable());
        model.addAttribute("search", search);
        model.addAttribute("results", searchResults);
        return "/index";
    }

    @GetMapping
    public ModelAndView creationForm() {
        return new ModelAndView("item", "item", new Item());
    }

    @PostMapping
    public ModelAndView submit(@Validated @ModelAttribute("item") Item item) {
        return new ModelAndView("item", "item", repository.save(item));
    }

    public ItemController(ItemRepository repository, SearchService searchService) {
        this.repository = repository;
        this.searchService = searchService;
    }
}
