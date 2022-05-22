package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.Address;
import isep.endoftrackproject._0money_c2c.model.User;
import isep.endoftrackproject._0money_c2c.repositories.AddressRepository;
import isep.endoftrackproject._0money_c2c.repositories.UserRepository;
import isep.endoftrackproject._0money_c2c.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView creationForm() {
        return new ModelAndView("user_creation", "user", new User());
    }

    @PostMapping
    public ModelAndView createUser(@Validated @ModelAttribute("user") User user) {
        Address address = user.getAddress();
        locationService.completeAddress(address);
        //TODO handle username and email uniqueness error
        //TODO handle password encryption and password validity in js
        addressRepository.save(address);
        userRepository.save(user);
        //TODO redirect to user profile page instead of user creation
        return new ModelAndView("user_creation", "user", user);
    }
}