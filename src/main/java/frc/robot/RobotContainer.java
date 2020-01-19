/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.TestMotorByTimeCommand;
import frc.robot.commands.TestMotorCommand;
import frc.robot.commands.TestMotorEncoderCommand;
import frc.robot.commands.TestMotorTeleopCommand;
import frc.robot.subsystems.TestMotorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 * 
 * 
 */


public class RobotContainer {
	//private Joystick joystick1; 
	private JoystickButton button1; 
	private JoystickButton button3; 
	private Joystick buttonPanel; 
	// The robot's subsystems and commands are defined here...
	private final TestMotorSubsystem testMotorSubsystem = new TestMotorSubsystem();

	private final TestMotorCommand testMotorCommand = new TestMotorCommand(testMotorSubsystem);

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the button bindings
		configureButtonBindings();
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		buttonPanel = new Joystick(Constants.JOYSTICK_BUTTON_PANEL); 
		button1 = new JoystickButton(buttonPanel, 1); 
		button3 = new JoystickButton(buttonPanel, 3); 
		button1.whenPressed(new TestMotorTeleopCommand(testMotorSubsystem, 0.8)); 
		button3.whenPressed(new TestMotorTeleopCommand(testMotorSubsystem, 0)); 
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		
		
		
		return new InstantCommand(() -> testMotorSubsystem.setPower(-0.5), testMotorSubsystem);
		// return testMotorEncoderCommand;
	}
}
