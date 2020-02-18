package frc.robot.cv;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.auto.TurnDegreesCommand;
import frc.robot.subsystems.DriveSubsystem;

public class CVDriveCommand extends SequentialCommandGroup {
	
	DriveSubsystem driveSubsystem;

	public CVDriveCommand(double distFromWallInches, DriveSubsystem driveSubsystem) {
		this.driveSubsystem = driveSubsystem;

		double straightDriveDistFeet = 3;

		// CVData cvData = new CVData(true, Units.feetToMeters(8), Units.feetToMeters(-3), 0);
		CVData cvData = Robot.jetson.getCVData();
		double dy = cvData.getDY();
		double dx = cvData.getDX();
		double angle = cvData.getAngle();

		// double signOfDY = (cvData.getDY() < 0) ? -1 : 1;

		// for the following variables, refer to https://www.desmos.com/calculator/hhdwjepfxd
		double thetaStraight = Units.radiansToDegrees(Math.acos(-dy / Math.sqrt(dy*dy + dx*dx)));
		double thetaRobotToWall = angle;
		double thetaRobotToStraight = -(90 - thetaStraight - thetaRobotToWall);

		// double angleToStraightDriveDegrees = signOfDY * (90.0 - Math.abs(Units.radiansToDegrees(Math.atan((cvData.getDX() - Units.feetToMeters(straightDriveDistFeet)) / cvData.getDY()))));
		// angleToTargetDegrees -= 10;
		// double angleToTargetDegrees = -90;
		TurnDegreesCommand turn = new TurnDegreesCommand(thetaRobotToStraight, driveSubsystem);
		CVTrajectory trajectory = new CVTrajectory(distFromWallInches, cvData, thetaRobotToStraight, straightDriveDistFeet);

		addCommands(
			turn,
			new WaitCommand(0.1),
			new InstantCommand(() -> Robot.robotContainer.getDriveSubsystem().resetPose()),
			trajectory.getCommand()
		);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void end(boolean interrupted) {
		driveSubsystem.stop();
	}
}