package com.plantplaces.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.plantplaces.dto.PhotoDTO;

@Component
public class PhotoDAO implements IPhotoDAO {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IPhotoDAO#savePhotoImage(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public void savePhotoImage(MultipartFile imageFile) throws Exception {
		String folder = "/photos/";
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(folder + imageFile.getOriginalFilename());
		Files.write(path, bytes);
		
	}
	
	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IPhotoDAO#save(com.plantplaces.dto.PhotoDTO)
	 */
	@Override
	public void save(PhotoDTO photoDTO) {
		photoRepository.save(photoDTO);
	}
}
