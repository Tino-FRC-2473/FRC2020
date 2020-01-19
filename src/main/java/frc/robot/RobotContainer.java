/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.TestMotorCommand;
import frc.robot.subsystems.TestMotorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ServoSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	public final TestMotorSubsystem testMotorSubsystem = new TestMotorSubsystem();
	public final TestMotorCommand testMotorCommand = new TestMotorCommand(testMotorSubsystem);

	public final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public final ServoSubsystem servoSubsystem = new ServoSubsystem();

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 * 
	 */

	private Joystick joystick1;
	private Joystick joystick2;
	private Joystick wheel;
	private Joystick throttle;
	private Joystick buttonPanel;
	private JoystickButton buttonPanel2;
	private JoystickButton buttonPanel4;
	private JoystickButton buttonPanel6;

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

	public Joystick getWheel() {
		return wheel;
	}

	public Joystick getThrottle() {
		return throttle;
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
		wheel = new Joystick(Constants.WHEEL_PORT);
		throttle = new Joystick(Constants.THROTTLE_PORT);

		buttonPanel = new Joystick(Constants.BUTTON_PANEL_PORT);
		buttonPanel2 = new JoystickButton(buttonPanel, 2);
		buttonPanel4 = new JoystickButton(buttonPanel, 4);
		buttonPanel6 = new JoystickButton(buttonPanel, 6);
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

	public Joystick getButtonPanel() {
		return buttonPanel;
	}

	public JoystickButton getButtonPanel2() {
		return buttonPanel2;
	}

	public JoystickButton getButtonPanel4() {
		return buttonPanel4;
	}

	public JoystickButton getButtonPanel6() {
		return buttonPanel6;
	}
}
