package demos;

import java.awt.Container;
import java.awt.Font;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class JTabbedPaneDemo {
	public static void main(String[] args) {
		JFrame f = new JFrame("my second Swing Window");
		
		String iconPath_01 = "E:"+File.separator+"qq接收到的文件"+File.separator+"表情png\\stupid.png";
		String iconPath = "E:\\CloudNote_01\\app\\src\\main\\res\\mipmap-hdpi\\ic_launcher.png";
		Icon icon = new ImageIcon(iconPath_01);
		Font font = new Font("宋体", Font.ITALIC + Font.BOLD, 128);
		
		Container container = f.getContentPane();
		
		JPanel jPanel_01 = new JPanel();
		JPanel jPanel_02 = new JPanel();
		
		JButton jButton = new JButton("Button");
		JLabel jLabel = new JLabel("Label");
		
		jPanel_01.add(jButton);
		jPanel_02.add(jLabel);
		
		
		JDesktopPane jDesktopPane = new JDesktopPane();
		
		JInternalFrame jInternalFrame = null;
		JPanel jPanel = null;
		for(int i=0;i<=2;i++){
			jInternalFrame = new JInternalFrame("the "+i+" jInternalFrame window",true,true,true);
			
			jPanel = new JPanel();
			jPanel.add(new JLabel(icon));
			
			jInternalFrame.add(jPanel);
			jInternalFrame.setLocation(40-i*10,40-i*10);
			jInternalFrame.setVisible(true);
			jInternalFrame.pack();
			
			jDesktopPane.add(jInternalFrame);
		}
		
		container.add(jDesktopPane);
		
		f.setSize(300,200);
		f.setLocation(0,0);
		f.setVisible(true);
		f.pack();
		
	}
}
