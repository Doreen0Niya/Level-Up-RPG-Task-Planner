package activity;

import java.util.ArrayList;

import entity.Category;
import entity.Subject;
import entity.Task;

public class User {
	
	//methods have not been used
	
	public void createSubject(String name, Category category) {
		try {
			Subject s = new Subject(name, category);
			Init.allSubject.add(s);
		} catch (Exception e) {
			System.out.println("fail to create a subject");
			e.printStackTrace();
		}
	}

	public void createCategory(String name) {
		try {
			Category c = new Category(name);
			Init.allCategory.add(c);
		} catch (Exception e) {
			System.out.println("fail to create a category");
			e.printStackTrace();
		}
	}
	
	public void createTask(Subject subject) {
		Task t = new Task(subject);
		Init.currentTask.add(t);
	}
}
