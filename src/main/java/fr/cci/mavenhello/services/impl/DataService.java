package fr.cci.mavenhello.services.impl;

import java.util.ArrayList;
import java.util.List;

import fr.cci.mavenhello.model.DataModel;
import fr.cci.mavenhello.repositories.entities.DataEntity;
import fr.cci.mavenhello.repositories.interfaces.IDataRepository;
import fr.cci.mavenhello.services.interfaces.IDataService;

public class DataService implements IDataService {

	private IDataRepository dataRepository;

	public DataService(final IDataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@Override
	public DataModel getById(int id) {
		DataEntity dataEntity = this.dataRepository.findById(id);
		return toModel(dataEntity);
	}

	@Override
	public List<DataModel> getAll() {
		List<DataEntity> dataEntities = this.dataRepository.findAll();
		List<DataModel> dataModels = new ArrayList<DataModel>();
		dataEntities.forEach(dataEntity -> dataModels.add(toModel(dataEntity)));
		return dataModels;
	}

	@Override
	public DataModel save(DataModel model) {
		DataEntity dataEntity = this.dataRepository.upSert(toEntity(model));
		if (dataEntity != null) {
			return toModel(dataEntity);
		}
		return null;
	}
	
	@Override
	public boolean deleteById(int id) {
		int result = this.dataRepository.deleteById(id);
		if(result == 1) {
			return true;
		}		
		return false;
	}

	public DataModel toModel(DataEntity dataEntity) {
		DataModel dataModel = new DataModel();
		dataModel.setId(dataEntity.getId());
		dataModel.setLabel(dataEntity.getLabel());
		return dataModel;
	}

	private DataEntity toEntity(DataModel dataModel) {
		DataEntity dataEntity = new DataEntity();
		dataEntity.setId(dataModel.getId());
		dataEntity.setLabel(dataModel.getLabel());
		return dataEntity;
	}

	

}
