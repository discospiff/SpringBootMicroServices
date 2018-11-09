package com.plantplaces.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.plantplaces.dto.PhotoDTO;

@Component
public class PhotoDAO implements IPhotoDAO {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IPhotoDAO#savePhotoImage(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public void savePhotoImage(PhotoDTO photoDTO, MultipartFile imageFile) throws Exception {
		// this gets us to src/main/resources without knowing the full path (hardcoding)
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		photoDTO.setPath(absolutePath + "/src/main/resources/static/photos/");
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(photoDTO.getPath() + imageFile.getOriginalFilename());
		Files.write(path, bytes);
		jmsTemplate.convertAndSend("photos", path.toString());
		
	}
	
	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IPhotoDAO#save(com.plantplaces.dto.PhotoDTO)
	 */
	@Override
	public void save(PhotoDTO photoDTO) {
		photoRepository.save(photoDTO);
	}
}
