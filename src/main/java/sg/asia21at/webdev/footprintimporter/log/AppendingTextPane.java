package sg.asia21at.webdev.footprintimporter.log;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;

public class AppendingTextPane extends JTextPane {
	public AppendingTextPane() {
		super();
	}

	public AppendingTextPane(StyledDocument doc) {
		super(doc);

	}

	// Appends text to the document and ensure that it is visible
	public void appendText(String text) {
		try {
			Document doc = getDocument();

			// Move the insertion point to the end
			setCaretPosition(doc.getLength());

			// Insert the text
			replaceSelection(text);

			// Convert the new end location
			// to view co-ordinates
			Rectangle r = modelToView(doc.getLength());

			// Finally, scroll so that the new text is visible
			if (r != null) {
				scrollRectToVisible(r);
			}
		} catch (BadLocationException e) {
			System.out.println("Failed to append text: " + e);
		}
	}
}
