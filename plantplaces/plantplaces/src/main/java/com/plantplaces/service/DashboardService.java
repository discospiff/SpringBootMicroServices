package com.plantplaces.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plantplaces.dao.IDashboardDAO;

@Service
public class DashboardService implements IDashboardService {
	
	@Autowired
	private IDashboardDAO dashboardDAO;
		
	@Override
	public Set<String> getUnprocessedPhotos() {
		Set<String> intersection = new HashSet<String>(dashboardDAO.getPhotoIn());
		intersection.removeAll(dashboardDAO.getPhotoOut());
		return intersection;
	}

	@Override
	public Set<String> getProcessedPhotos() {
		Set<String> intersection = new HashSet<String>(dashboardDAO.getPhotoIn());
		intersection.retainAll(dashboardDAO.getPhotoOut());
		return intersection;
	}

	@Override
	public Set<String> getExceptions() {
		return dashboardDAO.getPhotoException();
	}

	
}
