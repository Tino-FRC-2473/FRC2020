package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.LiftMechanism;
import frc.robot.subsystems.TestMotorSubsystem;

public class LiftCommand extends SequentialCommandGroup {

    public LiftCommand(LiftMechanism liftMech, double goHeight) {
        
       addCommands(new LiftRunDownCommand(liftMech, 0.1), 
                    new LiftRunToHeight(liftMech, goHeight, -0.1));
    }
}