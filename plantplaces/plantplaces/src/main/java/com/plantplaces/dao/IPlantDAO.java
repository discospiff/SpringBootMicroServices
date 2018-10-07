package com.plantplaces.dao;

import java.util.List;

import com.plantplaces.dto.PlantDTO;

public interface IPlantDAO {

	List<PlantDTO> fetch(String searchFilter) throws Exception;

}