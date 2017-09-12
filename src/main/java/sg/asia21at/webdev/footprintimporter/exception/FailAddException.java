package sg.asia21at.webdev.footprintimporter.exception;

public class FailAddException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FailAddException(String message) {
		super(message);
	}

	public FailAddException(String message, Throwable cause) {
		super(message, cause);
	}
}
