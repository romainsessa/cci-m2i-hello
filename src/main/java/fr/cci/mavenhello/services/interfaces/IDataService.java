package fr.cci.mavenhello.services.interfaces;

import java.util.List;

import fr.cci.mavenhello.model.DataModel;

public interface IDataService {
	
	public DataModel getById(int id);
	
	public List<DataModel> getAll();
	
	public DataModel save(DataModel model);
	
	public boolean deleteById(int id);

}
