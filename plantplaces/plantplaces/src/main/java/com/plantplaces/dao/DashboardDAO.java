package com.plantplaces.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DashboardDAO implements IDashboardDAO {

	private Set<String> photoOut = new HashSet<String>();
	private Set<String> photoIn = new HashSet<String>();
	private Set<String> photoException = new HashSet<String>();
	
	@KafkaListener(topics="photoOut", groupId="plantplaces")
	public void processPhotoOut(String path) {
		getPhotoOut().add(path);
	}
	
	@KafkaListener(topics="photoIn", groupId="plantplaces")
	public void processPhotoIn(String path) {
		photoIn.add(path);
	}
	
	@KafkaListener(topics="photoException", groupId="plantplaces")
	public void processPhotoException(String path) {
		photoException.add(path);
	}

	@Override
	public Set<String> getPhotoOut() {
		return photoOut;
	}

	@Override
	public void setPhotoOut(Set<String> photoOut) {
		this.photoOut = photoOut;
	}
	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IDashboardDAO#getPhotoIn()
	 */
	@Override
	public Set<String> getPhotoIn() {
		return photoIn;
	}

	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IDashboardDAO#setPhotoIn(java.util.Set)
	 */
	@Override
	public void setPhotoIn(Set<String> photoIn) {
		this.photoIn = photoIn;
	}

	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IDashboardDAO#getPhotoException()
	 */
	@Override
	public Set<String> getPhotoException() {
		return photoException;
	}

	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IDashboardDAO#setPhotoException(java.util.Set)
	 */
	@Override
	public void setPhotoException(Set<String> photoException) {
		this.photoException = photoException;
	}


}
