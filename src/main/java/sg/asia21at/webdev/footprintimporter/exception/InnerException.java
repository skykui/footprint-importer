package sg.asia21at.webdev.footprintimporter.exception;

public class InnerException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Repeat log id
	 */
	

	public InnerException(String message) {
		super(message);
	}

	public InnerException(String message, Throwable cause) {
		super(message, cause);
	}
}
