/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FireShooterPistonCommand;
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
    super(new AutoArcadeDriveCommand(driveSubsystem, 0.3).withTimeout(3),
          new WaitCommand(1),
          new AutoArcadeDriveCommand(driveSubsystem, -0.3).withTimeout(3),
          new WaitCommand(1),
          new AutoIntakeStorageCommand(intakeStorageSubsystem, true),
          new WaitCommand(1),
          new AutoShooterCommand(shooterSubsystem),
          new WaitCommand(1),
          new AutoScissorLiftCommand(liftMechanism),
          new WaitCommand(1),
          new AutoRunWinchCommand(liftMechanism, 0.5));
  }

  
}
