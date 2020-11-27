package org.example.controller;

import org.example.model.Message;
import org.example.model.Specialists;
import org.example.model.User;
import org.example.model.UserBucket;
import org.example.service.message.MessageService;
import org.example.service.specialist.SpecialistsService;
import org.example.service.user.UserService;
import org.example.service.userbucket.UserBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyCabinetController {
    private final SpecialistsService specialistsService;
    private final UserService userService;
    private final UserBucketService userBucketService;
    private final MessageService messageService;


    @Autowired
    public MyCabinetController(SpecialistsService specialistsService, UserService userService, UserBucketService userBucketService, MessageService messageService) {
        this.specialistsService = specialistsService;
        this.userService = userService;
        this.userBucketService = userBucketService;
        this.messageService = messageService;
    }

    @GetMapping("/myCabinet")
    public String getMyCabinetPage(Model model,
                                   @AuthenticationPrincipal User user) {
        User userDB = userService.getUserByUsername(user.getUsername());
        List<Specialists> specialists = userBucketService.getSpecialistsByUserId(userDB.getId());

        model.addAttribute("specialists", specialists);
        model.addAttribute("user", userDB);

        return "myCabinet";
    }

    @GetMapping("/addSpecialist/{blockId}/{id}")
    public String addSpecialistToMyCabinet(@PathVariable int blockId,
                                           @PathVariable long id,
                                           @AuthenticationPrincipal User user) {
        User userDB = userService.getUserByUsername(user.getUsername());
        Specialists specialist = specialistsService.getSpecialistById(id);

        userBucketService.addSpecialistToUserBucket(new UserBucket(userDB, specialist));

        return "redirect:/specialists/" + blockId + "/0/40";
    }

    //    Message Controller
    @GetMapping("/api/message")
    @ResponseBody
    public List<Message> getAllMessagesById(long id,
                                            @AuthenticationPrincipal User user) {
        User userDB = userService.getUserByUsername(user.getUsername());
        Specialists specialist = specialistsService.getSpecialistById(id);
        List<Message> messages = new ArrayList<>();

        if (userDB != null && specialist != null) {
            messages = messageService.getAllMessagesById(userDB.getId(), specialist.getId());
        }

        return messages;
    }

    @GetMapping("/api/getSpecialist/{id}")
    @ResponseBody
    public Specialists getSpecialist(@PathVariable long id) {
        return specialistsService.getSpecialistById(id);
    }

    @GetMapping("/api/messageClient")
    @ResponseBody
    public List<Message> getAllMessagesOfClientById(long id,
                                                    @AuthenticationPrincipal User user) {
        User userDB = userService.getUserByUsername(user.getUsername());
        List<Specialists> specialists = specialistsService.getAllSpecialistsFromUserResumeByUserId(userDB.getId());

        User clientUser = userService.getUserById(id);

        List<Message> messages = new ArrayList<>();

        for (Specialists s : specialists) {
            UserBucket bucket = userBucketService.getUserBucketByUserIdAndSpecId(clientUser.getId(), s.getId());

            if (bucket != null) {
                messages = messageService.getAllMessagesById(clientUser.getId(), s.getId());
            }
        }

        return messages;
    }

    @GetMapping("/api/getUser/{id}")
    @ResponseBody
    public User getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

}
