/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeStorageConstants;

public class IntakeStorageSubsystem extends SubsystemBase {

	private CANSparkMax intakeMotor;
	private CANSparkMax storageMotor;

	/**
	 * Creates a new ServoSubsystem.
	 */
	public IntakeStorageSubsystem() {
		intakeMotor = new CANSparkMax(IntakeStorageConstants.SPARK_INTAKE, MotorType.kBrushless);
		storageMotor = new CANSparkMax(IntakeStorageConstants.SPARK_STORAGE, MotorType.kBrushless);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void runIntakeMotor(double power) {
		intakeMotor.set(power);
	}

	public void runStorageMotor(double power) {
		storageMotor.set(power);
	}
}
