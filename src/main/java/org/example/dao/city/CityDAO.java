package org.example.dao.city;

import org.example.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDAO extends CrudRepository<City, String> {
}
