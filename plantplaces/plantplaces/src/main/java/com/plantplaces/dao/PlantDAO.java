package com.plantplaces.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plantplaces.dto.PlantDTO;

@Component
public class PlantDAO implements IPlantDAO {
	@Autowired
	NetworkDAO networkDAO;
	
	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IPlantDAO#fetch(java.lang.String)
	 */
	@Override
	public List<PlantDTO> fetch(String searchFilter) throws Exception {
		List<PlantDTO> allPlants = new ArrayList<PlantDTO>();
		
		networkDAO.request("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Oak");
		
		return allPlants;
	}

}
