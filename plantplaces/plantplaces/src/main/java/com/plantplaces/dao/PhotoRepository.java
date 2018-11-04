package com.plantplaces.dao;

import org.springframework.data.repository.CrudRepository;

import com.plantplaces.dto.PhotoDTO;

public interface PhotoRepository extends CrudRepository<PhotoDTO, Integer> {
	
}
