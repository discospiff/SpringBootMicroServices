package com.plantplaces.dao;

import org.springframework.data.repository.CrudRepository;

import com.plantplaces.dto.SpecimenDTO;

public interface SpecimenRepository extends CrudRepository<SpecimenDTO, Integer> {

}
