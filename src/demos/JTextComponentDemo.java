package demos;

import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class JTextComponentDemo {
	public static void main(String[] args) {
		JFrame f = new JFrame("my second Swing Window");
		f.setLayout(new FlowLayout());

		String iconPath_01 = "E:" + File.separator + "qq接收到的文件"
				+ File.separator + "表情png\\stupid.png";
		String iconPath = "E:\\CloudNote_01\\app\\src\\main\\res\\mipmap-hdpi\\ic_launcher.png";
		Icon icon = new ImageIcon(iconPath_01);
		Font font = new Font("宋体", Font.ITALIC + Font.BOLD, 128);

		Container container = f.getContentPane();

		JPanel jPanel_01 = new JPanel();
		jPanel_01.setLayout(new FlowLayout());
		JPanel jPanel_02 = new JPanel();
		jPanel_02.setLayout(new FlowLayout());

		JTextField jTextField = new JTextField(5);
		JTextField jTextField_none = new JTextField("none", 30);
		jTextField_none.setEditable(false);

		JLabel jLabel_name = new JLabel("name:");
		JLabel jLabel_null = new JLabel("null:");

		jPanel_01.add(jLabel_name);
		jPanel_01.add(jTextField);
		jPanel_02.add(jLabel_null);
		jPanel_02.add(jTextField_none);

		container.add(jPanel_01);
		container.add(jPanel_02);

		f.setSize(300, 200);
		f.setLocation(0, 0);
		f.setVisible(true);

	}
}
