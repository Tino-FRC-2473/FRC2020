package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.IntakeStorageSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ManualIntakeCommand extends SequentialCommandGroup {

    public ManualIntakeCommand(IntakeStorageSubsystem intakeStorageSubsystem) {
        
       addCommands(new InstantCommand(() -> intakeStorageSubsystem.deployIntake(0.7)),
            new WaitCommand(1),
           new InstantCommand(() -> intakeStorageSubsystem.runStorageMotor(0.3)).withTimeout(3), 
                    new WaitCommand(1),
                    new InstantCommand(() -> intakeStorageSubsystem.retractIntake())
                   );
    }
}