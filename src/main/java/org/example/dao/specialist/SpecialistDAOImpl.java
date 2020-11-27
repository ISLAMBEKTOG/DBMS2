package org.example.dao.specialist;

import org.example.model.Specialists;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class SpecialistDAOImpl {
    @PersistenceContext
    private EntityManager em;

    public Iterable<Specialists> findAll() {
        StoredProcedureQuery specialists = em.createNamedStoredProcedureQuery("findAllSpecialists");
        return (List<Specialists>) specialists.getResultList();
    }

    public List<Specialists> findSpecialistsByCategoriesId(Long first, Long second, Pageable pageable) {
        StoredProcedureQuery specialists = em.createNamedStoredProcedureQuery("findSpecialistsByCategoriesId");
        specialists.setParameter(2, first);
        specialists.setParameter(3, second);

        return (List<Specialists>) specialists.getResultList();
    }

    public Integer getCountOfAllSpecialistsByCategoryId(Long id) {
        StoredProcedureQuery specialists = em.createNamedStoredProcedureQuery("getCountOfAllSpecialistsByCategoryId");
        specialists.setParameter("catId", id);
        specialists.execute();
        return (Integer) specialists.getOutputParameterValue("result");
    }

    public List<Specialists> findSpecialistsByUserId(Long id) {
        StoredProcedureQuery specialists = em.createNamedStoredProcedureQuery("findSpecialistsByUserId");
        specialists.setParameter(2, id);

        return (List<Specialists>) specialists.getResultList();

    }

    public List<Specialists> getAllSpecialistFromUserResumeByUserId(Long id) {
        StoredProcedureQuery specialists = em.createNamedStoredProcedureQuery("getAllSpecialistFromUserResumeByUserId");
        specialists.setParameter(2, id);

        return (List<Specialists>) specialists.getResultList();
    }

    public Specialists findSpecialistsById(Long id) {
        StoredProcedureQuery specialists = em.createNamedStoredProcedureQuery("findSpecialistsById");
        specialists.setParameter(2, id);

        return (Specialists) specialists.getSingleResult();
    }

    public Specialists addSpecialist(Specialists specialists) {
        StoredProcedureQuery specialist = em.createNamedStoredProcedureQuery("addSpecialist");
        specialist.setParameter("p_content", specialists.getContent());
        specialist.setParameter("p_created_date", specialists.getDate());
        specialist.setParameter("p_image", specialists.getImage());
        specialist.setParameter("p_price", specialists.getPrice());
        specialist.setParameter("p_title", specialists.getTitle());
        specialist.setParameter("p_category_id", specialists.getCategory().getId());
        specialist.setParameter("p_city_id", specialists.getCity().getId());
        specialist.execute();

        return (Specialists) specialist.getSingleResult();
    }

    public void deleteSpecialist(Long id) {
        StoredProcedureQuery specialist = em.createNamedStoredProcedureQuery("deleteSpecialist");
        specialist.setParameter("p_id", id);
        specialist.execute();
    }
}
