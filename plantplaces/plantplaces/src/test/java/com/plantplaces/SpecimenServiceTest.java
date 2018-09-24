package com.plantplaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.plantplaces.dao.ISpecimenDAO;
import com.plantplaces.dto.PlantDTO;
import com.plantplaces.dto.SpecimenDTO;
import com.plantplaces.service.ISpecimenService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SpecimenServiceTest {
	
	@Autowired
	ISpecimenService specimenService;
	List<PlantDTO> plants;
	private SpecimenDTO specimen;
	
	@MockBean
	private ISpecimenDAO specimenDAO;
	
	@Before
	public void setup() throws Exception {
		SpecimenDTO specimen = new SpecimenDTO();
		specimen.setDescription("A beautiful Redbud I planted myself");
		specimen.setSpecimenId(83);
		Mockito.when(specimenDAO.save(specimen)).thenReturn(true);
		specimenService.setSpecimenDAO(specimenDAO);
	}
	
	@Test
	public void saveSpecimen_whenRedbudEntered() {
		givenUserIsLoggedInToMyPlantDiary();
		whenUserSearchesForEasternRedbud();
		whenUserAddsTextDetails();
		thenSpecimenIsSaved();
	}
	
	private void whenUserSearchesForEasternRedbud() {
		plants = specimenService.fetchPlants("Eastern Redbud");
	}

	private void whenUserAddsTextDetails() {
		specimen = new SpecimenDTO();
		PlantDTO plantDTO = plants.get(0);
		specimen.setPlantId(plantDTO.getGuid());
		specimen.setDescription("A beautiful Redbud I planted myself");
		specimen.setSpecimenId(83);
		
	}

	private void thenSpecimenIsSaved() {
		try {
			boolean result = specimenService.save(specimen);
			// if we have made it to this line, the test passes.
			assertTrue(result);
		} catch (Exception e) {
			// we should not get here if the test passes.
			fail();
		}
	}

	@Test
	public void fetchPlants_validateNoResultsForJunkData() {
		givenUserIsLoggedInToMyPlantDiary();
		whenTheUserSearchesForJunk();
		thenMyPlantDiaryReturnsZeroResults();
	}

	private void givenUserIsLoggedInToMyPlantDiary() {
		
	}
	

	private void whenTheUserSearchesForJunk() {
		 plants = specimenService.fetchPlants("kajsd;luaopuidfjo;aj;sd");
	}
	

	private void thenMyPlantDiaryReturnsZeroResults() {
		assertEquals("Number of plants returned", 0, plants.size());
		
	}
	

}
