package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.Item;
import isep.endoftrackproject._0money_c2c.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    public ModelAndView creationForm() {
        return new ModelAndView("item_creation", "item", new Item());
    }

    @PostMapping
    public ModelAndView submit(@Validated @ModelAttribute("item") Item item) {
        return new ModelAndView("item_creation", "item", itemRepository.save(item));
    }
}
