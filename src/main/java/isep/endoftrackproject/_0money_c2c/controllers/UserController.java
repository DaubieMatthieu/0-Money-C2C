package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.User;
import isep.endoftrackproject._0money_c2c.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/users")
class UserController {
    private final UserRepository repository;

    @GetMapping
    public ModelAndView creationForm() {
        return new ModelAndView("user", "user", new User());
    }

    @PostMapping
    public ModelAndView submit(@Validated @ModelAttribute("user") User user) {
        return new ModelAndView("user", "user", repository.save(user));
    }

    public UserController(UserRepository repository) {
        this.repository = repository;
    }
}