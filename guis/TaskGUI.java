package guis;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import activity.Init;
import entity.Subject;
import entity.Task;
import utinity.MyDate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.text.ParseException;
import java.util.Date;

public class TaskGUI extends JFrame{
	private JTextField txtA;
	public TaskGUI() {
		getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Current Task");
		label1.setBounds(227, 57, 116, 25);
		getContentPane().add(label1);		
		
		JLabel label2 = new JLabel("Create A Task");
		label2.setBounds(693, 25, 99, 31);
		getContentPane().add(label2);
		
		JLabel month = new JLabel("Month");
		month.setBounds(520, 129, 56, 16);
		getContentPane().add(month);
		
		JLabel date = new JLabel("Date");
		date.setBounds(597, 129, 56, 16);
		getContentPane().add(date);
		
		JLabel year = new JLabel("Year");
		year.setBounds(674, 130, 56, 16);
		getContentPane().add(year);
		
		JLabel hour = new JLabel("Hour");
		hour.setBounds(751, 129, 56, 16);
		getContentPane().add(hour);
		
		JLabel minute = new JLabel("Minute");
		minute.setBounds(827, 130, 56, 16);
		getContentPane().add(minute);

//text info textarea
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(550, 253, 407, 165);
		getContentPane().add(textArea);
		
//left list		
		DefaultListModel<Task> listModel=new DefaultListModel<Task>();
		for(Task t : Init.currentTask) {
			listModel.addElement(t);
		}
		JList<Task> list = new JList<Task>(listModel);	
		list.setBounds(81, 136, 422, 321);
		ListSelectionModel lsm = list.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = list.getSelectedIndex();
				if(index != -1) {
					Task t = (Task) list.getSelectedValue();
					String content = "Category Name: " + t.getSubject().getSubjectCategory().getCategoryName()
							+ "\nCategory Description: " + t.getSubject().getSubjectCategory().getCategoryDescription()
							+ "\nSubject Name: " + t.getSubject().getSubjectName()
							+ "\nSubject Description: " + t.getSubject().getSubjectDescription()
							+ "\nTask Deadline: " + t.getDeadline(); 
					textArea.setText(content);
				}else {
					System.out.println("Please Select a thing in the list");
				}
			}
		});
		getContentPane().add(list);
		
//right combobox
		DefaultComboBoxModel<Subject> comboboxModel = new DefaultComboBoxModel<Subject>();
		for(Subject s : Init.allSubject) {
			comboboxModel.addElement(s);
		}
		JComboBox<Subject> comboBox = new JComboBox<Subject>(comboboxModel);
		comboBox.setEditable(true);
		comboBox.setBounds(520, 57, 407, 25);
		getContentPane().add(comboBox);
		
//deadline combobox & textField
		txtA = new JTextField();
		Date now = new Date();
		String txtAtext = "month-day-year hour:minutes eg." + MyDate.userDateFormat.format(now);
		txtA.setText(txtAtext);
		txtA.setBounds(655, 92, 295, 25);
		getContentPane().add(txtA);
		txtA.setColumns(10);
		
		String[] monthes = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		JComboBox cMonth = new JComboBox(monthes);
		cMonth.setEditable(true);
		cMonth.setBounds(520, 149, 65, 25);
		getContentPane().add(cMonth);
		
		String[] dates = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		JComboBox cDate = new JComboBox(dates);
		cDate.setEditable(true);
		cDate.setBounds(597, 149, 65, 25);
		getContentPane().add(cDate);
		
		JComboBox cYear = new JComboBox();
		cYear.setEditable(true);
		cYear.setBounds(674, 149, 65, 25);
		getContentPane().add(cYear);
		
		JComboBox cHour = new JComboBox();
		cHour.setEditable(true);
		cHour.setBounds(751, 149, 65, 25);
		getContentPane().add(cHour);
		
		JComboBox cMinute = new JComboBox();
		cMinute.setEditable(true);
		cMinute.setBounds(827, 150, 65, 25);
		getContentPane().add(cMinute);
		
//create Task botton		
		JButton buttonCreate = new JButton("Create");
		buttonCreate.setBounds(943, 147, 76, 28);
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()!=-1) {
				Subject s = (Subject) comboboxModel.getSelectedItem();
				if(txtA.getText().isEmpty()) {
					Task t = new Task(s);
					Init.currentTask.add(t);
					return;
				}
				try {
					Date d = MyDate.userDateFormat.parse(txtA.getText());
					if(d.before(now)) {
						System.out.println("You can not set a deadliine prior to now");
						return;
					}
					Task t = new Task(s);
					t.setDeadline(MyDate.dateFormat.format(d));
					Init.currentTask.add(t);
				} catch (ParseException e1) {
					System.out.println("Please put in a right date and time");
					e1.printStackTrace();
					return;
				}
			}
		} 
			
		});
		getContentPane().add(buttonCreate);

//refresh left list botton
		JButton buttonRefresh = new JButton("Refresh");
		buttonRefresh.setBounds(98, 57, 97, 25);
		buttonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				for(Task t : Init.currentTask) {
					listModel.addElement(t);
				}
			}
		}
	);
		getContentPane().add(buttonRefresh);
		
		JLabel label3 = new JLabel("Get Task Information");
		label3.setBounds(550, 224, 131, 16);
		getContentPane().add(label3);

//finish task button
		JButton finishTaskButton = new JButton("Finish This Task");
		finishTaskButton.setBounds(359, 98, 131, 25);
		finishTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if(index != -1) {
					Task t = (Task) list.getSelectedValue();
					if(Init.currentTask.contains(t)) {
						Init.currentTask.remove(t);
						listModel.remove(index);
					}
				}else {
					System.out.println("Please Select a thing in the list");
				}
			}
		});
		getContentPane().add(finishTaskButton);
		
		JLabel lblNewLabel = new JLabel("Specify the Deadline");
		lblNewLabel.setBounds(530, 95, 123, 16);
		getContentPane().add(lblNewLabel);
	}
}
