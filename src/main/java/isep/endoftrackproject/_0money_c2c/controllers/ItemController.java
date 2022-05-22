package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.Item;
import isep.endoftrackproject._0money_c2c.model.Search;
import isep.endoftrackproject._0money_c2c.repositories.ItemRepository;
import isep.endoftrackproject._0money_c2c.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    SearchService searchService;

    //This is the home page, it should be accessible from multiple basic urls
    @GetMapping(value = {"", "/home", "/q"})
    public ModelAndView index() {
        return new ModelAndView("index", "search", new Search());
    }

    @PostMapping("/q")
    public String advancedSearch(@Validated @ModelAttribute("search") Search search, Model model) {
        Iterable<Item> searchResults = searchService.search(search);
        model.addAttribute("search", search);
        model.addAttribute("results", searchResults);
        return "/index";
    }

    @GetMapping("/items")
    public ModelAndView creationForm() {
        return new ModelAndView("item_creation", "item", new Item());
    }

    @PostMapping("/items")
    public ModelAndView createItem(@Validated @ModelAttribute("item") Item item) {
        return new ModelAndView("item_creation", "item", itemRepository.save(item));
    }
}
