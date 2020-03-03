package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.DriveConstants;
import frc.robot.cv.BallData;
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

		Robot.robotContainer.getIntakeStorageSubsystem().runStorageMotor(0.5);
	}

	@Override
	public void execute() {
		if (RobotState.isOperatorControl()) { // safety check to make sure nothing is accidentally happening in auto mode

			if (Robot.robotContainer.getCVButton().get()) {
				new CVDriveCommand(DriveConstants.CAMERA_TO_FRONT_DISTANCE_INCHES,
						Robot.robotContainer.getDriveSubsystem()).schedule();
				end(true);
			} else if (Robot.robotContainer.getDepthBallPickupButton().get()) {
				if (Robot.robotContainer.getIntakeStorageSubsystem().isIntakeDown()) {
					
					double basePower = 0.1;
					double k = 0.05;
					double maxDeltaPower = 0.3;
					
					BallData depthData = Robot.jetson.getClosestBallData();

					double deltaPower = Math.min(k * Math.abs(depthData.getBallAngle()), maxDeltaPower);
					double leftPower = basePower;
					double rightPower = basePower;

					if (depthData.getBallAngle() < 0) { // check if this sign is correct
						leftPower += deltaPower;
					} else {
						rightPower += deltaPower;
					}

					driveSubsystem.powerDrive(leftPower, rightPower);
				}
			} else {
				driveSubsystem.arcadeDrive();
			}
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