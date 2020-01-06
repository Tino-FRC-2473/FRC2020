/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.TestMotorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Command;



/**
 * An example command that uses an example subsystem.
 */
public class TestMotorByTimeCommand extends CommandBase {
	@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
	private final TestMotorSubsystem m_subsystem;
	private double m_timeout; 
	private int timeCount = 0; 
	private int targetTime; 
	

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public TestMotorByTimeCommand(TestMotorSubsystem subsystem) {
		
		m_subsystem = subsystem;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(subsystem);
	}
	/**
	 * @param targetTime the targetTime to set
	 */
	public void setTargetTime(int targetTime) {
		this.targetTime = targetTime;
	}



	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		Robot.testMotor.setPower(0.1);
		setTargetTime(4);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		timeCount++; 
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		double timePassed = timeCount * 0.020; 
		return timePassed >=  targetTime ? true : false; 
		
	}
}
