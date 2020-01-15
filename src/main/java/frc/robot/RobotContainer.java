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
import frc.robot.commands.RunShooterCommand;
import frc.robot.commands.TestMotorCommand;
import frc.robot.subsystems.TestMotorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	public final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	// private final TestMotorSubsystem testMotorSubsystem = new TestMotorSubsystem();

	// private final TestMotorCommand testMotorCommand = new TestMotorCommand(testMotorSubsystem);
	private final RunShooterCommand runShooterCommand = new RunShooterCommand(shooterSubsystem);

	// private final DriveSubsystem driveSubsystem = new DriveSubsystem();

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 * 
	 */

	private Joystick joystick1;
	private Joystick joystick2;

	private Joystick throttle;
	private Joystick buttonPanel;
	private Joystick buttonPanelShooter; 

	private JoystickButton button2;
	private JoystickButton button4;
	private JoystickButton button6;
	private JoystickButton button8;

	private JoystickButton button1;
	private JoystickButton button3;
	private JoystickButton button5;
	private JoystickButton button7;

	private JoystickButton stopButton;

	private JoystickButton buttonShooterTop; 
	private JoystickButton buttonShooterBottom; 

	

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

	public Joystick getThrottle() {
		return throttle;
	}

	public Joystick getButtonPanel() {
		return buttonPanel;
	}

	public Joystick getButtonPanelShooter(){
		return buttonPanelShooter; 
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		// joystick1 = new Joystick(Constants.JOYSTICK_1_PORT);
		// joystick2 = new Joystick(Constants.JOYSTICK_2_PORT);
		throttle = new Joystick(Constants.THROTTLE_PORT);

		buttonPanel = new Joystick(Constants.BUTTON_PANEL_PORT);
		// buttonPanelShooter = new Joystick(Constants.BUTTON_PANEL_PORT_SHOOTER);
		// System.out.println("Button Pannel Shooter Port: " + Constants.BUTTON_PANEL_PORT_SHOOTER);



		button2 = new JoystickButton(buttonPanel, 2);
		button4 = new JoystickButton(buttonPanel, 4);
		button6 = new JoystickButton(buttonPanel, 6);
		button8 = new JoystickButton(buttonPanel, 8);

		button1 = new JoystickButton(buttonPanel, 1);
		button3 = new JoystickButton(buttonPanel, 3);
		button5 = new JoystickButton(buttonPanel, 5);
		button7 = new JoystickButton(buttonPanel, 7);

		stopButton = new JoystickButton(throttle, 7);

		// buttonShooterTop = new JoystickButton(buttonPanelShooter, 2);
		// System.out.println("Top Shooter Motor Button Port:  2");
		
		
		// buttonShooterBottom = new JoystickButton(buttonPanelShooter, 4);
		// System.out.println("Bottom Shooter Motor Button Port: 4");
		

		// button2.whenReleased(() -> shooterSubsystem.setFeederPosition(0));
		// button4.whenReleased(() -> shooterSubsystem.setFeederPosition(0.5));
		// button6.whenReleased(() -> shooterSubsystem.setFeederPosition(0.7));
		// button8.whenReleased(() -> shooterSubsystem.setFeederPosition(1));

		button2.whenReleased(() -> shooterSubsystem.extendFeeder());
		button4.whenReleased(() -> shooterSubsystem.retractFeeder());
		button6.whenReleased(() -> shooterSubsystem.offFeeder());
		button8.whenReleased(() -> shooterSubsystem.testFeeder());

		button1.whenPressed(() -> shooterSubsystem.run(0.5));
		button3.whenPressed(() -> shooterSubsystem.run(0.65));
		button5.whenPressed(() -> shooterSubsystem.run(0.75));
		button7.whenPressed(() -> shooterSubsystem.run(0.8));

		stopButton.whenPressed(() -> shooterSubsystem.run(0));

		// 0.6, 0.8
		// 0.7, 0.8
		// 0.8, 0.6
		// 0.8, 0.7

		// button1.whenPressed(() -> shooterSubsystem.runDifferentSpeeds(0.6, 0.8));
		// button3.whenPressed(() -> shooterSubsystem.runDifferentSpeeds(0.7, 0.8));
		// button5.whenPressed(() -> shooterSubsystem.runDifferentSpeeds(0.8, 0.6));
		// button7.whenPressed(() -> shooterSubsystem.runDifferentSpeeds(0.8, 0.7));





		// buttonShooterTop.whileHeld(() ->  shooterSubsystem.runTopMotor(0.8));
		// buttonShooterBottom.whileHeld(() -> shooterSubsystem.runBottomMotor(0.8));
		
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous

		return runShooterCommand;
		// return testMotorEncoderCommand;
	}
}
