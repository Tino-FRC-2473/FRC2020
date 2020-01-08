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
	
	private TestMotorSubsystem testMotorSubsystem;
	private double power; 

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public TestMotorByTimeCommand(TestMotorSubsystem subsystem, double power) {
		this.power = power; 
		
		testMotorSubsystem = subsystem;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(subsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		testMotorSubsystem.setPower(power);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() { 
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		testMotorSubsystem.setPower(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
