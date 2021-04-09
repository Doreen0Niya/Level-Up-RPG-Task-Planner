package entity;

import utinity.Search;

//Categories of subjects
public class Category {

	private String name;
	private String description = "...";
	
	public Category(String name) throws Exception{
		this.setCategoryName(name);
	}
	
	
	//get and set method, format get/set+Category+Name/Description
	public void setCategoryDescription(String description) throws Exception {
		if(name.isEmpty()) {
			System.out.println("the name of subject can not be empty");
			throw new Exception();
		}
		//avoid RandW & Init errors, the two are split strings
		if(description.contains("&,")||description.contains("&;")) {
			System.out.println("Please do not use &, or &; they are sensitive words used in the programming T^T");
			throw new Exception();
		}
		this.description = description;
	}
	public String getCategoryDescription() {
		return description;
	}
	public String getCategoryName() {
		return name;
	}
	public void setCategoryName(String name) throws Exception {//if the name is empty
		if(name.isEmpty()) {
			System.out.println("the name of category can not be empty");
			throw new Exception();
		}
		//avoid RandW & Init errors, the two are split strings
		if(name.contains("&,")||name.contains("&;")) {
			System.out.println("Please do not use &, or &; they are sensitive words used in the programming T^T");
			throw new Exception();
		}
		//if the name already exist
		if(Search.searchCategory(name)!=-1) {
			System.out.println("the name of category can not be same to another subject");
			throw new Exception();
		}
		this.name = name;
	}
}
