package ru.javamentor.springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.springbootcrud.model.User;
import ru.javamentor.springbootcrud.service.UserService;


@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsersList(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "usersList";
    }

    @GetMapping("/new")
    public String getNewUser(@ModelAttribute("user") User user) {        ;
        return "userToCreateDetailsForm";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/id")
    public String getIdForm() {
        return "userToReadIdForm";
    }

    @PostMapping("/user")
    public String getUserById(@RequestParam("userId") Long userId, Model model) {
        if (userId != null) {
            User user = userService.getUserById(userId);
            if (user != null) {
                model.addAttribute("user", user);
            } else {
                model.addAttribute("error", "User not found");
            }
        } else {
            model.addAttribute("error", "User ID parameter is missing");
        }
        return "userDetails";
    }

    @GetMapping("/limit")
    public String getLimitForm() {
        return "usersListLimitForm";
    }

    @PostMapping("/users")
    public String readUsersList(@RequestParam(value = "count", required = false, defaultValue = "0") int count,
                                Model model) {
        model.addAttribute("users", userService.getUsersList(count));
        return "usersList";
    }

    @GetMapping("/edit")
    public String getEditedUserIdForm() {
        return "userToEditIdForm";
    }

    @PostMapping("/editedUser")
    public String getEditedUserById(@RequestParam("userId") Long userId, Model model) {
        if (userId != null) {
            User user = userService.getUserById(userId);
            if (user != null) {
                model.addAttribute("user", user);
            } else {
                model.addAttribute("error", "User not found");
            }
        } else {
            model.addAttribute("error", "User ID parameter is missing");
        }
        return "userToUpdateDetailsForm";
    }

    @PostMapping("/update")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String getDeletedUserIdForm() {
        return "userToDeleteIdForm";
    }


    @PostMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("userId") Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/users";
    }
}
