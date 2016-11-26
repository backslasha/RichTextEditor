package demos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class KeyEvent_01 extends JFrame implements KeyListener{
	private JTextArea text = new JTextArea();
	
	public KeyEvent_01(){
		setTitle("welcome to keyEvent");
		
		text.addKeyListener(this);
		JScrollPane jScrollPane = new JScrollPane(text);
		jScrollPane.setBounds(5,5,300,200);
		
		add(jScrollPane);
		setSize(310,210);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}
				
		});
	}

	public void keyPressed(KeyEvent arg0) {
		text.append("���̡�"+KeyEvent.getKeyText(arg0.getKeyCode())+"������");
	}

	public void keyReleased(KeyEvent arg0) {
		text.append("���̡�"+KeyEvent.getKeyText(arg0.getKeyCode())+"������");
	}

	public void keyTyped(KeyEvent arg0) {
		text.append("���̡�"+KeyEvent.getKeyText(arg0.getKeyCode())+"������");
	}
	
	
}
public class KeyEventDemo{
	public static void main(String[] args) {
		new MouseListner_01();
	}
}

