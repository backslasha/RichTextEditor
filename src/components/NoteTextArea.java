package components;

import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import bean.Note;


public class NoteTextArea extends JTextPane implements MouseListener {
	private static final long serialVersionUID = 1L;
	private Note mNote;
	public UndoManager undoManager;

	public Note getNote() {
		return mNote;
	}

	public void setNote(Note note) {
		this.mNote = note;
	}

	public NoteTextArea(Note note) {
		mNote = note;
		undoManager = new UndoManager();
		this.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent arg0) {
				undoManager.addEdit(arg0.getEdit());
			}
		});

		addMouseListener(this);
		setMargin(new Insets(15, 15, 15, 15));

		if (mNote.getFile().exists()) {
			try {
				Scanner scan = new Scanner(mNote.getFile());
				scan.useDelimiter("n#e*v%e%r&");
				if (scan.hasNext())
					setText(scan.next());
				scan.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
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

}
