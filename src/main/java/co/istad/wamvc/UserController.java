package co.istad.wamvc;// UserController.java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();

    public UserController() {
        // Initialize some static data
        users.add(new User("1", "Sokcheat", "sokcheatsrorng@gmail.com"));
        users.add(new User("2", "SeaMey", "seamey123@gmail.com"));
        users.add(new User("3", "Kang", "kangdev@gmail.com"));
        users.add(new User("4", "Jipor", "jipordev123@gmail.com"));
        users.add(new User("5", "Chiso", "chisoproeung@gmail.com"));
    }
    // read all users from list
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        users.add(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        User user = users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        users.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst()
                .ifPresent(u -> {
                    u.setName(user.getName());
                    u.setEmail(user.getEmail());
                });
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        users.removeIf(u -> u.getId().equals(id));
        return "redirect:/";
    }
}
