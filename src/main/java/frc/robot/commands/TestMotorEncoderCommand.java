/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.TestMotorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class TestMotorEncoderCommand extends CommandBase {
	@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
	private final TestMotorSubsystem m_subsystem;
	public double ticksToMove;
	public double currentPosition; 
	public double power = 0.4;  
	public double targetPosition; 

	

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public TestMotorEncoderCommand(TestMotorSubsystem subsystem) {
		m_subsystem = subsystem;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(subsystem);
	}

	public void driveUsingEncoders(double ticks){
		this.ticksToMove = ticks; 
	}

	public void setTargetPosition(double inches){
		targetPosition = inches * Constants.ENCODER_INCHES_TO_TICKS; 

	}
		

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {

	
		setTargetPosition(2);
		

	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		Robot.testMotor.drive(targetPosition);



	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		
		return false;
	}
}
