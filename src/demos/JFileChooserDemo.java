package demos;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class JFileChooserDemo {
	public static void main(String[] args) {
		new MainWindow();
	}
}

class Note implements ActionListener {
	public static String ICON = "E:\\CloudNote_01\\app\\src\\main\\res\\mipmap-hdpi\\ic_launcher.png";
	private JFrame jFrame = new JFrame("MyNote");
	private File file = null;
	private int result = 0;
	private JFileChooser jFileChooser = new JFileChooser();

	public Note() {
		final JTextArea jTextArea = new JTextArea();
		jTextArea.setLineWrap(true);

		JMenu jMenu_file = new JMenu("文件");
		JMenuBar jMenuBar = new JMenuBar();
		{
			JMenuItem item_new = new JMenuItem("新建");
			item_new.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

				}
			});
			JMenuItem item_open = new JMenuItem("打开");
			item_open.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					jTextArea.setText("");

					jFileChooser.setApproveButtonText("确定");
					jFileChooser.setDialogTitle("打开文件");
					result = jFileChooser.showOpenDialog(jFrame);// 阻塞

					if (result == JFileChooser.APPROVE_OPTION) {
						file = jFileChooser.getSelectedFile();
						jFrame.setTitle(file.getName() + " -MyNote");
					} else if (result == JFileChooser.CANCEL_OPTION) {

					} else if (result == JFileChooser.ERROR_OPTION) {

					}

					if (file != null) {
						Scanner scanner;
						try {
							scanner = new Scanner(new FileInputStream(file));
							scanner.useDelimiter("\n");
							while (scanner.hasNext()) {
								jTextArea.append(scanner.next() + "\n");
							}
							scanner.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

					}

				}
			});
			JMenuItem item_store = new JMenuItem("保存");
			item_store.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					result = jFileChooser.showSaveDialog(jFrame);

					if (result == JFileChooser.APPROVE_OPTION) {
						file = new File(jFileChooser.getCurrentDirectory()
								.getAbsolutePath()
								+ File.pathSeparator
								+ jFileChooser.getDialogType());
						try {
							file.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println(jFileChooser.getDialogType());
					}

				}
			});
			JMenuItem item_close = new JMenuItem("退出");
			item_close.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

				}
			});

			// jMenu_file.setIcon(new ImageIcon(ICON));
			jMenu_file.add(item_new);
			jMenu_file.add(item_open);
			jMenu_file.add(item_store);
			jMenu_file.addSeparator();
			jMenu_file.add(item_close);

			jMenuBar.add(jMenu_file);
		}

		jFrame.getContentPane().add(new JScrollPane(jTextArea));
		jFrame.setJMenuBar(jMenuBar);
		jFrame.setSize(400, 600);
		jFrame.setLocation(800, 50);
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}
		});
	}

	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getID()) {
		}
	}
}

class MainWindow extends JFrame {
	private String workplace = "E:\\作业文档\\";
	private Vector<String> notes;
	private Container container;
	private JList jList;

	public MainWindow() {

		JMenuBar jMenuBar = new JMenuBar();
		{
			JMenu jMenu_file = new JMenu("文件");

			JMenuItem item_new = new JMenuItem("新建");
			JMenuItem item_open = new JMenuItem("打开");

			item_open.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser jFileChooser = new JFileChooser();
					int result;
					File file = null;
					jFileChooser .setApproveButtonText("确定");
					jFileChooser.setDialogTitle("打开文件");
					result = jFileChooser.showOpenDialog(MainWindow.this);// 阻塞

					if (result == JFileChooser.APPROVE_OPTION) {
						file = jFileChooser.getSelectedFile();
						MainWindow.this.setTitle(file.getName() + " -MyNote");
					} else if (result == JFileChooser.CANCEL_OPTION) {

					} else if (result == JFileChooser.ERROR_OPTION) {

					}
				}
			});
			item_new.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

				}
			});

			// jMenu_file.setIcon(new ImageIcon(ICON));
			jMenu_file.add(item_new);
			jMenu_file.add(item_open);

			jMenuBar.add(jMenu_file);
		}

		container = getContentPane();
		notes = new Vector<String>();

		File d = new File(workplace);
		if (d.exists() && d.isDirectory()) {
			File[] files = d.listFiles();
			for (int i = 0; i < files.length; i++) {
				notes.add((i + 1) + ". " + files[i].getName());
			}
		}

		jList = new JList(notes);
		jList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					System.out.println(jList.getSelectedValue().toString());
				}
			}
		});
		jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		jList.setBorder(BorderFactory.createTitledBorder("所有笔记" + "("
				+ workplace + ")"));

		container.add(jList);
		setJMenuBar(jMenuBar);
		setTitle("MyNote");
		setSize(400, 600);
		setLocation(50, 50);
		setVisible(true);
	}

}
