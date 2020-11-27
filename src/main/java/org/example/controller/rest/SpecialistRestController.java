package org.example.controller.rest;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecialistRestController {
    private final SpecialistsService specialistsService;
    private final UserService userService;
    private final UserBucketService userBucketService;
    private final MessageService messageService;

    @Autowired
    public SpecialistRestController(SpecialistsService specialistsService, UserService userService, UserBucketService userBucketService, MessageService messageService) {
        this.specialistsService = specialistsService;
        this.userService = userService;
        this.userBucketService = userBucketService;
        this.messageService = messageService;
    }

    @PostMapping("/api/deleteSpecialist")
    @ResponseBody
    public void deleteSpecialist(@RequestParam(name = "id") Long id,
                                 @AuthenticationPrincipal User user) {

        Specialists specialist = specialistsService.getSpecialistById(id);
        User userDB = userService.getUserByUsername(user.getUsername());

        if (specialist != null && userDB != null) {
            UserBucket userBucket = userBucketService.getUserBucketByUserIdAndSpecId(userDB.getId(), specialist.getId());
            userBucketService.deleteSpecialistAndUser(userBucket);

            List<Message> messages = messageService.getAllMessagesById(userDB.getId(), specialist.getId());

            for (Message m: messages) {
                messageService.deleteMessage(m);
            }

        }
    }
}
