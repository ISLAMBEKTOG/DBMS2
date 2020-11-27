package org.example.service.message;

import org.example.dao.message.MessageDAO;
import org.example.dao.message.MessageDAOImpl;
import org.example.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageDAO messageDAO;
    private final MessageDAOImpl messageDAOImpl;

    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO, MessageDAOImpl messageDAOImpl) {
        this.messageDAO = messageDAO;
        this.messageDAOImpl = messageDAOImpl;
    }

    @Override
    public List<Message> getAllMessages() {
        return (List<Message>) messageDAOImpl.findAll();
    }

    @Override
    public List<Message> getAllMessagesById(Long userId, Long specId) {
        return messageDAOImpl.getAllMessagesByUserIdAndSpecId(userId, specId);
    }

    @Override
    public Message addMessage(Message message) {
        return messageDAOImpl.addMessage(message);
    }

    @Override
    public void deleteMessage(Message message) {
        messageDAOImpl.deleteMessage(message);
    }

    @Override
    public List<Message> getAllMessagesBySpecId(Long specId) {
        return messageDAOImpl.getAllMessagesBySpecId(specId);
    }
}
