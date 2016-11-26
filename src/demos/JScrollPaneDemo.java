package demos;

import java.awt.Container;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import components.RowHeaderNumberView;



public class JScrollPaneDemo {
	public static void main(String[] args) {
		JFrame f = new JFrame("my second Swing Window");

		String iconPath = "E:\\CloudNote_01\\app\\src\\main\\res\\mipmap-hdpi\\ic_launcher.png";
		Icon icon = new ImageIcon(iconPath);
		Font font = new Font("ËÎÌו", Font.ITALIC + Font.BOLD, 128);
		
		Container container = f.getContentPane();
		
		JPanel jPanel_01 = new JPanel();
		JPanel jPanel_02 = new JPanel();
		
		JButton jButton = new JButton("Button");
		JLabel jLabel = new JLabel("Label");
		
		jPanel_01.add(jButton);
		jPanel_02.add(jLabel);
		
		JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		jTabbedPane.addTab("pic option", new ImageIcon(iconPath),jPanel_01,"tip");
		jTabbedPane.addTab("text option", jPanel_02);
		
		JScrollPane jScrollPane = new JScrollPane(jTabbedPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JTextArea j = new JTextArea();
		j.setColumns(1);
		j.setEditable(false);
		
		//jScrollPane.setRowHeaderView(new RowHeaderNumberView(j));
		container.add(jScrollPane);
		
		f.setSize(300,200);
		f.setLocation(0,0);
		f.setVisible(true);
		
	}
}
