package frc.robot.trajectory;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

public class StraightThenArcTrajectory implements TrajectoryContainer {
	TrajectoryBuilder trajectory;

	public StraightThenArcTrajectory(TrajectoryBuilder.Position position) {
		trajectory = new TrajectoryBuilder(TrajectoryBuilder.Type.QUINTIC, position);

		trajectory.add(new Waypoint(0, 0, 0));
		trajectory.add(new Waypoint(Units.feetToMeters(3), 0, 0));
		trajectory.add(new Waypoint(Units.feetToMeters(7), Units.feetToMeters(-4), -90));
		trajectory.add(new Waypoint(Units.feetToMeters(7), Units.feetToMeters(-6), -90));
	}

	@Override
	public RamseteCommand getCommand() {
		return trajectory.getRamseteCommand();
	}

}