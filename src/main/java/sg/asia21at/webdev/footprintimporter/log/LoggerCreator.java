/**
 * 
 */
package sg.asia21at.webdev.footprintimporter.log;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class LoggerCreator {
	private final static String TITLE = "GNDManager";
//	public final static Logger log = Logger.getLogger(LoggerCreator.class.getName());
	private final static Logger log = LogManager.getLogger(TITLE);
	private LoggerCreator(){
		
	}
	public static Logger getLogger(){
		return log;
	}

	public static void main(String[] args) {
		System.out.println("Hello world!");

		
		JFrame mainFrame = new JFrame("Java Swing Examples");
		// JPanel p = new JPanel(new BorderLayout());
		// p.add(textPane);
		mainFrame.setSize(600, 400);

		
		mainFrame.getContentPane().add(LogPanel.INSTANCE.getPane());
		// mainFrame.pack();
		mainFrame.setVisible(true);
		log.debug("Hello this is a debug message");
		log.info("Hello this is an info message");
		for (int i = 1; i < 11; i++) {
			log.info("Hello this is an info message");
			log.debug("Hello this is a debug message");
			log.error("Hello this is a debug message");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}
