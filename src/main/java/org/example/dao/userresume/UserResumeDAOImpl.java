package org.example.dao.userresume;

import org.example.model.UserResume;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

@Repository
public class UserResumeDAOImpl {
    @PersistenceContext
    private EntityManager em;

    public UserResume getUserResumesByUserIdAndSpecialistId(@Param("userId") long userId,
                                                            @Param("specId") long specId) {
        TypedQuery<UserResume> q = em.createNamedQuery("getUserResumesByUserIdAndSpecialistId", UserResume.class);
        q.setParameter("userId", userId);
        q.setParameter("specId", specId);
        return q.getSingleResult();
    }

    public void addUserResume(UserResume userResume) {
        StoredProcedureQuery specialist = em.createNamedStoredProcedureQuery("addUserResume");
        specialist.setParameter("p_specId", userResume.getSpecialists().getId());
        specialist.setParameter("p_userId", userResume.getUser().getId());
        specialist.execute();
    }

    public void deleteUserResume(Long id) {
        StoredProcedureQuery specialist = em.createNamedStoredProcedureQuery("deleteUserResume");
        specialist.setParameter("p_id", id);
        specialist.execute();
    }
}
