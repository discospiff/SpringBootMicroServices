package com.plantplaces.service;

import java.util.Set;

public interface IDashboardService {

	Set<String> getExceptions();

	Set<String> getProcessedPhotos();

	Set<String> getUnprocessedPhotos();

}