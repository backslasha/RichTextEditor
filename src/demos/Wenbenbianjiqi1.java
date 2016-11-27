package demos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Wenbenbianjiqi1 extends JFrame {
	private JPanel contentPane;
	private String fileName;
	private JFrame frame;
	JTextArea textArea = null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Wenbenbianjiqi1() {
		setTitle("�ı��༭��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu wenjian = new JMenu("�ļ�");
		menuBar.add(wenjian);

		JMenuItem xinjian = new JMenuItem("�½�");
		wenjian.add(xinjian);
		xinjian.addActionListener(new xinjianAction());

		JMenuItem dakai = new JMenuItem("��");
		wenjian.add(dakai);
		dakai.addActionListener(new dakaiAction());

		JMenuItem baocun = new JMenuItem("����");
		wenjian.add(baocun);
		baocun.addActionListener(new baocunAction());

		JMenuItem lingcunwei = new JMenuItem("���Ϊ");
		wenjian.add(lingcunwei);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setText("");
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
	}

	// �½�
	class xinjianAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			textArea.setText("");

		}
	}

	// ��
	class dakaiAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FileDialog d = new FileDialog(frame, "openfile", FileDialog.LOAD);// ���ļ��Ի���
			d.addWindowListener(new WindowAdapter() {// �ر��ļ��Ի��򴰿�
				public void windowClosing(WindowEvent ee) {
					System.exit(0);
				}
			});
			d.setVisible(true);
			File f = new File(d.getDirectory() + d.getFile()); // �������ļ�
			fileName = d.getDirectory() + d.getFile();// �õ��ļ���
			char ch[] = new char[(int) f.length()];// /�ô��ļ��ĳ��Ƚ���һ���ַ�����
			try// �쳣����
			{
				// �������ݣ��������ַ�����ch��
				BufferedReader bw = new BufferedReader(new FileReader(f));
				bw.read(ch);
				bw.close();
			} catch (FileNotFoundException fe) {
				System.out.println("file not found");
				System.exit(0);
			} catch (IOException ie) {
				System.out.println("IO error");
				System.exit(0);
			}
			String s = new String(ch);
			textArea.setText(s);
		}
	}

	// ����
	class baocunAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (fileName == null) {
				FileDialog d = new FileDialog(frame, "savefile",
						FileDialog.SAVE);// �����ļ��Ի���
				d.addWindowListener(   new WindowAdapter() {public void windowClosing(WindowEvent ee) {System.exit(0);}}  );
				d.setVisible(true);
				String s = textArea.getText();
				try// �쳣����
				{
					File f = new File(d.getDirectory() + d.getFile());// �½��ļ�
					fileName = d.getDirectory() + d.getFile();// �õ��ļ���
					BufferedWriter bw = new BufferedWriter(new FileWriter(f));// ���뵽�ļ���
					bw.write(s, 0, s.length());
					bw.close();
				} catch (FileNotFoundException fe_) {
					System.out.println("file notfound");
					System.exit(0);
				} catch (IOException ie_) {
					System.out.println("IOerror");
					System.exit(0);
				}
			} else// ����ļ��Ѿ������
			{
				String s = textArea.getText();// �õ���������ı�����
				try// �쳣����
				{
					File f = new File(fileName);// �½��ļ�
					BufferedWriter bw = new BufferedWriter(new FileWriter(f));
					bw.write(s, 0, s.length());
					bw.close();
				} catch (FileNotFoundException fe_) {
					System.out.println("filenotfound");
					System.exit(0);
				} catch (IOException ie_) {
					System.out.println("IOerror");
					System.exit(0);
				}
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wenbenbianjiqi1 frame = new Wenbenbianjiqi1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}