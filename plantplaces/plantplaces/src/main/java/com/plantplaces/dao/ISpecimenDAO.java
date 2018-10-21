package com.plantplaces.dao;

import com.plantplaces.dto.SpecimenDTO;

public interface ISpecimenDAO {
	
	boolean save(SpecimenDTO specimenDTO) throws Exception;

	Iterable<SpecimenDTO> fetchAll() throws Exception;

}
