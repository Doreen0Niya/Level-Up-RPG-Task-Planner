package utinity;

import java.util.ArrayList;

import activity.Init;
import entity.Category;
import entity.Subject;
import entity.Task;

public class Search {

	//if such cat exist, return the index in the Init.allCategory arraylist. If not, return -1;
	public static int searchCategory(String CategoryName) {
		for(int i = 0; i < Init.allCategory.size(); i++) {
			if(CategoryName == Init.allCategory.get(i).getCategoryName()) {
				return i;
			}
		}
		return -1;
	}
	
	//if such cat exist, return the index in the Init.allSubject arraylist. If not, return -1;
	public static int searchSubject(String SubjectName) {
		for(int i = 0; i < Init.allSubject.size(); i++) {
			if(SubjectName.equals(Init.allSubject.get(i).getSubjectName())) {
				return i;
			}
		}
		return -1;
	}

	public static int searchCurrentTask(Subject s, String deadline) {
		String subjectName = s.getSubjectName();
		for(int i = 0; i < Init.currentTask.size(); i++) {
			if(Init.currentTask.get(i).isSame(subjectName, deadline)) {
				return i;
			}
		}
		return -1;
	}
	
	public static ArrayList<Subject> searchTaskListForCategory(Category c){
		ArrayList<Subject> returnValue = new ArrayList<Subject>();
		for(Subject s: Init.allSubject) {
			if(s.getSubjectCategory().equals(c)) {
				returnValue.add(s);
			}
		}
		return returnValue;
	}
}
