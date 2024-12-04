package fr.cci.mavenhello.repositories.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlManager {
	
	private Connection connection;
	
	public Connection getConnection() {
		return connection;
	}
	
	private static MysqlManager instance;
	
	public static MysqlManager getInstance() {
		if(instance == null) {
			instance = new MysqlManager();
		}
		return instance;
	}
	
	private MysqlManager() {
		String url = "jdbc:mysql://localhost:3306/hellocci";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, "root", "root");
		} catch (SQLException e) {
			System.out.println("Problème de connexion à la BDD");
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Problème de chargement du driver BDD");
			System.out.println(e.getMessage());
		}
		
	}	

}
