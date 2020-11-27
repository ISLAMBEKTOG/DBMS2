package org.example.dao.user;

import org.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl {
    @PersistenceContext
    private EntityManager em;

    public Iterable<User> findAll() {
        TypedQuery<User> q = em.createNamedQuery("getAllUsers", User.class);
        return q.getResultList();
    }

    public List<User> getAllUsersFromUserBucketBySpecialistId(long id) {
        TypedQuery<User> q = em.createNamedQuery("getAllUsersFromUserBucketBySpecialistId", User.class);
        q.setParameter("specId", id);
        return q.getResultList();
    }

    public User getUserById(Long id) {
        TypedQuery<User> q = em.createNamedQuery("getUserById", User.class);
        q.setParameter("userId", id);
        return q.getSingleResult();
    }

    public void addUser(User user) {
        StoredProcedureQuery specialist = em.createNamedStoredProcedureQuery("addUser");
        specialist.setParameter("p_age", user.getAge());
        specialist.setParameter("p_email", user.getEmail());
        specialist.setParameter("p_first_name", user.getFirstName());
        specialist.setParameter("p_last_name", user.getLastName());
        specialist.setParameter("p_password", user.getPassword());
        specialist.setParameter("p_username", user.getUsername());
        specialist.setParameter("p_roleId", user.getRole().getId());
        specialist.execute();
    }

    public void updateUser(User user) {
        StoredProcedureQuery specialist = em.createNamedStoredProcedureQuery("updateUser");
        specialist.setParameter("p_id", user.getId());
        specialist.setParameter("p_age", user.getAge());
        specialist.setParameter("p_email", user.getEmail());
        specialist.setParameter("p_first_name", user.getFirstName());
        specialist.setParameter("p_last_name", user.getLastName());
        specialist.setParameter("p_password", user.getPassword());
        specialist.setParameter("p_username", user.getUsername());
        specialist.setParameter("p_roleId", user.getRole().getId());
        specialist.execute();
    }
}
