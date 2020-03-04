/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeStorageSubsystem;
import frc.robot.subsystems.LiftMechanism;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SequentialTestCommand extends SequentialCommandGroup {

	/**
	 * Creates a new SequentialTest.
	 */
	public SequentialTestCommand(DriveSubsystem driveSubsystem, IntakeStorageSubsystem intakeStorageSubsystem,
			ShooterSubsystem shooterSubsystem, LiftMechanism liftMechanism) {
		// Add your commands in the super() call, e.g.
		// super(new FooCommand(), new BarCommand());
		super(
			new RunCommand(() -> driveSubsystem.powerDrive(0.3, 0.3), driveSubsystem).withTimeout(3),
			new WaitCommand(1),
			new RunCommand(() -> driveSubsystem.powerDrive(-0.3, -0.3), driveSubsystem).withTimeout(3),
			new WaitCommand(1),
			new AutoIntakeStorageCommand(intakeStorageSubsystem),
			new WaitCommand(1),
			new AutoShooterCommand(shooterSubsystem),
			new WaitCommand(1),
			new AutoScissorLiftCommand(liftMechanism),
			new WaitCommand(1),
			new RunCommand(() -> liftMechanism.runWinch(0.5), liftMechanism).withTimeout(3));
	}

}
