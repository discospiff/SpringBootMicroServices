package com.plantplaces.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.plantplaces.dto.SpecimenDTO;

public interface SpecimenRepository extends CrudRepository<SpecimenDTO, Integer> {
	List<SpecimenDTO> findByPlantId(int plantId);
}
