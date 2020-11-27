package org.example.dao.city;

import org.example.model.City;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class CityDAOImpl {
    @PersistenceContext
    private EntityManager em;

    public Iterable<City> findAll() {
        TypedQuery<City> q = em.createNamedQuery("getAllCities", City.class);
        return q.getResultList();
    }

    public Optional<City> findByName(String s) {
        TypedQuery<City> q = em.createNamedQuery("findCityByName", City.class);
        q.setParameter("s", s);
        return Optional.of(q.getSingleResult());
    }
}
