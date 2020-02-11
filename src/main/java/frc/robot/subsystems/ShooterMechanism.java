package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.framework.State;

public class ShooterMechanism extends SubsystemBase {

	private State currentState;

	public ShooterMechanism() {

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

	public final State RAMP_UP = new State("Ramp up") {

		@Override
		public void init() {

		}

		@Override
		public State handleEvent(Enum event) {
			return null;
		}
		
	};

	public final State SHOOTING = new State("Shooting") {

		@Override
		public void init() {

		}

		@Override
		public State handleEvent(Enum event) {
			return null;
		}
		
	};

	public final State REARMING = new State("Rearming") {

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