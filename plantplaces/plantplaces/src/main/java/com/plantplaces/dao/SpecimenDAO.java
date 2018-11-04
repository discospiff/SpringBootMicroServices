package com.plantplaces.dao;

import com.plantplaces.dto.SpecimenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecimenDAO implements ISpecimenDAO {

	@Autowired
	SpecimenRepository specimenRepository;
	
	@Override
	public boolean save(SpecimenDTO specimenDTO) throws Exception {
		// TODO Auto-generated method stub
		SpecimenDTO savedSpecimen = specimenRepository.save(specimenDTO);
		specimenDTO = savedSpecimen;
		return false;
	}
	
	@Override
	public Iterable<SpecimenDTO> fetchAll() throws Exception {
		return specimenRepository.findAll();
	}

}
