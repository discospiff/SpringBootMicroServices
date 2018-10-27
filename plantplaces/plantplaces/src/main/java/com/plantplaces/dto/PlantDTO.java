package com.plantplaces.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlantDTO {
	@SerializedName("id")
	@Expose
	private int guid;
	@SerializedName("genus")
	@Expose
	private String genus;
	@SerializedName("species")
	@Expose
	private String species;
	@SerializedName("cultivar")
	@Expose
	private String cultivar;
	@SerializedName("common")
	@Expose
	private String common;
	
	public int getGuid() {
		return guid;
	}
	public void setGuid(int guid) {
		this.guid = guid;
	}
	public String getGenus() {
		return genus;
	}
	public void setGenus(String genus) {
		this.genus = genus;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getCultivar() {
		return cultivar;
	}
	public void setCultivar(String cultivar) {
		this.cultivar = cultivar;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return genus + " " + species + " " + cultivar + " " + common;
	}
}
