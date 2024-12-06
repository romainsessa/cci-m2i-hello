package fr.cci.mavenhello.repositories.interfaces;

import java.util.List;

import fr.cci.mavenhello.repositories.entities.DataEntity;

public interface IDataRepository {
	
	public DataEntity findById(int id);
	
	public List<DataEntity> findAll();
	
	public DataEntity upSert(DataEntity dataEntity);
	
	public int deleteById(int id);

}
