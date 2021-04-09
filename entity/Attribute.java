package entity;

public class Attribute {
	private String name;
	
	public Attribute (String name) throws Exception {
		if(name.isEmpty()) {
			System.out.println("the name of attribute can not be empty");
			throw new Exception();
		}
		this.name = name;
	}
}
