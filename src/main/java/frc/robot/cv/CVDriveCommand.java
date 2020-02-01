package frc.robot.cv;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.TurnDegreesCommand;

public class CVDriveCommand extends SequentialCommandGroup {
	
	public CVDriveCommand(double distFromWallInches) {
		
		double straightDriveDistFeet = 3;

		CVData cvData = new CVData(true, Units.feetToMeters(8), Units.feetToMeters(-3), 0);

		double signOfDY = (cvData.getDY() < 0) ? -1 : 1;
		double angleToTargetDegrees = signOfDY * (90.0 - Math.abs(Units.radiansToDegrees(Math.atan((cvData.getDX() - Units.feetToMeters(straightDriveDistFeet)) / cvData.getDY()))));
		// angleToTargetDegrees -= 10;
		// double angleToTargetDegrees = -90;
		TurnDegreesCommand turn = new TurnDegreesCommand(angleToTargetDegrees, RobotContainer.driveSubsystem);
		CVTrajectory trajectory = new CVTrajectory(distFromWallInches, cvData, angleToTargetDegrees, straightDriveDistFeet);

		addCommands(
			turn,
			new WaitCommand(0.1),
			trajectory.getCommand()
		);
	}
}