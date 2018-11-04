package com.plantplaces.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="photos")
public class PhotoDTO {


	@Id
	@GeneratedValue
	private int photoId;
	private String photographer;
	private String path;
	private String fileName;
	private String comments;
	
	@ManyToOne
	@JoinColumn(name="SPECIMEN_ID")
	private SpecimenDTO specimenDTO;
	
	public String getPhotographer() {
		return photographer;
	}
	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public SpecimenDTO getSpecimenDTO() {
		return specimenDTO;
	}
	public void setSpecimenDTO(SpecimenDTO specimenDTO) {
		this.specimenDTO = specimenDTO;
	}
	
	
	
}
