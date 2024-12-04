package fr.cci.mavenhello.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cci.mavenhello.repositories.entities.DataEntity;
import fr.cci.mavenhello.repositories.interfaces.IDataRepository;

public class DataRepository implements IDataRepository {

	private Connection connection;

	public DataRepository(final Connection connection) {
		this.connection = connection;
	}

	public DataEntity findById(final int id) {
		String query = "SELECT * FROM data WHERE id=?";
		try {
			PreparedStatement stat = connection.prepareStatement(query);
			stat.setInt(1, id);
			ResultSet res = stat.executeQuery();
			res.next();
			DataEntity dataEntity = new DataEntity(id, res.getString(2));
			return dataEntity;
		} catch (SQLException e) {
			System.out.println("Erreur SQL pour " + query);
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<DataEntity> findAll() {
		List<DataEntity> dataEntities = new ArrayList<DataEntity>();
		String query = "SELECT * FROM data";
		try {
			PreparedStatement stat = connection.prepareStatement(query);
			ResultSet res = stat.executeQuery();
			while (res.next()) {
				DataEntity dataEntity = new DataEntity(res.getInt(1), res.getString(2));
				dataEntities.add(dataEntity);
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL pour " + query);
			System.out.println(e.getMessage());
		}
		return dataEntities;
	}

	public DataEntity upSert(final DataEntity dataEntity) {
		String query = "";
		PreparedStatement stat;
		try {
			if (dataEntity.getId() == 0) { // create
				query = "INSERT INTO data(label) VALUE(?)";
				stat = connection.prepareStatement(query);
				stat.setString(1, dataEntity.getLabel());

			} else { // update
				query = "UPDATE data SET label=? WHERE id=?";
				stat = connection.prepareStatement(query);
				stat.setString(1, dataEntity.getLabel());
				stat.setInt(2, dataEntity.getId());
			}
			int res = stat.executeUpdate();
			if (res == 1) {
				return dataEntity;
			} else {
				System.out.println("Probleme car aucune ligne affectée");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL pour " + query);
			System.out.println(e.getMessage());
		}
		return null;
	}
}
