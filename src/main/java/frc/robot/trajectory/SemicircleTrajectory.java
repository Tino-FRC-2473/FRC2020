package frc.robot.trajectory;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

public class SemicircleTrajectory implements TrajectoryContainer {
	TrajectoryBuilder trajectory;

	/**
	 * Constructs a trajectory to drive in a semicircle.
	 * 
	 * @param position
	 * @param radius in feet
	 */
	public SemicircleTrajectory(TrajectoryBuilder.Position position, double radius) {
		trajectory = new TrajectoryBuilder(TrajectoryBuilder.Type.QUINTIC, position);
		double convertedRadius = Units.feetToMeters(radius);

		trajectory.add(new Waypoint(0, 0, 0));
		trajectory.add(new Waypoint(convertedRadius, -convertedRadius, -90));
		trajectory.add(new Waypoint(0, -2*convertedRadius, -179));
	}

	@Override
	public RamseteCommand getCommand() {
		return trajectory.getRamseteCommand();
	}

}