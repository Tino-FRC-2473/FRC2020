package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TestMotorSubsystem;

public class RunShooterCommand extends CommandBase {

	private ShooterSubsystem shooterSubsystem;

	public RunShooterCommand(ShooterSubsystem shooterSubsystem) {
		this.shooterSubsystem = shooterSubsystem;		
	}

	@Override
	public void initialize() {
		
	}

	@Override
	public void execute() {
		double throttlePower = -Robot.robotContainer.getThrottle().getZ();
		System.out.println(throttlePower);
		shooterSubsystem.run(throttlePower);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void end(boolean interrupted) {
		shooterSubsystem.run(0);
	}
}