package activity;

import java.io.IOException;
import java.util.ArrayList;
import entity.Category;
import entity.Subject;
import entity.Task;
import utinity.RandW;
import utinity.Search;

public class Init {
	public static ArrayList<Task> currentTask = new ArrayList<Task>();
	public static ArrayList<Task> finishedTask = new ArrayList<Task>();
	public static ArrayList<Subject> allSubject = new ArrayList<Subject>();
	public static ArrayList<Category> allCategory = new ArrayList<Category>();
	
	//allCategory must be initiated first before allsubject!!!!!!!!!!!!!!!!!
	public static void InitAllCategory() throws Exception {
		String[] SAllCategory = RandW.readCategory().split("&;");
		for(String SCategory : SAllCategory) {
			String[] category = SCategory.split("&,");
			//init category first
			Category c = new Category(category[0]);
			c.setCategoryDescription(category[1]);
			allCategory.add(c);
		}
	}
	
	public static void InitAllSubject() throws Exception {
		String[] SAllSubject = RandW.readSubject().split("&;");
		for(String SSubject : SAllSubject) {
			String[] subject = SSubject.split("&,");
			//init category first
			int catIndex = Search.searchCategory(subject[1]);
			if(catIndex == -1) {
				System.out.println("Data Is Corrupted, cannot find category of subject " + subject[1]);
				throw new Exception();
			}
			Category c = allCategory.get(catIndex);
			Subject s = new Subject(subject[0], c);
			s.setSubjectDescription(subject[2]);
			allSubject.add(s);
		}
	}
	
	public static void InitCurrentTask() throws Exception {
		String[] SCurrentTask = RandW.readCurrentTask().split("&;");
		for(String STask : SCurrentTask) {
			String[] task = STask.split("&,");
			int catIndex = Search.searchSubject(task[0]);
			if(catIndex == -1) {
				System.out.println("Data Is Corrupted for current task " + task[0]);
				throw new Exception();
			}
			Subject s = allSubject.get(catIndex);
			Task t = new Task (s);
			currentTask.add(t);
		}
	}
	
	public static void InitFinishedTask() throws Exception {
		String[] SFinishedTask = RandW.readFinishedTask().split("&;");
		
		if(SFinishedTask.length == 1 && SFinishedTask[0].equals("")) return;
		for(String STask : SFinishedTask) {
			String[] task = STask.split("&,");
			int catIndex = Search.searchSubject(task[0]);
			if(catIndex == -1) {
				System.out.print("Data Is Corrupted for finished task " + task[0]);
				throw new Exception();
			}
			Subject s = allSubject.get(catIndex);
			Task t = new Task (s);
			t.finishTask();
			finishedTask.add(t);
		}
	}

}
