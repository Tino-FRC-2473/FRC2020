package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.trajectory.*;

public class HorizontalShiftCommand extends SequentialCommandGroup {
	/**
	 * Constructs an autonomous command to shift the robot's position horizontally.
	 * @param shift in feet
	 */
	public HorizontalShiftCommand(double shift) {
		double convertedShift = Units.feetToMeters(shift);

		TrajectoryBuilder forwardBuilder = new TrajectoryBuilder(TrajectoryBuilder.Type.QUINTIC, TrajectoryBuilder.Position.ABSOLUTE,
				TrajectoryBuilder.Direction.FORWARD);
		forwardBuilder.add(new Waypoint(0, 0, 0));
		forwardBuilder.add(new Waypoint(Units.feetToMeters(4), convertedShift/2, 0));
		// forwardBuilder.add(new Waypoint(Units.feetToMeters(6), convertedShift/2, 0));

		TrajectoryBuilder backwardBuilder = new TrajectoryBuilder(TrajectoryBuilder.Type.QUINTIC, TrajectoryBuilder.Position.ABSOLUTE,
				TrajectoryBuilder.Direction.REVERSE);
		backwardBuilder.add(new Waypoint(Units.feetToMeters(4), convertedShift/2, 0));
		backwardBuilder.add(new Waypoint(Units.feetToMeters(0), convertedShift, 0));
		// backwardBuilder.add(new Waypoint(0, convertedShift, 0));





		addCommands(
			forwardBuilder.getRamseteCommand(),
			// new WaitCommand(1),
			backwardBuilder.getRamseteCommand()
		);
	}
}