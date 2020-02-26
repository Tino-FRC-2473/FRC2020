package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.LiftMechanism;
import frc.robot.subsystems.ShooterSubsystem;

public class FireShooterPistonCommand extends SequentialCommandGroup {

    public FireShooterPistonCommand(ShooterSubsystem shooterSubsystem) {
        
       addCommands(new InstantCommand(() -> shooterSubsystem.extendFeeder()), 
                    new WaitCommand(0.1),
                    new InstantCommand(() -> shooterSubsystem.retractFeeder()));
    }
}