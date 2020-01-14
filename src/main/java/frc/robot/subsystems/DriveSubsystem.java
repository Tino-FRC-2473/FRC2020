/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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

	public DriveSubsystem() {

		frontLeftMotor = new CANSparkMax(Constants.SPARK_FRONT_LEFT_ID, MotorType.kBrushless);
		backLeftMotor = new CANSparkMax(Constants.SPARK_BACK_LEFT_ID, MotorType.kBrushless);
		frontRightMotor = new CANSparkMax(Constants.SPARK_FRONT_RIGHT_ID, MotorType.kBrushless);  
		backRightMotor = new CANSparkMax(Constants.SPARK_BACK_RIGHT_ID, MotorType.kBrushless);  

		leftSpeedControllerGroup = new SpeedControllerGroup(frontLeftMotor, backLeftMotor); 
		rightSpeedControllerGroup = new SpeedControllerGroup(frontRightMotor, backRightMotor);
		
		differentialDrive = new DifferentialDrive(leftSpeedControllerGroup, rightSpeedControllerGroup); 
		
		initPID();
	}

	public void powerDrive(double leftPower, double rightPower) { 
		leftSpeedControllerGroup.set(leftPower); 
		rightSpeedControllerGroup.set(rightPower);
	}

	public void drivePID(double leftInches, double rightInches) {
		double leftPosition = frontLeftMotor.getEncoder().getPosition(); 
		double rightPosition = frontRightMotor.getEncoder().getPosition();

		double leftTicks = (leftInches * Constants.DRIVE_TICKS_PER_INCH) + leftPosition; 
		double rightTicks = (rightInches * Constants.DRIVE_TICKS_PER_INCH) + rightPosition; 

		frontLeftMotor.getPIDController().setReference(leftTicks, ControlType.kPosition); 
		frontRightMotor.getPIDController().setReference(rightTicks, ControlType.kPosition);
	}

	private void setPID(CANSparkMax motor, double P, double I, double D) {
		motor.getPIDController().setP(P); 
		motor.getPIDController().setI(I); 
		motor.getPIDController().setD(D); 

	}

	private void initPID() {
		setPID(frontLeftMotor, Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D); 
		setPID(frontRightMotor, Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D);
		setPID(backLeftMotor, Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D);
		setPID(backRightMotor, Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D);
	}

	public void tankDrive(){
		differentialDrive.tankDrive(Robot.robotContainer.getJoystick1().getY(), Robot.robotContainer.getJoystick2().getY(), true) ;

	}

	public void arcadeDrive() {
		differentialDrive.arcadeDrive(Robot.robotContainer.getJoystick1().getY(), Robot.robotContainer.getJoystick1().getZ(), true);
	}

	public void stop() {
		differentialDrive.stopMotor();
	}

	

	@Override
	public void periodic() {

		// This method will be called once per scheduler run
	}
}