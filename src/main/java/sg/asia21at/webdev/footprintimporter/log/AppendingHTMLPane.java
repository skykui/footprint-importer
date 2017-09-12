package sg.asia21at.webdev.footprintimporter.log;

//import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class AppendingHTMLPane extends JTextPane {
	private HTMLDocument doc;
	private HTMLEditorKit kit;
//	private Rectangle r;

	public AppendingHTMLPane() {
		super();
		this.setEditable(false);
		this.setContentType("text/html");
		doc = (HTMLDocument) this.getStyledDocument();
		kit = (HTMLEditorKit) this.getEditorKit();
	}

	public AppendingHTMLPane(StyledDocument doc) {
		super(doc);

	}

	// Appends text to the document and ensure that it is visible
	public void appendText(String text) {
		try {

//			 kit.insertHTML(doc, doc.getLength(), "<font color='red'><u>"+text+"</u></font>", 0, 0, null);
			 kit.insertHTML(doc, doc.getLength(),text, 0, 0, null);

//			doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), "<b>" + text + "</b>");
			// doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),
			// "<b>Hello</b>");
//			r = modelToView(doc.getLength());
//
//			// Finally, scroll so that the new text is visible
//			if (r != null) {
//				scrollRectToVisible(r);
//			}
		} catch (BadLocationException e) {
			System.out.println("Failed to append text: " + e);
		} catch (IOException e) {

			System.out.println("Failed to append text: " + e);
		}
	}
}
