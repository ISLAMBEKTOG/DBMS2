package org.example.dao.userbucket;

import org.example.model.UserBucket;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserBucketDAOImpl {
    @PersistenceContext
    private EntityManager em;

    public UserBucket getUserBucketByUserIdAndSpecialistId(Long userId, Long specId) {
        TypedQuery<UserBucket> q = em.createNamedQuery("getUserBucketByUserIdAndSpecialistId", UserBucket.class);
        q.setParameter("userId", userId);
        q.setParameter("specId", specId);
        return q.getSingleResult();
    }

    public List<UserBucket> getAllUserBucketsBySpecialistId(Long id) {
        TypedQuery<UserBucket> q = em.createNamedQuery("getAllUserBucketsBySpecialistId", UserBucket.class);
        q.setParameter("specId", id);
        return q.getResultList();
    }

    public void addUserBucket(UserBucket userBucket) {
        StoredProcedureQuery specialist = em.createNamedStoredProcedureQuery("addUserBucket");
        specialist.setParameter("p_specId", userBucket.getSpecialists().getId());
        specialist.setParameter("p_userId", userBucket.getUser().getId());
        specialist.execute();
    }

    public void deleteUserBucket(Long id) {
        StoredProcedureQuery specialist = em.createNamedStoredProcedureQuery("deleteUserBucket");
        specialist.setParameter("p_id", id);
        specialist.execute();
    }
}
