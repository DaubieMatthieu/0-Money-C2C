package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.Item;
import isep.endoftrackproject._0money_c2c.model.Search;
import isep.endoftrackproject._0money_c2c.repositories.ItemRepository;
import isep.endoftrackproject._0money_c2c.services.SearchService;
import isep.endoftrackproject._0money_c2c.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ItemController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    SearchService searchService;
    @Autowired
    UserService userService;

    //This is the home page, it should be accessible from multiple basic urls
    @GetMapping(value = {"", "/q"})
    public ModelAndView index() {
        return new ModelAndView("index", "search", new Search());
    }

    @PostMapping("/q")
    public String advancedSearch(@Valid @ModelAttribute("search") Search search, Model model) {
        Iterable<Item> searchResults = searchService.search(search);
        model.addAttribute("search", search);
        model.addAttribute("results", searchResults);
        return "/index";
    }

    @GetMapping("/item")
    @PreAuthorize("isAuthenticated()")
    public String creationForm(Model model) {
        model.addAttribute("mode", "create");
        model.addAttribute("title", "New item");
        Item item = new Item();
        item.setOwner(userService.getCurrentUser());
        model.addAttribute("self", true);
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping("/item")
    @PreAuthorize("isAuthenticated()")
    public String createItem(@ModelAttribute("item") @Valid Item item, BindingResult result, Model model, HttpServletRequest request) {
        model.addAttribute("self", true);
        model.addAttribute("mode", "create");
        model.addAttribute("title", "New item");
        model.addAttribute("item", item);
        if (result.hasErrors()) {
            //wrong parameters, the errors are automatically added to the model
            return "item";
        }
        try {
            itemRepository.save(item);
            model.addAttribute("message", "Registered item");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "/item";
        }
        return "redirect:/item/" + item.getId();
    }

    @PostMapping("/item/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editUser(@ModelAttribute("item") @Valid Item item, BindingResult result, Model model, HttpServletRequest request) {
        item.setOwner(userService.getById(item.getOwner().getId()));
        if (!userService.isCurrentUser(item.getOwner()))
            throw new IllegalArgumentException("Not enough rights to update this item");
        if (result.hasErrors()) {
            //wrong parameters, the errors are automatically added to the model
            model.addAttribute("item", item);
            model.addAttribute("mode", "edit");
            model.addAttribute("self", true);
            model.addAttribute("title", "Edit " + item.getName());
            return "item";
        }
        try {
            itemRepository.save(item);
        } catch (Exception e) {
            model.addAttribute("mode", "edit");
            model.addAttribute("message", e.getMessage());
            model.addAttribute("item", item);
            model.addAttribute("title", "Edit " + item.getName());
            model.addAttribute("self", true);
            return "/item";
        }
        //redirect to profile
        return "redirect:/item/" + item.getId();
    }


    @GetMapping("/item/{id}")
    public String viewItem(@PathVariable("id") Item item, @RequestParam("mode") Optional<String> requestedMode, Model model) {
        String mode = requestedMode.orElse("view");
        boolean self = userService.isCurrentUser(item.getOwner());
        if (mode.equals("edit") && !self) mode = "view";
        model.addAttribute("mode", mode);
        model.addAttribute("self", self);
        String title = ((mode.equals("edit")) ? "Edit " : "") + item.getName();
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        return "/item";
    }
}
