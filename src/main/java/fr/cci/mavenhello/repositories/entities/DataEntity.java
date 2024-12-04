package fr.cci.mavenhello.repositories.entities;

public class DataEntity {
	
	private int id;
	private String label;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public DataEntity() {}
	
	public DataEntity(int id, String label) {
		super();
		this.id = id;
		this.label = label;
	}

	@Override
	public String toString() {
		return "DataEntity [id=" + id + ", label=" + label + "]";
	}

}
