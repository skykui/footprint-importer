package sg.asia21at.webdev.footprintimporter.exception;

public class RepeatLogException extends RuntimeException {
	/**
	 * Repeat log id
	 */
	private static final long serialVersionUID = -4508269894347743534L;

	public RepeatLogException(String message) {
		super(message);
	}

	public RepeatLogException(String message, Throwable cause) {
		super(message, cause);
	}
}
