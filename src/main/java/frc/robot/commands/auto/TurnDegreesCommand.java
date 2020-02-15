package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.trajectory.*;

public class TurnDegreesCommand extends CommandBase {
	
	private double angle;
	private DriveSubsystem driveSubsystem;

	private double initialGyroHeading;

	private final double power = 0.1;
	private final double marginOfError = 3;

	private double finalHeading;

	public TurnDegreesCommand(double angle, DriveSubsystem driveSubsystem) {
		this.angle = angle;
		this.driveSubsystem = driveSubsystem;
	}

	@Override
	public void initialize() {
		initialGyroHeading = driveSubsystem.getHeading();
		
		finalHeading = initialGyroHeading + angle;
		
		if (finalHeading < -179) {
			finalHeading += 180;
		} else if (finalHeading > 179) {
			finalHeading -= 180;
		}
	}

	@Override
	public void execute() {
		if (angle > 0) {
			driveSubsystem.powerDrive(-power, power);
		} else {
			driveSubsystem.powerDrive(power, -power);
		}
	}

	@Override
	public boolean isFinished() {
		return Math.abs(driveSubsystem.getHeading() - finalHeading) < marginOfError;
	}

	@Override
	public void end(boolean interrupted) {
		driveSubsystem.powerDrive(0, 0);
	}
}