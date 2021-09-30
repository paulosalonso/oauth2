package com.github.paulosalonso.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.paulosalonso.resourceserver.repository.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
