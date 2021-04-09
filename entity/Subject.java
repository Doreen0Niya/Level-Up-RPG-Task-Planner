package entity;

import activity.Init;

//different kind of subject set by User
public class Subject {
	private String name;
	private Category category;
	private String description = "...";
	
	public Subject (String name,Category category) throws Exception {	
		this.setSubjectName(name);
		this.setSubjectCategory(category);
	}
	
	//get and set method, format get/set+Subject+Name/Category/Description
	public void setSubjectDescription(String description) throws Exception {
		if(description.contains("&,")||description.contains("&;")) {
			System.out.println("Please do not use &, or &; they are sensitive words used in the programming T^T");
			throw new Exception();
		}
		this.description = description;
	}
	
	public String getSubjectDescription() {
		return description;
	}

	public Category getSubjectCategory() {
		return category;
	}

	public void setSubjectCategory(Category category) {
		this.category = category;
	}

	public String getSubjectName() {
		return name;
	}

	public void setSubjectName(String name) throws Exception {//if the name is empty
		if(name.isEmpty()) {
			System.out.println("the name of subject can not be empty");
			throw new Exception();
		}
		//avoid RandW & Init errors, the two are split strings
		if(name.contains("&,")||name.contains("&;")) {
			System.out.println("Please do not use &, or &; they are sensitive words used in the programming T^T");
			throw new Exception();
		}

		//avoid same name subject
		for(Subject s:Init.allSubject) {
			if(s.getSubjectName()==name) {
				System.out.println("the name of subject can not be same to another subject");
				throw new Exception();
			}
		}
		
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.getSubjectName();
	}
}
