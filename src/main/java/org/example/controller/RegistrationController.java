package org.example.controller;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.role.RoleService;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RegistrationController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@Valid User user) {
        User curUser = userService.getUserByUsername(user.getUsername());
        if (curUser != null) {
            return "registration";
        } else {
            Role role = roleService.getRoleByName("USER");
            user.setRole(role);
            userService.registrationUser(user);
            return "redirect:/main";
        }
    }
}
