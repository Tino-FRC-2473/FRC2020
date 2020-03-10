package frc.robot.trajectory;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

public class TwoWaypointTrajectory implements TrajectoryContainer {
	TrajectoryBuilder trajectory;

	/**
	 * Constructs a trajectory to perform a horizontal shift.
	 * @param position
	 * @param feet
	 */
	public TwoWaypointTrajectory(TrajectoryBuilder.Position position, TrajectoryBuilder.Direction direction, Waypoint start, Waypoint end) {
		trajectory = new TrajectoryBuilder(TrajectoryBuilder.Type.CLAMPED_CUBIC, position, direction);

		trajectory.setInitialWaypoint(start);
		trajectory.setFinalWaypoint(end);
	}

	@Override
	public Command getCommand() {
		return trajectory.getRamseteCommand();
	}

}