package frc.robot.framework;

public abstract class State {
	private String name;

	public State(String name) {
		this.name = name;
	}

	public abstract void init();

	@Override
	public String toString() {
		return name;
	}
	/**
	 * Handles any incoming events (represented as an enum)
	 * @param event the event to handle. Can be any Enum
	 * @return the new state to enter based on the event. If state does not need to change, return null
	 */
	public abstract State handleEvent(Enum event);
}