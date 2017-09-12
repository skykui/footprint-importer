package sg.asia21at.webdev.footprintimporter.log;
/**
 * @author Ye Chuang
 * Singleton by Enum
 * Only one instance is allowed
 */

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BoundedRangeModel;
import javax.swing.JScrollPane;

public enum LogPanel {
	INSTANCE;
	private final AppendingHTMLPane myTextPane = new AppendingHTMLPane();
	private final JScrollPane conversationScrollPane = new JScrollPane(myTextPane);
	
	private LogPanel() {
		
		// Initialize auto-scroll panel
		conversationScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

			BoundedRangeModel brm = conversationScrollPane.getVerticalScrollBar().getModel();
			boolean wasAtBottom = true;

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if (!brm.getValueIsAdjusting()) {
					if (wasAtBottom)
						brm.setValue(brm.getMaximum());
				} else
					wasAtBottom = ((brm.getValue() + brm.getExtent()) == brm.getMaximum());

			}

		});
	}
	
	public void appendText(String s) {
		myTextPane.appendText(s);
	}
	
	public void clearText(){
		myTextPane.setText("");
	}
	
	public JScrollPane getPane() {
		return conversationScrollPane;
	}
	

//	public AppendingTextPane getTextPane() {
//		return myTextPane;
//	}

}
