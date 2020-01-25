package frc.robot.trajectory;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

public class HorizontalShiftTrajectory implements TrajectoryContainer {
	TrajectoryBuilder trajectory;

	/**
	 * Constructs an trajectory to shift the robot's position horizontally.
	 * @param shift in feet
	 */
	public HorizontalShiftTrajectory(double shift, TrajectoryBuilder.Position position) {

		double convertedShift = Units.feetToMeters(shift);
		trajectory = new TrajectoryBuilder(TrajectoryBuilder.Type.CLAMPED_CUBIC, position);

		trajectory.setInitialWaypoint(new Waypoint(0, 0, 0));
		// trajectory.add(new Waypoint(2, convertedShift/2));
		trajectory.setFinalWaypoint(new Waypoint(0, convertedShift, 0));
	}

	@Override
	public RamseteCommand getCommand() {
		return trajectory.getRamseteCommand();
	}

}