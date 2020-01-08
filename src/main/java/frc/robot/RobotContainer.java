/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.TestMotorByTimeCommand;
import frc.robot.commands.TestMotorCommand;
import frc.robot.commands.TestMotorEncoderCommand;
import frc.robot.subsystems.TestMotorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	private final TestMotorSubsystem testMotorSubsystem = new TestMotorSubsystem();
	private final TestMotorCommand testMotorCommand = new TestMotorCommand(testMotorSubsystem);
  
	private final DriveSubsystem driveSubsystem = new DriveSubsystem(); 

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 * 
	 */

	private Joystick joystick1; 
	private Joystick joystick2;
	private JoystickButton button1;
	private JoystickButton button2;

	public RobotContainer() {
		// Configure the button bindings
		configureButtonBindings();
	}

	public Joystick getJoystick1() {
		return joystick1; 
	}

	public Joystick getJoystick2() {
		return joystick2; 
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		joystick1 = new Joystick(Constants.JOYSTICK_1_PORT); 
		joystick2 = new Joystick(Constants.JOYSTICK_2_PORT); 
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous		
		
		return testMotorCommand;
		// return testMotorEncoderCommand;
	}
}
