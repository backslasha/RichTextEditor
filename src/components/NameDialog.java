package components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NameDialog implements ActionListener {
	private JFrame mJFrame = new JFrame("请输入本文名字：");
	private JTextArea jTextArea;

	public NameDialog() {

		jTextArea = new JTextArea();
		jTextArea.setLineWrap(true);
		
		JPanel jPanel = new JPanel();
		jPanel.setSize(250,60);
		JButton jButton_cancle = new JButton("取消");
		JButton jButton_okay = new JButton("确定");
		
		jPanel.add(jButton_cancle);
		jPanel.add(jButton_okay);

		
		mJFrame.setLayout(new BorderLayout());
		mJFrame.add(jTextArea,BorderLayout.NORTH);
		mJFrame.add(jPanel,BorderLayout.SOUTH);
		
		mJFrame.setSize(250, 60);
		mJFrame.setLocation(800, 150);
		mJFrame.setVisible(true);
		mJFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

			}
		});
		mJFrame.pack();
	}
	
	public static void main(String[] args) {
		new NameDialog();
	}

	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getID()) {

		}
	}
}
