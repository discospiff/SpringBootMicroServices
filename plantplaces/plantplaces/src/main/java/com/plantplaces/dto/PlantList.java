package com.plantplaces.dto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlantList {
	@SerializedName("plants")
	@Expose
	private List<PlantDTO> plants = null;

	public List<PlantDTO> getPlants() {
	return plants;
	}

	public void setPlants(List<PlantDTO> plants) {
	this.plants = plants;
	}
}
