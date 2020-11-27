package org.example.service.message;

import org.example.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();

    List<Message> getAllMessagesById(Long userId, Long specId);

    Message addMessage(Message message);

    void deleteMessage(Message message);

    List<Message> getAllMessagesBySpecId(Long specId);
}
