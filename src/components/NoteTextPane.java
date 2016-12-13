package components;

import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

import bean.Note;

public class NoteTextPane extends JTextPane {
	private static final long serialVersionUID = 1L;
	private Note mNote;
	public UndoManager undoManager;
	private boolean isChange = false;//标志一个NoteTextPane内容已经发生改变

	public NoteTextPane(Note note) {
		mNote = note;

		undoManager = new UndoManager();

		setMargin(new Insets(15, 15, 15, 15));

		if (mNote.getFile().exists()) {
			// 打开的如果是纯文本
			if (note.getTitle().matches(".+.(txt|java)")) {
				Scanner scan = null;
				try {
					scan = new Scanner(note.getFile());
					scan.useDelimiter("不适用分隔符，这样一次把文本读取完毕");
					if (scan.hasNext()) {
						setText(scan.next());
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}finally{
					scan.close();
				}
				return;
			}// 打开的如果是yhb文本
			else if (readFromObj(mNote.getFilePath()) != null) {
				setStyledDocument(readFromObj(mNote.getFilePath()));
				System.out.println("打开格式化文本成功！");
			}
		}
		
		getDocument().addUndoableEditListener(new UndoableEditListener() {

			public void undoableEditHappened(UndoableEditEvent arg0) {
				undoManager.addEdit(arg0.getEdit());
			}
		});
		getStyledDocument().addUndoableEditListener(new UndoableEditListener() {

			public void undoableEditHappened(UndoableEditEvent arg0) {
				undoManager.addEdit(arg0.getEdit());
			}
		});
	}

	public Note getNote() {
		return mNote;
	}

	public boolean isChange() {
		return isChange;
	}

	private StyledDocument readFromObj(String completePath) {
		File file = new File(completePath);
		ObjectInputStream ois = null;
		StyledDocument doc = null;
		if (file.exists() && file.length() != 0) {
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

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	public void setNote(Note note) {
		this.mNote = note;
	}

}
