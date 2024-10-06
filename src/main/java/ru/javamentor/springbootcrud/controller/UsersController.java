package ru.javamentor.springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.springbootcrud.model.User;
import ru.javamentor.springbootcrud.service.UserService;

import javax.validation.Valid;
import java.util.List;


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
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userToCreateDetailsForm";
        }
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/id")
    public String getIdForm() {
        return "userToReadIdForm";
    }

    @PostMapping("/user")
    public String getUserById(@RequestParam("userId") Long userId, Model model) {
        if (userId == null) {
            model.addAttribute("error", "User ID parameter is missing");
            return "userToReadIdForm";
        }
        if (userId <= 0) {
            model.addAttribute("error", "User ID should be 1 or more");
            return "userToReadIdForm";
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "userToReadIdForm";
        }
        model.addAttribute("user", user);
        return "userDetails";
    }

    @GetMapping("/limit")
    public String getLimitForm() {
        return "usersListLimitForm";
    }

    @PostMapping("/users")
    public String readUsersList(@RequestParam(value = "count", required = false, defaultValue = "0") int count,
                                Model model) {
        if (count == 0) {
            model.addAttribute("error", "Users List Limit is missing");
            return "usersListLimitForm";
        }
        if (count < 0) {
            model.addAttribute("error", "User List Limit should be 1 or more");
            return "usersListLimitForm";
        }
        List<User> users = userService.getUsersList(count);
        if (users.isEmpty()) {
            model.addAttribute("error", "Users List not found");
            return "usersListLimitForm";
        }
        model.addAttribute("users", users);
        return "usersList";
    }

    @GetMapping("/edit")
    public String getEditedUserIdForm() {
        return "userToEditIdForm";
    }

    @PostMapping("/editedUser")
    public String getEditedUserById(@RequestParam("userId") Long userId, Model model) {
        if (userId == null) {
            model.addAttribute("error", "User ID parameter is missing");
            return "userToUpdateDetailsForm";
        }
        if (userId <= 0) {
            model.addAttribute("error", "User ID should be 1 or more");
            return "userToUpdateDetailsForm";
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "userToUpdateDetailsForm";
        }
        model.addAttribute("user", user);
        return "userToUpdateDetailsForm";
    }

    @PostMapping("/update")
    public String editUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userToUpdateDetailsForm";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String getDeletedUserIdForm() {
        return "userToDeleteIdForm";
    }


    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long userId, Model model) {
        if (userId == null) {
            model.addAttribute("error", "User ID parameter is missing");
            return "userToDeleteIdForm";
        }
        if (userId <= 0) {
            model.addAttribute("error", "User ID should be 1 or more");
            return "userToDeleteIdForm";
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "userToDeleteIdForm";
        }
        userService.deleteUserById(userId);
        return "redirect:/users";
    }
}
