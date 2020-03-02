package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.DriveConstants;
import frc.robot.cv.CVDriveCommand;
import frc.robot.subsystems.DriveSubsystem;

public class TeleopArcadeDriveCommand extends CommandBase {

	DriveSubsystem driveSubsystem;

	public TeleopArcadeDriveCommand(DriveSubsystem subsystem) {
		driveSubsystem = subsystem;
		addRequirements(subsystem);

	}

	@Override
	public void initialize() {
		driveSubsystem.resetPose();
		Robot.robotContainer.getShooterSubsystem().retractFeeder();
		Robot.robotContainer.getIntakeStorageSubsystem().retractIntake();
		(new LiftRunDownCommand(Robot.robotContainer.getLiftSubsystem(), 0.1)).schedule();
	}

	@Override
	public void execute() {
		if (RobotState.isOperatorControl()) { // safety check to make sure nothing is accidentally happening in auto mode
			driveSubsystem.arcadeDrive();

			if (Robot.robotContainer.getCVButton().get()) {
				new CVDriveCommand(DriveConstants.CAMERA_TO_FRONT_DISTANCE_INCHES,
						Robot.robotContainer.getDriveSubsystem()).schedule();
				end(true);
			}

			Robot.robotContainer.getIntakeStorageSubsystem().runStorageMotor(0.5);

		}

		// System.out.println(driveSubsystem.getHeading());
		// System.out.println(driveSubsystem.getLeftEncoderDistance() + " " +
		// driveSubsystem.getRightEncoderDistance());
		// System.out.println(driveSubsystem.getWheelSpeeds());

		// System.out.println(Units.degreesToRadians(-90));
		// System.out.println(driveSubsystem.getPose());
	}

	@Override
	public void end(boolean interrupted) {
		driveSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}