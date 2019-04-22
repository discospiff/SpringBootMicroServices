package com.plantplaces.dao;

import java.util.Set;

public interface IDashboardDAO {

	Set<String> getPhotoIn();

	void setPhotoIn(Set<String> photoIn);

	Set<String> getPhotoException();

	void setPhotoException(Set<String> photoException);

}