package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.framework.State;

public class IntakeMechanism extends SubsystemBase {

	private State currentState;

	public IntakeMechanism() {

	}

	public final State INIT = new State("Init") {

		@Override
		public void init() {

		}

		@Override
		public State handleEvent(Enum event) {
			return null;
		}
		
	};

	public final State IDLE = new State("Idle") {

		@Override
		public void init() {

		}

		@Override
		public State handleEvent(Enum event) {
			return null;
		}
		
	};

	public final State DOWN = new State("Down") {

		@Override
		public void init() {

		}

		@Override
		public State handleEvent(Enum event) {
			return null;
		}
		
	};

	public final State EJECT = new State("Eject") {

		@Override
		public void init() {

		}

		@Override
		public State handleEvent(Enum event) {
			return null;
		}
		
	};

	@Override
	public void periodic() {
		super.periodic();
	}

	public void setState(State newState) {
		this.currentState = newState;
		currentState.init();
	}

	public State getCurrentState() {
		return currentState;
	}
}