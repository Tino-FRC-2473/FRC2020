package frc.robot.commands;

import edu.wpi.first.wpilibj.LinearFilter;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.Constants.DriveConstants;
import frc.robot.cv.BallData;
import frc.robot.cv.CVDriveCommand;
import frc.robot.cv.ObstacleData;
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

		Robot.robotContainer.getIntakeStorageSubsystem().runStorageMotor(0.7);
	}

	@Override
	public void execute() {
		if (RobotState.isOperatorControl()) { // safety check to make sure nothing is accidentally happening in auto mode

			if (Robot.robotContainer.getCVButton().get()) {
				new CVDriveCommand(DriveConstants.CAMERA_TO_FRONT_DISTANCE_INCHES,
						Robot.robotContainer.getDriveSubsystem()).schedule();
				end(true);
			}

			if (Robot.robotContainer.getIntakeStorageSubsystem().isIntakeDown()) {
				// System.out.println("INTAKE DOWN")
				ObstacleData obstacleData = Robot.jetson.getObstacleData();

				if (obstacleData != null && obstacleData.getObstacleDistance() < 1.3 && obstacleData.getObstacleDistance() > 0.7) {
					new InstantCommand(() -> Robot.robotContainer.getIntakeStorageSubsystem().retractIntake()).schedule();
				}
			}
			
			if (Robot.robotContainer.getDepthBallPickupButton().get()) {
				if (Robot.robotContainer.getIntakeStorageSubsystem().isIntakeDown()) {
					
					double basePower = 0.2;
					double k = 0.01;
					double maxDeltaPower = 0.3;
					
					BallData depthData = Robot.jetson.getClosestBallData();

					if (depthData != null && depthData.getBallDistance() < 99) { // we can see a ball
						double deltaPower = Math.min(k * Math.abs(depthData.getBallAngle()), maxDeltaPower);
						double leftPower = basePower;
						double rightPower = basePower;

						double diffK = 2.0/3.0;
	
						if (depthData.getBallAngle() > 0) {
							leftPower += deltaPower * diffK;
							rightPower -= deltaPower * (1-diffK);
						} else {
							rightPower += deltaPower * diffK;
							leftPower -= deltaPower * (1-diffK);
						}

						// driveSubsystem.arcadeDriveWithInputs(basePower, k * depthData.getBallAngle());
	
						driveSubsystem.powerDrive(leftPower, rightPower);
					} else {
						// driveSubsystem.arcadeDriveWithInputs(0.2, 0);
						// driveSubsystem.powerDrive(basePower, basePower);
						driveSubsystem.powerDrive(0, 0);
					}				
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