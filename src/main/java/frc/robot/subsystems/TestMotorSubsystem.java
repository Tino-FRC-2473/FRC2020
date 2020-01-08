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

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestMotorSubsystem extends SubsystemBase {
	/**
	 * Creates a new ExampleSubsystem.
	 */
	CANSparkMax testMotor; 
	double lastReference;

	public TestMotorSubsystem() {
		testMotor = new CANSparkMax(Constants.TEST_PORT, MotorType.kBrushless); 
		testMotor.getPIDController().setP(0.1);

	}

	public void drive(double ticks){
		double startPosition = testMotor.getEncoder().getPosition(); 
		double targetPosition = startPosition + ticks; 

		lastReference = targetPosition;
		testMotor.getPIDController().setReference(targetPosition, ControlType.kPosition);
	}

	public boolean isAtReferencePosition() {
		return Math.abs(testMotor.getEncoder().getPosition() - lastReference) < 0.1;
	}

	public void setPower(double power){
		testMotor.set(power);
	}

	

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
