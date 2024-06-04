package com.hutech.demo.controller;

import com.hutech.demo.model.User;
import com.hutech.demo.service.RoleService;
import com.hutech.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService; // Đảm bảo bạn đã inject CategoryService
// Display a list of all products
    @GetMapping
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/users/user-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles()); //Load categories
        return "/users/add-user";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/users/add-user";
        }
        userService.addUser(user);
        return "redirect:/users";
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles()); //Load categories
        return "/users/update-user";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id); // set id to keep it in the form in case of errors
            return "/users/update-user";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteProductById(id);
        return "redirect:/users";
    }
}
