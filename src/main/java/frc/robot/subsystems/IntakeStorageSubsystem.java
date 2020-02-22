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
import frc.robot.Constants.IntakeStorageConstants;

public class IntakeStorageSubsystem extends SubsystemBase {

	private CANSparkMax intakeMotor;
	private CANSparkMax storageMotor;

	private DoubleSolenoid intakePistons;

	/**
	 * Creates a new ServoSubsystem.
	 */
	public IntakeStorageSubsystem() {
		intakeMotor = new CANSparkMax(IntakeStorageConstants.SPARK_INTAKE, MotorType.kBrushless);
		storageMotor = new CANSparkMax(IntakeStorageConstants.SPARK_STORAGE, MotorType.kBrushless);

		intakePistons = new DoubleSolenoid(IntakeStorageConstants.INTAKE_PISTON_FORWARD_PORT, IntakeStorageConstants.INTAKE_PISTON_REVERSE_PORT);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void deployIntake(double power) {
		extendIntakePistons();
		runIntakeMotor(power);
	}

	public void retractIntake() {
		retractIntakePistons();
		runIntakeMotor(0);
	}

	public void runIntakeMotor(double power) {
		intakeMotor.set(power);
	}

	public void extendIntakePistons() {
		intakePistons.set(Value.kOff);
		intakePistons.set(Value.kForward);
	}

	public void retractIntakePistons() {
		intakePistons.set(Value.kOff);
		intakePistons.set(Value.kReverse);
	}

	public void runStorageMotor(double power) {
		storageMotor.set(power);
	}
}
