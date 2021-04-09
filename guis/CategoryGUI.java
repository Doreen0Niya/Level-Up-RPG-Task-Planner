package guis;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import activity.Init;
import entity.Category;
import entity.Subject;
import entity.Task;
import utinity.Search;

import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class CategoryGUI extends JFrame{
	private JTextField textField;
	private JTextField description;
	private JTextField subjectName;
	private JTextField cName;
	public CategoryGUI() throws Exception {
		getContentPane().setLayout(null);
		
		Label label = new Label("Subject Description");
		label.setBounds(107, 359, 127, 24);
		getContentPane().add(label);
		
		Label label_1 = new Label("Create A Subject");
		label_1.setBounds(638, 20, 183, 24);
		getContentPane().add(label_1);
		
		JComboBox cCategory = new JComboBox();
		cCategory.setEditable(true);
		cCategory.setBounds(799, 50, 270, 24);
		getContentPane().add(cCategory);
		for(Category c : Init.allCategory) {
			cCategory.addItem(c);
		}
		
		Label label_2 = new Label("Choose A Category");
		label_2.setBounds(648, 50, 127, 24);
		getContentPane().add(label_2);
		
		JLabel lblNewLabel = new JLabel("<html>Type the Name<br> of the Subject<html>");
		lblNewLabel.setBounds(674, 104, 101, 35);
		getContentPane().add(lblNewLabel);
		
		JLabel lbltypeTheDescription = new JLabel("<html>Type the Description<br> of the Subject<html>");
		lbltypeTheDescription.setBounds(674, 191, 101, 54);
		getContentPane().add(lbltypeTheDescription);
		
		JTextField sName = new JTextField();
		sName.setBounds(799, 115, 270, 24);
		getContentPane().add(sName);
		sName.setColumns(10);
		
		JTextArea showDescription = new JTextArea();
		showDescription.setEditable(false);
		showDescription.setBounds(107, 401, 460, 210);
		getContentPane().add(showDescription);
	
		JTree tree = new JTree();
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Categories");
		for(Category c : Init.allCategory) {
			DefaultMutableTreeNode n = new DefaultMutableTreeNode(c.getCategoryName());
			ArrayList<Subject> subjectList = Search.searchTaskListForCategory(c);
			if(subjectList.size()!=0) {
				for(Subject s:subjectList) {
					n.add(new DefaultMutableTreeNode(s));
				}
			}else {
				System.out.println("There is no task for such category " + c.getCategoryName());
			}
			top.add(n);
		}
		tree.setModel(new DefaultTreeModel(top));
		tree.addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		                           tree.getLastSelectedPathComponent();
		        
		        if (node == null) return;

		        Object nodeInfo = node.getUserObject();
		        //solve the problem of a category leaf
		        if (node.isLeaf()&&nodeInfo.getClass()==Subject.class) {
		            Subject sub = (Subject)nodeInfo;
		            String t = "Name: " + sub.getSubjectName() + 
		            		"\nCategory Name: " + sub.getSubjectCategory().getCategoryName() +		
		            		"\nSubject Description :" + sub.getSubjectDescription()+
		            		"\nCategory Description: " + sub.getSubjectCategory().getCategoryDescription() ;
		            showDescription.setText(t);
		        } else {
		            showDescription.setText("");
		        }
		    }
		});
		tree.setBounds(48, 50, 519, 275);
		getContentPane().add(tree);
		
		JTextArea sDescription = new JTextArea();
		sDescription.setBounds(799, 191, 270, 134);
		getContentPane().add(sDescription);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Type the Name<br> of the Category<html>");
		lblNewLabel_1.setBounds(674, 403, 88, 35);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<html>Type the Description<br> of the Category<html>");
		lblNewLabel_2.setBounds(674, 481, 88, 48);
		getContentPane().add(lblNewLabel_2);
		
		cName = new JTextField();
		cName.setColumns(10);
		cName.setBounds(799, 403, 270, 24);
		getContentPane().add(cName);
		
		JTextArea cDescription = new JTextArea();
		cDescription.setBounds(799, 478, 270, 134);
		getContentPane().add(cDescription);
		
		JButton createSubject = new JButton("Create");
		createSubject.setBounds(960, 338, 97, 25);
		createSubject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Subject s = new Subject(sName.getText(),Init.allCategory.get(cCategory.getSelectedIndex()));
					s.setSubjectDescription(sDescription.getText());
					Init.allSubject.add(s);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTree(tree);
			}
		});
		getContentPane().add(createSubject);
		
		JButton createCategory = new JButton("Create");
		createCategory.setBounds(960, 625, 97, 25);
		createCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Category c = new Category(cName.getText());
					c.setCategoryDescription(cDescription.getText());
					Init.allCategory.add(c);
					cCategory.addItem(c);
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTree(tree);
			}
		});
		getContentPane().add(createCategory);
		
		JLabel lblNewLabel_3 = new JLabel("Create A Category");
		lblNewLabel_3.setBounds(638, 367, 124, 16);
		getContentPane().add(lblNewLabel_3);

	}
	
	public void refreshTree(JTree tree) {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode top = (DefaultMutableTreeNode)model.getRoot();
		top.removeAllChildren();
		for(Category c : Init.allCategory) {
			DefaultMutableTreeNode n = new DefaultMutableTreeNode(c.getCategoryName());
			ArrayList<Subject> subjectList = Search.searchTaskListForCategory(c);
			if(subjectList.size()!=0) {
				for(Subject s:subjectList) {
					n.add(new DefaultMutableTreeNode(s));
				}
			}else {
				System.out.println("There is no task for such category " + c.getCategoryName());
			}
			top.add(n);
		}
		model.reload();
	}
	
	public void refreshChoice(Choice c, Category cat) {
		c.add(cat.getCategoryName());
		c.repaint();
	}
}
