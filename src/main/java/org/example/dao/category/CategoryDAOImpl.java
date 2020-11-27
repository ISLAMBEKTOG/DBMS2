package org.example.dao.category;

import org.example.model.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDAOImpl {
    @PersistenceContext
    private EntityManager em;

    public Iterable<Category> findAll() {
        TypedQuery<Category> q = em.createNamedQuery("getAllCategories", Category.class);
        return q.getResultList();
    }

    public Optional<Category> findByName(String s) {
        TypedQuery<Category> q = em.createNamedQuery("findCategoryByName", Category.class);
        q.setParameter("s", s);
        return Optional.of(q.getSingleResult());
    }

    public List<Category> getCategoriesByIds(Long first, Long second) {
        TypedQuery<Category> q = em.createNamedQuery("getCategoriesByIds", Category.class);
        q.setParameter("first", first);
        q.setParameter("second", second);
        return q.getResultList();
    }

    public List<Category> getAllCategoriesByUserId(Long id) {
        TypedQuery<Category> q = em.createNamedQuery("getAllCategoriesByUserId", Category.class);
        q.setParameter("user_id", id);
        return q.getResultList();
    }

}
