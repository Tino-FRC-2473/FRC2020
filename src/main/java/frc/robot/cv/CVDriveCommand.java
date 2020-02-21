package frc.robot.cv;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.auto.TurnDegreesCommand;
import frc.robot.subsystems.DriveSubsystem;

public class CVDriveCommand extends SequentialCommandGroup {
	
	DriveSubsystem driveSubsystem;
	double distFromWallInches;

	CVData cvData;

	TurnDegreesCommand turn;
	CVTrajectory trajectory;

	public CVDriveCommand(double distFromWallInches, DriveSubsystem driveSubsystem) {
		this.driveSubsystem = driveSubsystem;
		this.distFromWallInches = distFromWallInches;

		System.out.println("new command group instantiated");
		
		cvData = Robot.jetson.getCVData();

		if (!cvData.canSeeTarget()) {
			addCommands(new PerpetualCommand(new InstantCommand(driveSubsystem::stop, driveSubsystem))); // wait for driver to release CV button
			System.out.println("---------------CANNOT SEE TARGETS--------------");
		} else {
			updateWithNewValues();

			addCommands(
				new InstantCommand(() -> driveSubsystem.resetPose()),
				turn,
				new InstantCommand(() -> System.out.println("POSE: " + driveSubsystem.getPose())),
				new WaitCommand(0.1),
				trajectory.getCommand(),
				new PerpetualCommand(new InstantCommand(driveSubsystem::stop, driveSubsystem)) // wait for driver to release CV button
			);
		}

		
	}

	// @Override
	// public void initialize() {
	// 	updateWithNewValues();
	// }

	public void updateWithNewValues() {
		double straightDriveDistFeet = 3;

		System.out.println(cvData);


		// for the following variables, refer to https://www.desmos.com/calculator/hhdwjepfxd
		double d = Units.inchesToMeters(distFromWallInches) + Units.feetToMeters(straightDriveDistFeet);

		double x_d = -d * Math.cos(Units.degreesToRadians(cvData.getAngle())) + cvData.getDX();
		double y_d = d * Math.sin(Units.degreesToRadians(cvData.getAngle())) + cvData.getDY();

		double thetaRobotToStraight = Units.radiansToDegrees(Math.atan2(y_d, x_d));

		turn = new TurnDegreesCommand(thetaRobotToStraight, driveSubsystem);
		trajectory = new CVTrajectory(cvData, thetaRobotToStraight, straightDriveDistFeet, distFromWallInches);
	
		System.out.println("-------- updated CVDriveCommand with new values");
	}

	@Override
	public boolean isFinished() {
		return !Robot.robotContainer.getCVButton().get();
	}

	@Override
	public void end(boolean interrupted) {
		System.out.println("/////////// finished CVDriveCommand");
		driveSubsystem.stop();
	}
}