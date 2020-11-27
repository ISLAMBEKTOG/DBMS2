package org.example.service.city;

import org.example.model.City;

import java.util.List;

public interface CityService {
    List<City> getAllCities();

    City getCityByName(String name);
}
