package components;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Utilities;

public class RowHeaderNumberView extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int hoff = 1;
	private JTextPane mTextChangeEvent;

	public RowHeaderNumberView(JTextPane jTextPane) {
		super();
		mTextChangeEvent = jTextPane;
		setColumns(1);
		setBackground(Color.decode("#e4ba73"));
		setMargin(new Insets(15, 5, 0, 15));
		setEditable(false);
		setCaretColor(Color.decode("#e4ba73"));
		updateLineNum();

		mTextChangeEvent.getStyledDocument().addDocumentListener(
				new DocumentListener() {

					public void removeUpdate(DocumentEvent arg0) {
						updateLineNum();
					}

					public void insertUpdate(DocumentEvent arg0) {
						updateLineNum();
					}

					public void changedUpdate(DocumentEvent arg0) {
						updateLineNum();
					}
				});
	}

	public int getLineCount() {
		Element map = mTextChangeEvent.getStyledDocument()
				.getDefaultRootElement();
		return map.getElementCount();
		/*
		 * int totalCharacters =
		 * mTextChangeEvent.getStyledDocument().getLength(); int lineCount =
		 * (totalCharacters == 0) ? 1 : 0;
		 * 
		 * try { int offset = totalCharacters; while (offset > 0) { offset =
		 * Utilities.getRowStart(mTextChangeEvent, offset) - 1; lineCount++; } }
		 * catch (BadLocationException e) { e.printStackTrace(); } return
		 * lineCount;
		 */
	}

	private void updateLineNum() {
		setText("");
		for (int i = 1; i <= getLineCount(); i++) {
			append(String.valueOf(i) + "\n");
		}
	}

}