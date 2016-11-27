package mynote;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

import models.JListAdapter;
import bean.Note;

import components.NoteTextPane;
//import components.RowHeaderNumberView;

public class MainWindow {
	private JFrame mJFrame;
	private Container mContainer;
	private JList<Note> mJList;
	private JListAdapter mJListAdapter;
	private JMenuBar mJMenuBar;
	private JTabbedPane mJTabbedPane;
	private JLabel mJLabel;
	private JSplitPane mJSplitPane_lr;
	private JToolBar mJToolBar;
	private JButton button_undo, button_redo, button_save;//Ϊ�˼��״̬����Ϊȫ�ֱ���

	public MainWindow() {
		mJFrame = new JFrame();

		mJSplitPane_lr = createJSplitPane_lr();

		mContainer = mJFrame.getContentPane();
		mContainer.add(mJSplitPane_lr, BorderLayout.CENTER);
		mContainer.add(mJToolBar, BorderLayout.NORTH);

		initJMenuBar();
		mJFrame.setJMenuBar(mJMenuBar);
		mJFrame.setTitle("MyNote");
		mJFrame.setSize(400 + 400, 600);
		mJFrame.setLocation(50, 50);
		mJFrame.setVisible(true);
		mJFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}
		});
	}

	/**
	 * ��������ͼ���б�+���ı�pane+J��ǩ����JSplitPane
	 * @return �б�+���ı�pane+J��ǩ����JSplitPane
	 */
	private JSplitPane createJSplitPane_lr() {
		initJList();
		initJTabbedPane();
		initJLabel();
		initJToolBar();

		JSplitPane jSplitPane_tb = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				false, mJTabbedPane, mJLabel);
		jSplitPane_tb.setDividerSize(3);

		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				false, new JScrollPane(mJList), jSplitPane_tb);
		jSplitPane.setDividerSize(3);

		return jSplitPane;
	}
	/**
	 * ��ʼ��JLabel
	 */
	private void initJLabel() {
		mJLabel = new JLabel();
	}

	/**
	 * ��ʼ��JTabbedPane��0��tab
	 */
	private void initJTabbedPane() {
		mJTabbedPane = new JTabbedPane();
		mJTabbedPane.setMinimumSize(new Dimension(0, 499));
		mJTabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!(e.getModifiers() == InputEvent.BUTTON3_MASK)) {
					return;
				}
				JPopupMenu jPopupMenu = new JPopupMenu();
				JMenuItem menuItem2 = new JMenuItem("�ر�");
				JMenuItem menuItem3 = new JMenuItem("�ر�����");

				menuItem2
						.addActionListener(new java.awt.event.ActionListener() { // �رյ��¼�����
							public void actionPerformed(ActionEvent e) {
								mJTabbedPane.remove(mJTabbedPane
										.getSelectedComponent());
							}
						});
				menuItem3
						.addActionListener(new java.awt.event.ActionListener() { // �ر����е��¼�����
							public void actionPerformed(ActionEvent e) {
								mJTabbedPane.removeAll();
							}
						});

				jPopupMenu.add(menuItem2);
				jPopupMenu.add(menuItem3);
				jPopupMenu.show(mJTabbedPane, e.getX(), e.getY());

			}
		});
	}

	/**
	 * ��ʼ���б�
	 */
	private void initJList() {
		mJListAdapter = new JListAdapter("E:\\��ҵ�ĵ�\\");

		mJList = new JList<Note>(mJListAdapter);
		mJList.setFixedCellHeight(30);
		mJList.setFixedCellWidth(150);
		mJList.setMinimumSize(new Dimension(0, 0));
		mJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					mJListAdapter.setReturnObject();
					addNoteTab(mJList.getSelectedValue());
				}
			}
		});
		mJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mJList.setBorder(BorderFactory.createTitledBorder("���бʼ�" + "("
				+ mJListAdapter.getWorkplace() + ")"));
	}

	/**
	 * ��ʼ���˵���
	 */
	private void initJMenuBar() {
		mJMenuBar = new JMenuBar();
		
		/**
		 * �ļ���
		 */
		JMenu jMenu_file = new JMenu("�ļ�(F)");
		jMenu_file.setMnemonic('F');

		JMenuItem item_new = new JMenuItem(new NewFileAction());
		JMenuItem item_open = new JMenuItem(new OpenFileAction());
		JMenuItem item_store = new JMenuItem(new SaveFileAction());
		JMenuItem item_choose_workplace = new JMenuItem(
				new ChooseWorkPlaceAction());

		item_new.setText("�½�");
		item_open.setText("��");
		item_store.setText("����");
		item_choose_workplace.setText("������");

		item_new.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		item_open.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		item_store.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

		jMenu_file.add(item_new);
		jMenu_file.add(item_open);
		jMenu_file.add(item_store);
		jMenu_file.add(item_choose_workplace);

		mJMenuBar.add(jMenu_file);
		
		/**
		 * �༭��
		 */
		JMenu jMenu_edit = new JMenu("�༭(E)");
		jMenu_edit.setMnemonic('E');

		JMenuItem item_undo = new JMenuItem("����");
		JMenuItem item_redo = new JMenuItem("�ָ�");
		JMenuItem item_cut = new JMenuItem("����");
		JMenuItem item_copy = new JMenuItem("����");
		JMenuItem item_paste = new JMenuItem("���");
		JMenuItem item_delete = new JMenuItem("ɾ��");
		JMenuItem item_select_all = new JMenuItem("ȫѡ");

		item_undo.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
		item_redo.setAccelerator(KeyStroke.getKeyStroke("ctrl Y"));
		item_cut.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		item_copy.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		item_paste.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
		item_delete.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
		item_select_all.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));

		item_undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NoteTextPane nat = (NoteTextPane) ((JScrollPane) mJTabbedPane
						.getSelectedComponent()).getViewport().getView();
				if (nat.undoManager.canUndo()) {
					nat.undoManager.undo();
				} else {
					System.out.println("nat.undoManager.canUndo():"
							+ nat.undoManager.canUndo());
				}
			}
		});
		item_redo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				NoteTextPane nat = (NoteTextPane) ((JScrollPane) mJTabbedPane
						.getSelectedComponent()).getViewport().getView();
				if (nat.undoManager.canRedo()) {
					nat.undoManager.redo();
				}

			}
		});
		item_cut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

			}
		});
		item_copy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		item_paste.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		item_delete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				NoteTextPane nat = (NoteTextPane) ((JScrollPane) mJTabbedPane
						.getSelectedComponent()).getViewport().getView();
				if (nat.getSelectedText() != null
						&& !nat.getSelectedText().equals("")) {
					nat.replaceSelection("");
				}

			}
		});
		item_select_all.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				NoteTextPane nat = (NoteTextPane) ((JScrollPane) mJTabbedPane
						.getSelectedComponent()).getViewport().getView();
				nat.selectAll();
			}
		});

		jMenu_edit.add(item_undo);
		jMenu_edit.add(item_redo);
		jMenu_edit.add(item_cut);
		jMenu_edit.add(item_copy);
		jMenu_edit.add(item_paste);
		jMenu_edit.add(item_delete);
		jMenu_edit.add(item_select_all);

		mJMenuBar.add(jMenu_edit);

		/**
		 * ���ã�
		 */
		JMenu jMenu_setting = new JMenu("����(T)");
		jMenu_setting.setMnemonic('T');

		JMenuItem item_personal_setting = new JMenuItem("��������");

		item_personal_setting.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

			}
		});

		jMenu_setting.add(item_personal_setting);
		// jMenu_setting.add(DefaultEditorKit.selectAllAction);

		mJMenuBar.add(jMenu_setting);
		
		
		/**���ڣ�
		 * 
		 */
		JMenu jMenu_about = new JMenu("����(H)");
		jMenu_about.setMnemonic('H');

		JMenuItem item_about_mynote = new JMenuItem("����MyNote");

		item_about_mynote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

			}
		});

		jMenu_about.add(item_about_mynote);

		mJMenuBar.add(jMenu_about);
		
		
		/**
		 * ��ʽ��
		 */
		JMenu jMenu_style = new JMenu("��ʽ" + "(S)");
		jMenu_style.setMnemonic('S');

		Action action = new StyledEditorKit.BoldAction();
		action.putValue(Action.NAME, "�Ӵ�");
		action.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke("ctrl B"));
		jMenu_style.add(action);

		action = new StyledEditorKit.UnderlineAction();
		action.putValue(Action.NAME, "�»���");
		action.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke("ctrl U"));
		jMenu_style.add(action);

		action = new StyledEditorKit.ItalicAction();
		action.putValue(Action.NAME, "��б");
		action.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke("ctrl I"));
		jMenu_style.add(action);

		jMenu_style.addSeparator();

		jMenu_style.add(new StyledEditorKit.FontSizeAction("12", 12));
		jMenu_style.add(new StyledEditorKit.FontSizeAction("14", 14));
		jMenu_style.add(new StyledEditorKit.FontSizeAction("18", 18));

		jMenu_style.addSeparator();

		jMenu_style.add(new StyledEditorKit.ForegroundAction("Red", Color.red));
		jMenu_style.add(new StyledEditorKit.ForegroundAction("Green",
				Color.green));
		jMenu_style
				.add(new StyledEditorKit.ForegroundAction("Blue", Color.blue));
		jMenu_style.add(new StyledEditorKit.ForegroundAction("Black",
				Color.black));

		mJMenuBar.add(jMenu_style);
		
	}

	
	/**
	 * ����note������һ���µ�tabҳ��JScrollPane(nTextPane)
	 * ����ѡ��л�ʱ���������¡����桱�����������͡���������������ť
	 * ����nTextPane�ı�StyledDocument�ı�ʱ���������¡����桱�����������͡���������������ť
	 * @param note 
	 */
	protected void addNoteTab(Note note) {
		NoteTextPane nTextPane = new NoteTextPane(note);
		JScrollPane jsp = new JScrollPane(nTextPane);
		//jsp.setRowHeaderView(new RowHeaderNumberView(nTextPane));
		mJTabbedPane.addTab(nTextPane.getNote().getTitle(), jsp);
		mJTabbedPane.setSelectedComponent(jsp);
		mJTabbedPane.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				if (mJTabbedPane.getTabCount() == 0){
					button_undo.setEnabled(false);
					button_redo.setEnabled(false);
					button_save.setEnabled(false);
					return;
				}
				checkUndoButton();
				 checkSaveButton();
				 checkRedoButton();
			}
		});
		nTextPane.getStyledDocument().addDocumentListener(
				new DocumentListener() {

					public void removeUpdate(DocumentEvent arg0) {
						
						setChange();
						checkSaveButton();
						checkUndoButton();
						checkRedoButton();
						System.out.println("removeUpdate");
					}

					public void insertUpdate(DocumentEvent arg0) {
						setChange();
						checkSaveButton();
						checkUndoButton();
						checkRedoButton();
						System.out.println("insertUpdate");
					}

					public void changedUpdate(DocumentEvent arg0) {
						setChange();
						checkSaveButton();
						checkUndoButton();
						checkRedoButton();
						System.out.println("changedUpdate");
					}

					private void setChange() {
						if (mJTabbedPane.getTabCount() == 0)
							return;
						NoteTextPane ntp = (NoteTextPane) ((JScrollPane) mJTabbedPane
								.getSelectedComponent()).getViewport()
								.getView();
						ntp.setChange(true);
						System.out.println("ntp.undoManager.canUndo(): "+ntp.undoManager.canUndo());
					}
				});
	}

	/**
	 * ��StyledDocument���л����浽�����ļ�����Ϊ***.yhb��ʽ���ı�
	 * @param doc StyledDocument����
	 * @param completePath	���������·��/***.yhb
	 */
	protected void saveAsObj(StyledDocument doc, String completePath) {
		File file = new File(completePath);
		if (file.exists()) {
			try {
				file.createNewFile();
				ObjectOutputStream oos = new ObjectOutputStream(
						new FileOutputStream(file));
				oos.writeObject(doc);
				oos.flush();
				oos.close();
				System.out.println("����ɹ�");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("����ʧ��");
			}
		}
	}

	/**
	 * ��ʼ����ݹ�����
	 */
	private void initJToolBar() {
		mJToolBar = new JToolBar();
		mJToolBar.setFloatable(false);

		JButton button = null;
		// new a file button
		button = new JButton(new NewFileAction());
		button.setToolTipText("�½�");//tip
		button.setBorderPainted(false);//ȡ����ť�Դ��ı߿�
		button.setFocusPainted(false);//ȡ����ťѡ��ʱ�Դ��ı߿�
		button.setIcon(new ImageIcon(
				"D:\\javasoft\\workplace\\MyNote/src/img/new.png"));
		mJToolBar.add(button);

		// open a file button
		button = new JButton(new OpenFileAction());
		button.setToolTipText("��");
		button.setIcon(new ImageIcon(
				"D:\\javasoft\\workplace\\MyNote/src/img/open.png"));
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		mJToolBar.add(button);

		// save a file button
		button_save = new JButton(new SaveFileAction());
		button_save.setEnabled(false);
		button_save.setIcon(new ImageIcon(
				"D:\\javasoft\\workplace\\MyNote/src/img/save.png"));
		button_save.setToolTipText("����");
		button_save.setBorderPainted(false);
		button_save.setFocusPainted(false);
		mJToolBar.add(button_save);

		// undo button
		button_undo = new JButton(new UndoAction());
		button_undo.setEnabled(false);
		button_undo.setIcon(new ImageIcon(
				"D:\\javasoft\\workplace\\MyNote/src/img/undo.png"));
		button_undo.setToolTipText("����");
		button_undo.setBorderPainted(false);
		button_undo.setFocusPainted(false);
		mJToolBar.add(button_undo);

		// redo button
		button_redo = new JButton(new RedoAction());
		button_redo.setEnabled(false);
		button_redo.setIcon(new ImageIcon(
				"D:\\javasoft\\workplace\\MyNote/src/img/redo.png"));
		button_redo.setToolTipText("����");
		button_redo.setBorderPainted(false);
		button_redo.setFocusPainted(false);
		mJToolBar.add(button_redo);

	}

	/**
	 * ����Undo��ť��״̬
	 */
	protected void checkUndoButton() {
			
		NoteTextPane ntp = (NoteTextPane) ((JScrollPane) mJTabbedPane
				.getSelectedComponent()).getViewport().getView();
		button_undo.setEnabled(ntp.undoManager.canUndo());
	}

	protected void checkRedoButton(){
		NoteTextPane ntp = (NoteTextPane) ((JScrollPane) mJTabbedPane
				.getSelectedComponent()).getViewport().getView();
		button_redo.setEnabled(ntp.undoManager.canRedo());
	}
	
	protected void checkSaveButton() {
		NoteTextPane ntp = (NoteTextPane) ((JScrollPane) mJTabbedPane
				.getSelectedComponent()).getViewport().getView();
		button_save.setEnabled(ntp.isChange());
	}

	class NewFileAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent arg0) {
			String input = JOptionPane.showInputDialog(mJFrame, "�����ı����⣺", "",
					JOptionPane.PLAIN_MESSAGE);

			if (input != null) {
				String title = !input.equals("") ? input : "δ�����ı�";
				Note note = new Note(title + ".yhb",
						mJListAdapter.getWorkplace() + title + ".yhb");
				addNoteTab(note);

				mJListAdapter.addNote(note);
				mJList.setModel(mJListAdapter);
				mJList.updateUI();

			}

		}

	}

	class OpenFileAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent arg0) {
			FileDialog fileDialog = new FileDialog(mJFrame, "ѡ���ļ�");
			fileDialog.setFilenameFilter(new FilenameFilter() {

				public boolean accept(File arg0, String arg1) {
					if (arg1.matches(".+.yhb"))
						return true;
					return false;
				}
			});
			fileDialog.setVisible(true);

			File file = null;
			if (fileDialog.getFile() != null) {
				file = new File(fileDialog.getDirectory(), fileDialog.getFile());
				Note note = new Note();
				note.setFilePath(file.getAbsolutePath());
				note.setTitle(file.getName());
				note.setFile(file);

				addNoteTab(note);

				mJListAdapter.addNote(note);
				mJList.setModel(mJListAdapter);
				mJList.updateUI();
			} else {
				System.out.println("�ļ������ڣ�");
			}
		}

	}

	class SaveFileAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent arg0) {
			JScrollPane scp = (JScrollPane) mJTabbedPane.getSelectedComponent();
			Note note = ((NoteTextPane) scp.getViewport().getView()).getNote();
			if (note.getFile() != null) {
				NoteTextPane ntp = (NoteTextPane) scp.getViewport().getView();
				saveAsObj(ntp.getStyledDocument(), note.getFilePath());
				button_save.setEnabled(false);
				ntp.setChange(false);
			}
		}

	}

	class ChooseWorkPlaceAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			chooser.setVisible(true);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = chooser.showOpenDialog(mJFrame);
			if (result == JFileChooser.APPROVE_OPTION) {
				mJListAdapter = new JListAdapter(chooser.getSelectedFile()
						.getAbsolutePath() + "\\");
				mJList.setBorder(BorderFactory.createTitledBorder("���бʼ�" + "("
						+ mJListAdapter.getWorkplace() + ")"));

				mJList.setModel(mJListAdapter);
				mJList.updateUI();
			}
		}

	}

	class UndoAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent arg0) {
			if (mJTabbedPane.getSelectedComponent() != null) {
				NoteTextPane nat = (NoteTextPane) ((JScrollPane) mJTabbedPane
						.getSelectedComponent()).getViewport().getView();
				if (nat.undoManager.canUndo()) {
					nat.undoManager.undo();
					checkUndoButton();
				}
			}
		}

	}

	class RedoAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent arg0) {
			NoteTextPane nat = (NoteTextPane) ((JScrollPane) mJTabbedPane
					.getSelectedComponent()).getViewport().getView();
			if (nat.undoManager.canRedo()) {
				nat.undoManager.redo();
				if (nat.undoManager.canRedo()) {
					((JButton) (arg0.getSource())).setEnabled(true);
				} else {
					((JButton) (arg0.getSource())).setEnabled(false);
				}
			}

		}

	}
}
