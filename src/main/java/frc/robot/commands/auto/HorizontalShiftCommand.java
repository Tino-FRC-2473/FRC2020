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
		addCommands(
			new TwoWaypointTrajectory(TrajectoryBuilder.Position.ABSOLUTE, 
								TrajectoryBuilder.Direction.FORWARD, 
								new Waypoint(0, 0, 0), 
								new Waypoint(2, convertedShift, 0)).getCommand(),

			new TwoWaypointTrajectory(TrajectoryBuilder.Position.ABSOLUTE, 
								TrajectoryBuilder.Direction.REVERSE, 
								new Waypoint(2, convertedShift, 0), 
								new Waypoint(0, convertedShift, 0)).getCommand()
		);
	}
}