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
		text.append("���̡�" + KeyEvent.getKeyText(arg0.getKeyCode()) + "������");
	}

	public void keyReleased(KeyEvent arg0) {
		text.append("���̡�" + KeyEvent.getKeyText(arg0.getKeyCode()) + "������");
	}

	public void keyTyped(KeyEvent arg0) {
		text.append("���̡�" + KeyEvent.getKeyText(arg0.getKeyCode()) + "������");
	}

	public void mouseClicked(MouseEvent e) {
		int c = e.getButton();
		String mouseInfo = c == MouseEvent.BUTTON1 ? "���"
				: c == MouseEvent.BUTTON3 ? "���I" : "����";
		text.append(mouseInfo);
	}

	public void mouseEntered(MouseEvent e) {
		text.append("���������\n");
	}

	public void mouseExited(MouseEvent e) {
		text.append("����뿪���\n");
	}

	public void mousePressed(MouseEvent arg0) {
		text.append("��갴��\n");

	}

	public void mouseReleased(MouseEvent arg0) {
		text.append("����ͷ�\n");
	}

	public void mouseDragged(MouseEvent e) {
		//System.out.print("����״̬����껬�������꣺" + e.getX() + "," + e.getY() + "\n");
	}

	public void mouseMoved(MouseEvent e) {
	//	System.out.print("�ǰ���״̬����껬�������꣺"+e.getX() + "," + e.getY()+"\n");
		System.out.print(text.getText()+"\n");
	}

}

public class MouseListenerDemo {
	public static void main(String[] args) {
		new MouseListner_01();
	}
}
