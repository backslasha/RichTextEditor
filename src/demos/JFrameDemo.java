package demos;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class JFrameDemo {
	public static void main(String[] args) {
		JFrame f = new JFrame("my first Swing Window");
		
		String iconPath = "E:"+File.separator+"qq接收到的文件"+File.separator+"表情png\\stupid.png";
		Icon icon = new ImageIcon(iconPath);
		Font font = new Font("宋体",Font.ITALIC+Font.BOLD,128);
		
		JLabel jLabel = new JLabel("habblism");
		//jLabel.setIcon(icon);
		jLabel.setFont(font);
		
		JButton jButton = new JButton("JButton");
		JButton jButton1 = new JButton("JButton1");
		JButton jButton2 = new JButton("JButton2");
		JButton jButton3 = new JButton("JButton3");
		
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jButton,jButton1);
		jSplitPane.setDividerSize(10);
		jSplitPane.setOneTouchExpandable(true);
		
		CardLayout cardLayout =new CardLayout();
		f.setLayout(cardLayout);
		f.add(jSplitPane);//f.add(jButton,"1");f.add(jButton1,"2");f.add(jButton2,"3");f.add(jButton3,"4");f.add(jLabel,"5");
		
		f.setSize(240*3,80*6);
		f.setBackground(Color.blue);
		f.setLocation(300,200);
		f.setVisible(true);
		//f.pack();
		
		/*for(int i=5 ;i>=1;i--){
			try {
				Thread.sleep(1000*2);
				cardLayout.next(f.getContentPane());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		
		
		
		
	}
}
