package com.plantplaces.dao;

import org.springframework.web.multipart.MultipartFile;

import com.plantplaces.dto.PhotoDTO;

public interface IPhotoDAO {

	void savePhotoImage(MultipartFile imageFile) throws Exception;

	void save(PhotoDTO photoDTO);

}