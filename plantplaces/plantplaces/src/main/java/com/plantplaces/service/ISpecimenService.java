package com.plantplaces.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.plantplaces.dao.ISpecimenDAO;
import com.plantplaces.dto.PhotoDTO;
import com.plantplaces.dto.PlantDTO;
import com.plantplaces.dto.SpecimenDTO;

/**
 * CRUD operations for specimens
 * @author Administrator
 *
 */
public interface ISpecimenService {

	/**
	 * Get specimens from persistence layer.
	 * @param id a unique lookup
	 * @return a specimen with a matching ID.
	 */
	SpecimenDTO fetchById(int id);

	/**
	 * Persist the given DTO
	 * @param specimenDTO
	 */
	boolean save(SpecimenDTO specimenDTO) throws Exception;

	/**
	 * Return a list of plants that contain this String.
	 * @param string the search criteria: can be genus, species, cultivar, or common
	 * @return a list of matching plants.
	 */
	List<PlantDTO> fetchPlants(String string) throws Exception;

	void setSpecimenDAO(ISpecimenDAO specimenDAO);

	ISpecimenDAO getSpecimenDAO();

	Iterable<SpecimenDTO> fetchAllSpecimens() throws Exception;

	void saveImage(MultipartFile imageFile, PhotoDTO photoDTO) throws Exception;

	
}