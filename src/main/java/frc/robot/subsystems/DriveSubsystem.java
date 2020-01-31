/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;

public class DriveSubsystem extends SubsystemBase {
	/**
	 * Creates a new ExampleSubsystem.
	 */

	CANSparkMax frontLeftMotor; 
	CANSparkMax backLeftMotor; 
	CANSparkMax frontRightMotor; 
	CANSparkMax backRightMotor; 

	SpeedControllerGroup leftSpeedControllerGroup;
	SpeedControllerGroup rightSpeedControllerGroup; 
	DifferentialDrive differentialDrive;

	AHRS gyro;

	DifferentialDriveOdometry odometry;

	public DriveSubsystem() {

		frontLeftMotor = new CANSparkMax(DriveConstants.SPARK_FRONT_LEFT_ID, MotorType.kBrushless);
		backLeftMotor = new CANSparkMax(DriveConstants.SPARK_BACK_LEFT_ID, MotorType.kBrushless);
		frontRightMotor = new CANSparkMax(DriveConstants.SPARK_FRONT_RIGHT_ID, MotorType.kBrushless);  
		backRightMotor = new CANSparkMax(DriveConstants.SPARK_BACK_RIGHT_ID, MotorType.kBrushless);  

		leftSpeedControllerGroup = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
		rightSpeedControllerGroup = new SpeedControllerGroup(frontRightMotor, backRightMotor);
		
		leftSpeedControllerGroup.setInverted(true);
		rightSpeedControllerGroup.setInverted(true);

		gyro = new AHRS(SPI.Port.kMXP);
		differentialDrive = new DifferentialDrive(leftSpeedControllerGroup, rightSpeedControllerGroup); 
		odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()), new Pose2d(0, 0, new Rotation2d(0)));

		differentialDrive.setSafetyEnabled(false);

		resetPose();
	}

	public void powerDrive(double leftPower, double rightPower) { 
		leftSpeedControllerGroup.set(leftPower); 
		rightSpeedControllerGroup.set(-rightPower);
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		leftSpeedControllerGroup.setVoltage(leftVolts);
		rightSpeedControllerGroup.setVoltage(-rightVolts);

		// System.out.println(leftVolts);
		System.out.println(System.currentTimeMillis() + "," + getHeading());
	}

	public void tankDrive(){
		differentialDrive.tankDrive(Robot.robotContainer.getJoystick1().getY(), Robot.robotContainer.getJoystick2().getY(), true);
	}

	public void arcadeDrive() {
		differentialDrive.arcadeDrive(-Robot.robotContainer.getThrottle().getZ(), Robot.robotContainer.getWheel().getX());
	}

	public void stop() {
		differentialDrive.stopMotor();
	}

	public double getLeftEncoderDistance() {
		return -frontLeftMotor.getEncoder().getPosition() * DriveConstants.DRIVE_METERS_PER_ROTATION;
	}

	public double getRightEncoderDistance() {
		return frontRightMotor.getEncoder().getPosition() * DriveConstants.DRIVE_METERS_PER_ROTATION;
	}

	public void resetEncoders() {
		frontLeftMotor.getEncoder().setPosition(0);
		frontRightMotor.getEncoder().setPosition(0);
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void resetPose() {
		resetGyro();
		resetEncoders();
		odometry.resetPosition(new Pose2d(0, 0, new Rotation2d(0)), Rotation2d.fromDegrees(getHeading()));
	}

	// Trajectory methods

	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		double leftRateMetersPerSecond = -frontLeftMotor.getEncoder().getVelocity() * DriveConstants.DRIVE_METERS_PER_ROTATION / 60;
		double rightRateMetersPerSecond = frontRightMotor.getEncoder().getVelocity() * DriveConstants.DRIVE_METERS_PER_ROTATION / 60;

		// System.out.print(System.currentTimeMillis() + "," + leftRateMetersPerSecond + ",");

		return new DifferentialDriveWheelSpeeds(leftRateMetersPerSecond, rightRateMetersPerSecond);
	}

	public double getHeading() {
		return -Math.IEEEremainder(gyro.getAngle(), 360);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
		odometry.update(Rotation2d.fromDegrees(getHeading()), getLeftEncoderDistance(), getRightEncoderDistance());
		// System.out.println(getPose());
	}

	public Pose2d getPose() {
		return odometry.getPoseMeters();
	}
}