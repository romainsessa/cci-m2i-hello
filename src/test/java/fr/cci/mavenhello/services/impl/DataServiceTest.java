package fr.cci.mavenhello.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.cci.mavenhello.model.DataModel;
import fr.cci.mavenhello.repositories.entities.DataEntity;
import fr.cci.mavenhello.repositories.impl.DataRepository;

@ExtendWith(MockitoExtension.class)
public class DataServiceTest {
	
	@Mock
	private DataRepository dataRepository;

	@Test
	public void testGetById() {		
		//Arrange
		int id = 1;
		String label = "fake";
		DataEntity dataEntityFake = new DataEntity(id, label);
		DataService dataService = new DataService(dataRepository);
		when(dataRepository.findById(id)).thenReturn(dataEntityFake);
		
		//Act
		DataModel dataModelResult = dataService.getById(id);
		
		//Assert
		verify(dataRepository).findById(id);
		assertTrue(dataModelResult instanceof DataModel);
		assertEquals(id, dataModelResult.getId());
		assertEquals(label, dataModelResult.getLabel());		
	}
	
	@Test
	public void testDeleteById() {		
		//Arrange
		int id = 1;
		DataService dataService = new DataService(dataRepository);
		when(dataRepository.deleteById(id)).thenReturn(1);
		//Act
		
		boolean result = dataService.deleteById(id);
		
		//Assert
		verify(dataRepository).deleteById(id);
		assertTrue(result);		
	}
	
	@Test
	public void testToModel() {		
		//Arrange
		int id = 1;
		String label = "test";
		DataEntity dataEntity = new DataEntity(id, label);
		DataService dataService = new DataService(null);
		
		//Act
		DataModel dataModel = dataService.toModel(dataEntity);
		
		//Assert
		assertEquals(id, dataModel.getId());
		assertEquals(label, dataModel.getLabel());		
	}
		
}
