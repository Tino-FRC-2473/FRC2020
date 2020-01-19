package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.TestMotorSubsystem;

public class TestMotorCommand extends SequentialCommandGroup {
    public TestMotorCommand(TestMotorSubsystem subsystem){

            // addCommands( new InstantCommand(subsystem, subsystem.setPower(0.2)), subsystem);
        addCommands(new TestMotorByTimeCommand(subsystem, 0.3).withTimeout(3),
                    new WaitCommand(1), 
                    new TestMotorByTimeCommand(subsystem, -0.1).withTimeout(3),
                    new WaitCommand(1),
                    new TestMotorByTimeCommand(subsystem, 0.2).withTimeout(3),
                    new WaitCommand(1),
                    new TestMotorEncoderCommand(subsystem, 3),
                    new WaitCommand(1),
                    new TestMotorEncoderCommand(subsystem, -3));
    }
}