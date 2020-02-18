package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
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
        driveSubsystem.resetPose();
    }

    @Override
    public void execute() {
        // System.out.println(Robot.robotContainer.getThrottle().getZ());
        // System.out.println(Robot.robotContainer.getWheel().getX());
        if (RobotState.isOperatorControl()) {
            driveSubsystem.arcadeDrive();
        }
        
        // System.out.println(driveSubsystem.getHeading());
        // System.out.println(driveSubsystem.getLeftEncoderDistance() + " " + driveSubsystem.getRightEncoderDistance());
        // System.out.println(driveSubsystem.getWheelSpeeds());
    
		// System.out.println(Units.degreesToRadians(-90));
        // System.out.println(driveSubsystem.getPose());
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