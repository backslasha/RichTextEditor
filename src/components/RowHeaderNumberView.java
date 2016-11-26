package components;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class RowHeaderNumberView extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int hoff = 1;
	private JTextPane mJTextPane;

	public RowHeaderNumberView(JTextPane jTextPane) {
		super();
		mJTextPane = jTextPane;
		setColumns(1);
		setBackground(Color.decode("#e4ba73"));
		setMargin(new Insets(15, 5, 0, 15));
		setEditable(false);
		setCaretColor(Color.decode("#e4ba73"));
		setText("");
		for (int i = 1; i <= getLineCount(); i++) {
			append(String.valueOf(i) + "\n");
		}
		mJTextPane.getDocument().addDocumentListener(new DocumentListener() {

			public void removeUpdate(DocumentEvent arg0) {
				setText("");
				for (int i = 1; i <= getLineCount(); i++) {
					append(String.valueOf(i) + "\n");
				}

			}

			public void insertUpdate(DocumentEvent arg0) {
				setText("");
				for (int i = 1; i <= getLineCount(); i++) {
					append(String.valueOf(i) + "\n");
				}

			}

			public void changedUpdate(DocumentEvent arg0) {
				setText("");
				for (int i = 1; i <= getLineCount(); i++) {
					append(String.valueOf(i) + "\n");
				}
			}
		});
	}

	public int getLineCount() {
		Element map = mJTextPane.getDocument().getDefaultRootElement();
		return map.getElementCount();
	}

}