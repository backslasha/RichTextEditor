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
		setTitle("文本编辑器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu wenjian = new JMenu("文件");
		menuBar.add(wenjian);

		JMenuItem xinjian = new JMenuItem("新建");
		wenjian.add(xinjian);
		xinjian.addActionListener(new xinjianAction());

		JMenuItem dakai = new JMenuItem("打开");
		wenjian.add(dakai);
		dakai.addActionListener(new dakaiAction());

		JMenuItem baocun = new JMenuItem("保存");
		wenjian.add(baocun);
		baocun.addActionListener(new baocunAction());

		JMenuItem lingcunwei = new JMenuItem("另存为");
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

	// 新建
	class xinjianAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			textArea.setText("");

		}
	}

	// 打开
	class dakaiAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FileDialog d = new FileDialog(frame, "openfile", FileDialog.LOAD);// 打开文件对话框
			d.addWindowListener(new WindowAdapter() {// 关闭文件对话框窗口
				public void windowClosing(WindowEvent ee) {
					System.exit(0);
				}
			});
			d.setVisible(true);
			File f = new File(d.getDirectory() + d.getFile()); // 建立新文件
			fileName = d.getDirectory() + d.getFile();// 得到文件名
			char ch[] = new char[(int) f.length()];// /用此文件的长度建立一个字符数组
			try// 异常处理
			{
				// 读出数据，并存入字符数组ch中
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

	// 保存
	class baocunAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (fileName == null) {
				FileDialog d = new FileDialog(frame, "savefile",
						FileDialog.SAVE);// 保存文件对话框
				d.addWindowListener(   new WindowAdapter() {public void windowClosing(WindowEvent ee) {System.exit(0);}}  );
				d.setVisible(true);
				String s = textArea.getText();
				try// 异常处理
				{
					File f = new File(d.getDirectory() + d.getFile());// 新建文件
					fileName = d.getDirectory() + d.getFile();// 得到文件名
					BufferedWriter bw = new BufferedWriter(new FileWriter(f));// 输入到文件中
					bw.write(s, 0, s.length());
					bw.close();
				} catch (FileNotFoundException fe_) {
					System.out.println("file notfound");
					System.exit(0);
				} catch (IOException ie_) {
					System.out.println("IOerror");
					System.exit(0);
				}
			} else// 如果文件已经保存过
			{
				String s = textArea.getText();// 得到所输入的文本内容
				try// 异常处理
				{
					File f = new File(fileName);// 新建文件
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