package org.example.service.city;

import org.example.dao.city.CityDAOImpl;
import org.example.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityDAOImpl cityDAOImpl;

    @Autowired
    public CityServiceImpl(CityDAOImpl cityDAOImpl) {
        this.cityDAOImpl = cityDAOImpl;
    }

    @Override
    public List<City> getAllCities() {
        return (List<City>) cityDAOImpl.findAll();
    }

    @Override
    public City getCityByName(String name) {
        return cityDAOImpl.findByName(name).isPresent()
                ? cityDAOImpl.findByName(name).get() : null;
    }
}
