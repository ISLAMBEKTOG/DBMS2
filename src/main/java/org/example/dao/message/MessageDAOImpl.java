package org.example.dao.message;

import org.example.model.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MessageDAOImpl {
    @PersistenceContext
    private EntityManager em;

    public Iterable<Message> findAll() {
        TypedQuery<Message> q = em.createNamedQuery("getAllMessages", Message.class);
        return q.getResultList();
    }

    public List<Message> getAllMessagesByUserIdAndSpecId(Long userId, Long specId) {
        TypedQuery<Message> q = em.createNamedQuery("getAllMessagesByUserIdAndSpecId", Message.class);
        q.setParameter("userId", userId);
        q.setParameter("specId", specId);
        return q.getResultList();
    }

    public void deleteMessage(Message message) {
        StoredProcedureQuery messages = em.createNamedStoredProcedureQuery("deleteMessage");
        messages.setParameter("messId", message.getId());
        messages.execute();
    }

    public Message addMessage(Message message) {
        StoredProcedureQuery messages = em.createNamedStoredProcedureQuery("addMessage");
        messages.setParameter("p_content", message.getContent());
        messages.setParameter("p_created_date", message.getDate());
        messages.setParameter("p_specId", message.getSpecialists().getId());
        messages.setParameter("p_userId", message.getUser().getId());
        messages.execute();

        return (Message) messages.getSingleResult();
    }

    public List<Message> getAllMessagesBySpecId(Long specId) {
        TypedQuery<Message> q = em.createNamedQuery("getAllMessagesBySpecId", Message.class);
        q.setParameter("specId", specId);
        return q.getResultList();
    }
}
