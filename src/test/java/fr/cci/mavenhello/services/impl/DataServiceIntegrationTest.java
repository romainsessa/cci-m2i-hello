package fr.cci.mavenhello.services.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import fr.cci.mavenhello.repositories.db.MysqlManager;
import fr.cci.mavenhello.repositories.impl.DataRepository;

public class DataServiceIntegrationTest {
	
	@Test
	public void testDeleteByIdWithExistingLabel() {		
		//Arrange
		int id = 4;
		
		DataRepository dataRepository = new DataRepository(MysqlManager.getInstance().getConnection());
		DataService dataService = new DataService(dataRepository);
		
		//Act		
		boolean result = dataService.deleteById(id);
		
		//Assert
		assertTrue(result);		
	}
	
	@Test
	public void testDeleteByIdWithMissingLabel() {		
		//Arrange
		int id = 6;
		
		DataRepository dataRepository = new DataRepository(MysqlManager.getInstance().getConnection());
		DataService dataService = new DataService(dataRepository);
		
		//Act		
		boolean result = dataService.deleteById(id);
		
		//Assert
		assertFalse(result);		
	}
	

}
