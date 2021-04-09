package guis;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Canvas;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MainGUI extends JFrame{
	public MainGUI() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		ImageIcon background = new ImageIcon("img/background.jpg");
		JLabel backgroundLabel = new JLabel(new ImageIcon("E:\\basicCodeWorkplace\\Eclipse-regularWorkspace\\LifeRPG\\img\\background.jpg"));
		backgroundLabel.setBounds(0, 0, 1249, 683);
		getContentPane().add(backgroundLabel);
		
		JButton categoryButton = new JButton("New button");
		categoryButton.setBounds(316, 184, 153, 187);
		categoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Init Category");
		    	JFrame gui;
				try {
					gui = new CategoryGUI();
					gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gui.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		} 
			
		});
		getContentPane().add(categoryButton);
		
		JButton subjectButton = new JButton("New button");
		subjectButton.setBounds(560, 157, 159, 146);
		subjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Init current task page");
		    	JFrame gui;
				try {
					gui = new TaskGUI();
					gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gui.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		} 
			
		});
		getContentPane().add(subjectButton);
		
		JButton currentTask = new JButton("New button");
		currentTask.setBounds(827, 215, 153, 153);
		getContentPane().add(currentTask);
	}

}
