package earthquakes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import earthquakes.entities.AppUser;
import earthquakes.repositories.UserRepository;

@Controller
public class UsersController {

    private UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;   
    }
    
    @GetMapping("/users")
    public String index(Model model) {
        Iterable<AppUser> users= userRepository.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

}
