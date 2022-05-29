package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.User;
import isep.endoftrackproject._0money_c2c.repositories.AddressRepository;
import isep.endoftrackproject._0money_c2c.repositories.UserRepository;
import isep.endoftrackproject._0money_c2c.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    LocationService locationService;

    @GetMapping
    public String creationForm(Model model) {
        model.addAttribute("mode", "create");
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping
    public RedirectView createUser(@Validated @ModelAttribute("user") User user) {
        locationService.completeAddress(user.getAddress());
        //TODO handle username and email uniqueness error
        //TODO handle password encryption and password validity in js
        userRepository.save(user);
        return new RedirectView("/users/" + user.getId());
    }

    @RequestMapping("/{id}")
    public String viewUser(@PathVariable("id") User user, @RequestParam("mode") Optional<String> mode, Model model) {
        if (mode.isEmpty()) mode = Optional.of("view");
        //TODO check mode depending on user rights
        model.addAttribute("mode", mode.get());
        model.addAttribute("self", false);
        model.addAttribute("user", user);
        return "/user";
    }
}
