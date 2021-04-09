package utinity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFrame;

import activity.Init;
import entity.Category;
import entity.Subject;
import entity.Task;
import guis.CategoryGUI;
import guis.MainGUI;
import guis.TaskGUI;

public class RandW {
	//write subject information to the file
	public static void writeCategory() {
		try {
		      File myObj = new File("src/data/Categories.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred when create Categories.txt.");
		      e.printStackTrace();
		    }
		try {
			OutputStream output = new FileOutputStream("src/data/Categories.txt",false);//no rewrite, start at the end of the file
			
			//data format
			for(Category c:Init.allCategory) {
				String content = c.getCategoryName()+"&,"+c.getCategoryDescription()+"&;";
				output.write(content.getBytes());
			}
				output.close();
				
		}catch(Exception e) {
			System.out.println("An error occurred when write from arraylist Init.all Category to Categories.txt");
			e.printStackTrace();
		}

	}
	
	public static String readCategory() throws IOException {
		String instring = "";
		try {
			InputStream input = new FileInputStream("src/data/Categories.txt");
			int in = input.read();
			while(in!=-1) {
				instring = instring + (char)in;
				in = input.read();
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		//in the end instring contain all text
		return instring;
	}
	
	//write subject information to the file
	public static void writeSubject() {
		try {
		      File myObj = new File("src/data/Subjects.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred when create Subjects.txt.");
		      e.printStackTrace();
		    }
		
		try {
			OutputStream output = new FileOutputStream("src/data/Subjects.txt",false);
			for(Subject s:Init.allSubject) {
				//data format
				String content = s.getSubjectName() + "&," + s.getSubjectCategory().getCategoryName()+"&,"+s.getSubjectDescription()+"&;";
				output.write(content.getBytes());
			}
			output.close();
		} catch (Exception e) {
			System.out.println("An error occurred when write from arraylist Init.allSubject to Subjects.txt");
			e.printStackTrace();
		}
	}
	
	public static String readSubject() throws IOException {
		String instring = "";
		try {
			InputStream input = new FileInputStream("src/data/Subjects.txt");
			int in = input.read();
			while(in!=-1) {
				instring = instring + (char)in;
				in = input.read();
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		//in the end instring contain all text
		return instring;
	}
	
	//write finished task recordings to the file
	public static void writeFinishedTask() {
		try {
		      File myObj = new File("src/data/FinishedTasks.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred when create FinishedTasks.txt.");
		      e.printStackTrace();
		    }
		
		try {
			OutputStream output = new FileOutputStream("src/data/FinishedTasks.txt",false);
			for(Task t:Init.finishedTask) {
				//data format
				String content = t.getSubject().getSubjectName()+"&;";
				output.write(content.getBytes());
			}
			output.close();
		} catch (Exception e) {
			System.out.println("An error occurred when write from arraylist Init.finishedTask to FinishedTasks.txt");
			e.printStackTrace();
		}
	}
	
	//read finished task recordings in the file
	public static String readFinishedTask() throws IOException {
		String instring = "";
		try {
			InputStream input = new FileInputStream("src/data/FinishedTasks.txt");
			int in = input.read();
			while(in!=-1) {
				instring = instring + (char)in;
				in = input.read();
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		//in the end instring contain all text
		return instring;
	}	
	
	//write current task recordings to the file
	public static void writeCurrentTask() {
		try {
		      File myObj = new File("src/data/CurrentTasks.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred when create CurrentTasks.txt.");
		      e.printStackTrace();
		    }
		
		try {
			OutputStream output = new FileOutputStream("src/data/CurrentTasks.txt",false);
			for(Task t:Init.currentTask) {
				//data format
				String content = t.getSubject().getSubjectName()+"&;";
				output.write(content.getBytes());
			}
			output.close();
		} catch (Exception e) {
			System.out.println("An error occurred when write from arraylist Init.CurrentTask to Tasks.txt");
			e.printStackTrace();
		}
	}
	
	//read Current task informations in the file
	public static String readCurrentTask() throws IOException {
		String instring = "";
		try {
			InputStream input = new FileInputStream("src/data/CurrentTasks.txt");
			int in = input.read();
			while(in!=-1) {
				instring = instring + (char)in;
				in = input.read();
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		//in the end instring contain all text
		return instring;
	}
	
	public static void main(String[] args) throws Exception {
		Category cat = new Category("category");
		Init.allCategory.add(cat);
		for(int i = 0; i < 10; i++) {
			Init.allSubject.add(new Subject("subject"+i, cat));
			Init.currentTask.add(new Task(new Subject("subject"+i, cat)));
		}

		JFrame gui = new MainGUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		
	}
}
