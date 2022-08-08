package com.plantplaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.plantplaces.dto.LabelValueDTO;
import com.plantplaces.dto.PhotoDTO;
import com.plantplaces.dto.PlantDTO;
import com.plantplaces.dto.SpecimenDTO;
import com.plantplaces.service.IDashboardService;
import com.plantplaces.service.ISpecimenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * the controller class doc
 */
@Controller
public class PlantPlacesController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ISpecimenService specimenService;

	@Autowired
	private IDashboardService dashboardService;
	
	private List<PlantDTO> allPlants;
	
	private String firstThreeCharacters;

	/**
	 * Add method doc for postmapping
	 * @param imageFile
	 * @param specimenDTO
	 * @return
	 */
	@PostMapping(value="/savespecimen")
	public ModelAndView saveSpecimen(@RequestParam("imageFile") MultipartFile imageFile, SpecimenDTO specimenDTO) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			specimenService.save(specimenDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("unable to save specimen", e);
			e.printStackTrace();
			modelAndView.setViewName("error");
			return modelAndView;
		}
		
		
		PhotoDTO photoDTO = new PhotoDTO();
		photoDTO.setFileName(imageFile.getOriginalFilename());
		photoDTO.setPath("/photo/");
		photoDTO.setSpecimenDTO(specimenDTO);
		modelAndView.setViewName("success");
		try {
			specimenService.saveImage(imageFile, photoDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error saving photo", e);
			modelAndView.setViewName("error");
			return modelAndView;
		}
		
		modelAndView.addObject("photoDTO", photoDTO);
		modelAndView.addObject("specimenDTO", specimenDTO);
		return modelAndView;
		
	}
	
	/**
	 * Handle the /start endpoint.
	 * @return
	 */
	@RequestMapping(value="/start", method=RequestMethod.GET, headers={"content-type=text/json"})
	@ResponseBody
	public SpecimenDTO readJSON(Model model) {
		SpecimenDTO specimenDTO = specimenService.fetchById(43);
		model.addAttribute("specimenDTO", specimenDTO);
		return specimenDTO;
		
	}


	/**
	 * added java doc for read method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String read(Model model) {
		log.info("User has entered the /start endpoint");
		model.addAttribute("specimenDTO", new SpecimenDTO());
		return "start";
	}
 
	@RequestMapping(value="/addspecimen", method=RequestMethod.GET)
	public String addSpecimen(Model model, @RequestParam(value="latitude", required=false, defaultValue="0.0") String latitude) {
		SpecimenDTO specimenDTO = specimenService.fetchById(43);
		specimenDTO.setLatitude(latitude); 
		model.addAttribute("specimenDTO", specimenDTO);
		return "start";
	}
	
	/**
	 * Handle the /start endpoint.
	 * @return
	 */
	@RequestMapping(value="/start", method=RequestMethod.GET, params= {"loyalty=blue"})
	public String readBlue() {
		return "start";
	}
	
	/**
	 * Handle the /start endpoint.
	 * @return
	 */
	@RequestMapping(value="/start", method=RequestMethod.GET, params= {"loyalty=silver"})
	public ModelAndView readSilver() {
		SpecimenDTO specimenDTO = specimenService.fetchById(43);
		specimenDTO.setSpecimenId(83);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("start");
		modelAndView.addObject("specimenDTO", specimenDTO);
		return modelAndView;
	}
	
	@PostMapping("/start")
	public String create() {
		return "start";
	}
	
	/**
	 * Handle the / endpoint
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "start";
	}
	
	@RequestMapping("/searchPlants")
	public ModelAndView searchPlants(@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm) {
		log.debug("entering search plants");
		ModelAndView modelAndView = new ModelAndView();
		List<PlantDTO> plants = new ArrayList<PlantDTO>(); 
		try {
			plants = specimenService.fetchPlants(searchTerm);
			modelAndView.setViewName("plantResults");
			if (plants.size() == 0 ) {
				log.warn("Received 0 results for search string: " + searchTerm);
			}
		} catch (Exception e) {
			log.error("Error happened in searchPlants endpoint", e);
			e.printStackTrace();
			modelAndView.setViewName("error");
		}
		modelAndView.addObject("plants", plants);
		log.debug("exiting search Plants");
		return modelAndView;
	}
	
	
	@RequestMapping("/searchPlantsAll")
	public String searchPlantsAll(@RequestParam Map<String,String> requestParams) {
		int params = requestParams.size();
		requestParams.get("searchTerm");
		return "start";
	}
	
	@RequestMapping("/sustainability")
	public String sustainability () {
		return "sustainability";
	}
	
	@RequestMapping("/showSpecimens")
	public ModelAndView showSpecimens() {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			Iterable<SpecimenDTO> allSpecimens = specimenService.fetchAllSpecimens();
			modelAndView.setViewName("showSpecimens");
			modelAndView.addObject("allSpecimens", allSpecimens);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Unable to retrieve specimens", e);
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/plantNamesAutocomplete")
	@ResponseBody
	public List<LabelValueDTO> plantNamesAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term)  {
		List<LabelValueDTO> suggestions = new ArrayList<LabelValueDTO>();
		try {
			// only update when term is three characters.
			if (term.length() == 3) {
				firstThreeCharacters = term;
				allPlants = specimenService.fetchPlants(term);
			}
			
			for (PlantDTO plantDTO : allPlants) {
				if (plantDTO.toString().contains(term)) {
					LabelValueDTO lv = new LabelValueDTO();
					lv.setLabel(plantDTO.toString());
					lv.setValue(Integer.toString(plantDTO.getGuid()));
					suggestions.add(lv);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Exception in autocomplete", e);
		}
		
		return suggestions;
		
	}
	
	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
		String returnValue = "start";
		
		PhotoDTO photoDTO = new PhotoDTO();
		photoDTO.setFileName(imageFile.getOriginalFilename());
		photoDTO.setPath("/photo/");
		
		try {
			specimenService.saveImage(imageFile, photoDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error saving photo", e);
			returnValue = "error";
		}
		
		return returnValue;
	}

	@RequestMapping("/showSpecimenDetails") 
	public ModelAndView showSpecimenDetails(@RequestParam("plant_ID") int plantId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("specimenDetails");
		List<SpecimenDTO> specimens = specimenService.fetchSpecimensByPlantId(plantId);
		modelAndView.addObject("specimens", specimens);
		return modelAndView;
	}
	
	/**
	 * Show the dashboard.
	 * @return
	 */
	@RequestMapping(value="/dashboard")
	public ModelAndView dashboard() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
		Set<String> exceptions = dashboardService.getExceptions();
		
		Set<String> processedPhotos = dashboardService.getProcessedPhotos();
		
		Set<String> unprocessedPhotos = dashboardService.getUnprocessedPhotos();
		
		modelAndView.addObject("exceptions", exceptions);
		modelAndView.addObject("processedPhotos", processedPhotos);
		modelAndView.addObject("unprocessedPhotos", unprocessedPhotos);
		return modelAndView;
	}
	
}
