package components;

import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

import bean.Note;

public class NoteTextPane extends JTextPane implements MouseListener {
	private static final long serialVersionUID = 1L;
	private Note mNote;
	public UndoManager undoManager;

	public Note getNote() {
		return mNote;
	}

	public void setNote(Note note) {
		this.mNote = note;
	}

	public NoteTextPane(Note note) {
		mNote = note;
		
		undoManager =new UndoManager();
		getStyledDocument().addUndoableEditListener(new UndoableEditListener() {
			
			public void undoableEditHappened(UndoableEditEvent arg0) {
				undoManager.addEdit(arg0.getEdit());
				
			}
		});

		addMouseListener(this);
		setMargin(new Insets(15, 15, 15, 15));

		if (mNote.getFile().exists()) {
			if (readFromObj(mNote.getFilePath()) != null) {
				setStyledDocument(readFromObj(mNote.getFilePath()));
				System.out.println("打开格式化文本成功！");
			}
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

	private StyledDocument readFromObj(String completePath) {
		File file = new File(completePath);
		ObjectInputStream ois = null;
		StyledDocument doc = null;
		if (file.exists() && file.length()!=0) {
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				doc = (StyledDocument) ois.readObject();
			} catch (IOException e) {
				 e.printStackTrace();
				System.out.println("文件打开失败！");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("读取文件格式错误！");
			} finally {
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return doc;
	}
}
