package org.example.dao.region;

import org.example.model.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionDAO extends CrudRepository<Region, String> {
    Optional<Region> findByName(String s);
}
