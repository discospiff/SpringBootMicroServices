package com.plantplaces.dao;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
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
		
		String rawJson = networkDAO.request("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Oak");
		
		JSONObject root = new JSONObject(rawJson);
		
		JSONArray plants = root.getJSONArray("plants");
		
		for(int i = 0; i < plants.length(); i++) {
			// the JSON data
			JSONObject jsonPlant = plants.getJSONObject(i);
			// Plant object that we will populate from JSON data.
			PlantDTO plant = new PlantDTO();
			int guid = jsonPlant.getInt("id");
			String genus = jsonPlant.getString("genus");
			String species = jsonPlant.getString("species");
			String cultivar = jsonPlant.getString("cultivar");
			String common = jsonPlant.getString("common");
			
			// populate our DTO with this information,.
			plant.setGuid(guid);
			plant.setGenus(genus);
			plant.setCommon(common);
			plant.setCultivar(cultivar);
			plant.setSpecies(species);
			
			// add the populated plant to our collection.
			allPlants.add(plant);
			
		}
		
		return allPlants;
	}

}
