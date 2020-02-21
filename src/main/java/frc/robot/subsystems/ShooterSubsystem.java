/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {

	private CANSparkMax leftShooterMotor;
	private CANSparkMax rightShooterMotor;

	// private DoubleSolenoid feederPiston;

	/**
	 * Creates a new ServoSubsystem.
	 */
	public ShooterSubsystem() {
		leftShooterMotor = new CANSparkMax(ShooterConstants.SPARK_SHOOTER_LEFT, MotorType.kBrushless);
		rightShooterMotor = new CANSparkMax(ShooterConstants.SPARK_SHOOTER_RIGHT, MotorType.kBrushless);

		// feederPiston = new DoubleSolenoid(ShooterConstants.FORWARD_SOLENOID_PORT, ShooterConstants.REVERSE_SOLENOID_PORT);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void runShooter(double power) {
		leftShooterMotor.set(power);
		rightShooterMotor.set(-power);
	}

	// public void extendFeeder() {
	// 	feederPiston.set(Value.kOff);
	// 	feederPiston.set(Value.kForward); 
	// }

	// public void retractFeeder() {
	// 	feederPiston.set(Value.kOff);
	// 	feederPiston.set(Value.kReverse);
	// }
}
