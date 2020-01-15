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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
	/**
	 * Creates a new ExampleSubsystem.
	 */
	CANSparkMax topMotor;
	CANSparkMax bottomMotor;

	Servo servoFeeder;

	DoubleSolenoid pistonFeeder; 

	public ShooterSubsystem() {
		topMotor = new CANSparkMax(Constants.SPARK_SHOOTER_TOP, MotorType.kBrushless); 
		bottomMotor = new CANSparkMax(Constants.SPARK_SHOOTER_BOTTOM, MotorType.kBrushless);

		servoFeeder = new Servo(Constants.SERVO_SHOOTER_FEEDER);

		pistonFeeder = new DoubleSolenoid(Constants.FORWARD_SOLENOID_PORT,Constants.REVERSE_SOLENOID_PORT); 
	}

	public void run(double power){
		topMotor.set(power);
		bottomMotor.set(-power);
	}

	public void runDifferentSpeeds(double top, double bottom) {
		topMotor.set(top);
		bottomMotor.set(-bottom);
	}

	public void runTopMotor(double power){
		topMotor.set(power); 
	}

	public void runBottomMotor(double power){
		bottomMotor.set(power);
	}

	public void setFeederPosition(double position) {
		servoFeeder.set(position);
	}

	public void extendFeeder(){
		
		pistonFeeder.set(Value.kOff);
		pistonFeeder.set(Value.kForward); 
		
	}
	public void retractFeeder(){
		pistonFeeder.set(Value.kOff);
		pistonFeeder.set(Value.kReverse);

	}

	public void offFeeder(){
		pistonFeeder.set(Value.kOff); 
	}

	public void testFeeder(){
		pistonFeeder.set(Value.kOff);
		pistonFeeder.set(Value.kForward);
		pistonFeeder.set(Value.kOff);
		pistonFeeder.set(Value.kReverse);
		pistonFeeder.set(Value.kOff);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
