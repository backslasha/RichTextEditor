package demos;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class MouseListner_01 extends JFrame implements KeyListener, MouseListener,
		MouseMotionListener {
	private JTextArea text = new JTextArea();
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public MouseListner_01() {
		setTitle("welcome to Mouse_Listner");

		text.setLineWrap(true);
		//text.addKeyListener(this);
		//text.addMouseListener(this);
		text.addMouseMotionListener(this);
		JScrollPane jScrollPane = new JScrollPane(text);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setBounds(5, 5, (int) screenSize.getWidth() - 10,
				(int) screenSize.getHeight() - 10);

		add(jScrollPane);
		setSize((int) (screenSize.getWidth() - 10),
				(int) screenSize.getHeight() - 10);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}

		});
	}

	public void keyPressed(KeyEvent arg0) {
		text.append("键盘“" + KeyEvent.getKeyText(arg0.getKeyCode()) + "”按下");
	}

	public void keyReleased(KeyEvent arg0) {
		text.append("键盘“" + KeyEvent.getKeyText(arg0.getKeyCode()) + "”按下");
	}

	public void keyTyped(KeyEvent arg0) {
		text.append("键盘“" + KeyEvent.getKeyText(arg0.getKeyCode()) + "”按下");
	}

	public void mouseClicked(MouseEvent e) {
		int c = e.getButton();
		String mouseInfo = c == MouseEvent.BUTTON1 ? "左键"
				: c == MouseEvent.BUTTON3 ? "右鍵" : "滚轮";
		text.append(mouseInfo);
	}

	public void mouseEntered(MouseEvent e) {
		text.append("鼠标进入组件\n");
	}

	public void mouseExited(MouseEvent e) {
		text.append("鼠标离开组件\n");
	}

	public void mousePressed(MouseEvent arg0) {
		text.append("鼠标按下\n");

	}

	public void mouseReleased(MouseEvent arg0) {
		text.append("鼠标释放\n");
	}

	public void mouseDragged(MouseEvent e) {
		//System.out.print("按下状态，鼠标滑动到坐标：" + e.getX() + "," + e.getY() + "\n");
	}

	public void mouseMoved(MouseEvent e) {
	//	System.out.print("非按下状态，鼠标滑动到坐标："+e.getX() + "," + e.getY()+"\n");
		System.out.print(text.getText()+"\n");
	}

}

public class MouseListenerDemo {
	public static void main(String[] args) {
		new MouseListner_01();
	}
}
