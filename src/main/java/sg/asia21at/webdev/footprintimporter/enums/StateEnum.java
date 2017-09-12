package sg.asia21at.webdev.footprintimporter.enums;

public enum StateEnum {
	SUCCESS(1, "Insert Successful"), INSERT_ERROR(0, "Insert Failed"), INNER_ERROR(-2, "Inner Error"), NO_ENTRY(2, "No entry found");

	private int state;

	private String stateInfo;

	private StateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static StateEnum stateOf(int index) {
		for (StateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
