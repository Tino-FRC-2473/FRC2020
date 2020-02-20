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
			addCommands();
			System.out.println("---------------CANNOT SEE TARGETS--------------");
		} else {
			updateWithNewValues();

			addCommands(
				new InstantCommand(() -> driveSubsystem.resetPose()),
				turn,
				new WaitCommand(0.1),
				trajectory.getCommand(),
				new PerpetualCommand(new InstantCommand(driveSubsystem::stop, driveSubsystem))
			);
		}

		
	}

	// @Override
	// public void initialize() {
	// 	updateWithNewValues();
	// }

	public void updateWithNewValues() {
		double straightDriveDistFeet = 3;

		// CVData cvData = new CVData(true, Units.feetToMeters(8), Units.feetToMeters(-3), 0);
		double dy = cvData.getDY();
		double dx = cvData.getDX();
		double angle = cvData.getAngle();

		System.out.println(cvData);

		// double signOfDY = (cvData.getDY() < 0) ? -1 : 1;

		// for the following variables, refer to https://www.desmos.com/calculator/hhdwjepfxd
		double d = Units.inchesToMeters(distFromWallInches) + Units.feetToMeters(straightDriveDistFeet);
		double a = d * Math.sin(Units.degreesToRadians(angle));
		double b = -d * Math.cos(Units.degreesToRadians(angle));

		System.out.println("d: " + d);
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("y straight: " + (a + dy));
		System.out.println("x straight: " + (b + dx));

		double thetaRobotToStraight = Units.radiansToDegrees(Math.atan2(a + dy, b + dx));

		// double angleToStraightDriveDegrees = signOfDY * (90.0 - Math.abs(Units.radiansToDegrees(Math.atan((cvData.getDX() - Units.feetToMeters(straightDriveDistFeet)) / cvData.getDY()))));
		// angleToTargetDegrees -= 10;
		// double angleToTargetDegrees = -90;
		// thetaRobotToStraight *= 1.4;
		turn = new TurnDegreesCommand(thetaRobotToStraight, driveSubsystem);
		trajectory = new CVTrajectory(cvData, thetaRobotToStraight, straightDriveDistFeet, (a + dy), (b + dx));
	
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