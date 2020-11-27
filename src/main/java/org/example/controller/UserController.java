package org.example.controller;

import org.example.model.Specialists;
import org.example.model.User;
import org.example.service.specialist.SpecialistsService;
import org.example.service.user.UserService;
import org.example.service.userbucket.UserBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final SpecialistsService specialistsService;

    @Autowired
    public UserController(UserService userService, SpecialistsService specialistsService) {
        this.userService = userService;
        this.specialistsService = specialistsService;
    }

    @GetMapping("/userPage")
    public String getUserPage(@AuthenticationPrincipal User user,
                              Model model) {
        model.addAttribute("user", user);
        return "userPage";
    }

    @PostMapping("/userPage")
    public String updateUser(@Valid User user,
                             @RequestParam("mPassword") String mPassword,
                             @AuthenticationPrincipal User authUser) {
        if (user.getPassword().equals(mPassword)) {
            if (authUser.getUsername().equals(user.getUsername())) {
                userService.updateUser(user);
                return "main";
            } else {
                User curUser = userService.getUserByUsername(user.getUsername());
                if (curUser == null) {
                    userService.updateUser(user);
                    return "main";
                }
            }
        }
        return "userPage";
    }

    @GetMapping("/myClients")
    public String getMyClientsPage(Model model,
                                   @AuthenticationPrincipal User user) {
        User userDB = userService.getUserByUsername(user.getUsername());
        List<Specialists> specialists = specialistsService.getAllSpecialistsFromUserResumeByUserId(userDB.getId());

        List<User> allUsers = new ArrayList<>();

        for (Specialists s : specialists) {
            List<User> users = userService.getAllUsersFromUserBucketBySpecialistId(s.getId());
            allUsers.addAll(users);
        }

        model.addAttribute("users", allUsers);
        model.addAttribute("user", userDB);

        return "myClients";
    }
}
