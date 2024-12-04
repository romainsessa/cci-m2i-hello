package fr.cci.mavenhello.model;

public class DataModel {
	
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
	
	public DataModel() {}
	
	public DataModel(int id, String label) {
		super();
		this.id = id;
		this.label = label;
	}

	@Override
	public String toString() {
		return "DataModel [id=" + id + ", label=" + label + "]";
	}

}
