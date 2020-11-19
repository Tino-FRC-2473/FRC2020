package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeStorageSubsystem;
public class FireShooter2Command extends SequentialCommandGroup {

    public FireShooter2Command(ShooterSubsystem shooterSubsystem, IntakeStorageSubsystem intakeStorageSubsystem) {
        
        System.out.println("Running shooter auto"); 

        addCommands(new InstantCommand(() -> shooterSubsystem.runShooterRPM(0)),
                    new InstantCommand(() -> intakeStorageSubsystem.runStorageMotor(-0.1)),
                     new WaitCommand(3),  
                     new InstantCommand(() -> intakeStorageSubsystem.runStorageMotor(0)), 
                     new WaitCommand(1),
                     new RunShooterToRPMCommand(shooterSubsystem),  
                    new InstantCommand(() -> intakeStorageSubsystem.runStorageMotor(0.3)), 
                    new WaitCommand(3));
    }
}