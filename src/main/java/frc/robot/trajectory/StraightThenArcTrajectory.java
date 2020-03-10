package frc.robot.trajectory;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

public class StraightThenArcTrajectory implements TrajectoryContainer {
	TrajectoryBuilder trajectory;

	public StraightThenArcTrajectory(TrajectoryBuilder.Position position) {
		trajectory = new TrajectoryBuilder(TrajectoryBuilder.Type.QUINTIC, position);

		trajectory.add(new Waypoint(0, 0, 0));
		trajectory.add(new Waypoint(Units.feetToMeters(2), 0, 0));
		trajectory.add(new Waypoint(Units.feetToMeters(5), Units.feetToMeters(-3), -90));
		trajectory.add(new Waypoint(Units.feetToMeters(5), Units.feetToMeters(-5), -90));
	}

	@Override
	public Command getCommand() {
		return trajectory.getRamseteCommand();
	}

}