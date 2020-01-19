package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class TeleopArcadeDriveCommand extends CommandBase {

    DriveSubsystem driveSubsystem;


    public TeleopArcadeDriveCommand(DriveSubsystem subsystem) {
        driveSubsystem = subsystem;
        addRequirements(subsystem);

    }

    @Override
    public void initialize() { 
        
    }

    @Override
    public void execute() {
        driveSubsystem.arcadeDrive();
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}