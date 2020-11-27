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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MessageRestController {
    private final MessageService messageService;
    private final UserService userService;
    private final SpecialistsService specialistsService;
    private final UserBucketService userBucketService;

    @Autowired
    public MessageRestController(MessageService messageService, UserService userService, SpecialistsService specialistsService, UserBucketService userBucketService) {
        this.messageService = messageService;
        this.userService = userService;
        this.specialistsService = specialistsService;
        this.userBucketService = userBucketService;
    }

    @PostMapping("/api/addMessage")
    public ResponseEntity<Message> addMessage(@RequestParam("content") String content,
                                             @RequestParam("specId") String specId,
                                             @AuthenticationPrincipal User user) {

        User userDB = userService.getUserByUsername(user.getUsername());
        Specialists specialist = specialistsService.getSpecialistById(Long.parseLong(specId));

        Message message = new Message(userDB, specialist, content, new Date());
        Message addMessage = messageService.addMessage(message);

        return new ResponseEntity<>(addMessage, HttpStatus.OK);
    }

    @PostMapping("/api/addMessageToClient")
    public ResponseEntity<Message> addMessageToClient(@RequestParam("content") String content,
                                              @RequestParam("userId") String userId,
                                              @AuthenticationPrincipal User user) {

        User clientUser = userService.getUserById(Long.parseLong(userId));

        User userDB = userService.getUserByUsername(user.getUsername());
        List<Specialists> specialists = specialistsService.getAllSpecialistsFromUserResumeByUserId(userDB.getId());

        Message addMessage = null;

        for (Specialists s: specialists) {
            UserBucket bucket = userBucketService.getUserBucketByUserIdAndSpecId(clientUser.getId(), s.getId());

            if(bucket != null) {
                Message message = new Message(clientUser, s, content, new Date());
                addMessage = messageService.addMessage(message);
            }
        }

        return new ResponseEntity<>(addMessage, HttpStatus.OK);
    }
}
