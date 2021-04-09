package entity;

public class Loot {
	private String name;
	private String description = "...";
	
	public Loot (String name) throws Exception {
		if(name.isEmpty()) {
			System.out.println("the name of loot can not be empty");
			throw new Exception();
		}
		this.name = name;
	}
	
	public void setLootDescription(String description) {
		this.description = description;
	}
	
	public String getLootDescription() {
		return description;
	}
}
