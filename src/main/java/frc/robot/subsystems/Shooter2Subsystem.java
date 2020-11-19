/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter2Subsystem extends SubsystemBase {

	private CANSparkMax leftShooterMotor;
	private CANSparkMax rightShooterMotor;

	

	/**
	 * Creates a new ServoSubsystem.
	 */
	public Shooter2Subsystem() {
		leftShooterMotor = new CANSparkMax(ShooterConstants.SPARK_SHOOTER_LEFT, MotorType.kBrushless);
		rightShooterMotor = new CANSparkMax(ShooterConstants.SPARK_SHOOTER_RIGHT, MotorType.kBrushless);


		double kP = 0;
		double kI = 2e-7;
		double kD = 0;

		leftShooterMotor.getPIDController().setP(kP);
		leftShooterMotor.getPIDController().setI(kI);
		leftShooterMotor.getPIDController().setD(kD);

		rightShooterMotor.getPIDController().setP(kP);
		rightShooterMotor.getPIDController().setI(kI);
		rightShooterMotor.getPIDController().setD(kD);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void runShooterPower(double power) {
		leftShooterMotor.set(power);
		rightShooterMotor.set(-power);
	}

	public void runShooterRPM(double rpm) { // 5834
		leftShooterMotor.getPIDController().setReference(rpm, ControlType.kVelocity);
		rightShooterMotor.getPIDController().setReference(-rpm, ControlType.kVelocity);
	}


    public double getLeftVelocity() {
		return leftShooterMotor.getEncoder().getVelocity();
	}

	public double getRightVelocity() {
		return leftShooterMotor.getEncoder().getVelocity();
    }
    
    
}