package com.plantplaces.dao;

import com.plantplaces.dto.PlantList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetPlants {
	
	@GET("/perl/mobile/viewplantsjson.pl")
	Call<PlantList> getAllPlants(@Query("Combined_Name") String combinedName);

}
