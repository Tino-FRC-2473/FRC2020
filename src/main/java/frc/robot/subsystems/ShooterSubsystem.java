/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
	/**
	 * Creates a new ExampleSubsystem.
	 */
	CANSparkMax topMotor;
	CANSparkMax bottomMotor;

	Servo feeder;

	public ShooterSubsystem() {
		topMotor = new CANSparkMax(Constants.SPARK_SHOOTER_TOP, MotorType.kBrushless); 
		bottomMotor = new CANSparkMax(Constants.SPARK_SHOOTER_BOTTOM, MotorType.kBrushless);

		feeder = new Servo(Constants.SERVO_SHOOTER_FEEDER);
	}

	public void run(double power){
		topMotor.set(power);
		bottomMotor.set(-power);
	}	

	public void runTopMotor(double power){
		topMotor.set(power); 
	}

	public void runBottomMotor(double power){
		bottomMotor.set(power);
	}

	public void setFeederPosition(double position) {
		feeder.set(position);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
