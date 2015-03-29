package fr.molecules;

public class UserData {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserData [id=" + id + "]";
	}

	public UserData(String id) {
		super();
		this.id = id;
	}
	
}
