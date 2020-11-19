/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import frc.robot.Constants.JoystickConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.RunShooterToRPMCommand;
import frc.robot.commands.FireShooterPistonCommand;
import frc.robot.commands.LiftRunToDialHeight;
import frc.robot.commands.WinchDriveCommand;
import frc.robot.commands.TeleopArcadeDriveCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeStorageSubsystem;
import frc.robot.subsystems.LiftMechanism;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.LiftMechanism.LiftHeights;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	// public final TestMotorSubsystem testMotorSubsystem = new TestMotorSubsystem();
	// public final TestMotorCommand testMotorCommand = new TestMotorCommand(testMotorSubsystem);

	private final IntakeStorageSubsystem intakeStorageSubsystem = new IntakeStorageSubsystem();
	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	private final LiftMechanism liftSubsystem = new LiftMechanism();
	private final DriveSubsystem driveSubsystem = new DriveSubsystem();

	private final Relay cvLight = new Relay(0);

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 * 
	 */

	private Joystick wheel;
	private JoystickButton cvButton;

	private Joystick throttle;

	private Joystick buttonPanel;

	private JoystickButton intakeButton;
	private JoystickButton shooterPistonButton;
	private JoystickButton scissorPositionButton;
	private JoystickButton runWinchButton;

	private JoystickButton scissorDownDial;
	private JoystickButton scissorLowDial;
	private JoystickButton scissorMediumDial;
	private JoystickButton scissorHighDial;


	public RobotContainer() {
		// Configure the button bindings
		configureButtonBindings();
		driveSubsystem.setDefaultCommand(new TeleopArcadeDriveCommand(driveSubsystem));

	}

	public DriveSubsystem getDriveSubsystem() {
		return driveSubsystem;
	}

	public IntakeStorageSubsystem getIntakeStorageSubsystem() {
		return intakeStorageSubsystem;
	}

	public ShooterSubsystem getShooterSubsystem() {
		return shooterSubsystem;
	}

	public LiftMechanism getLiftSubsystem() {
		return liftSubsystem;
	}



	public Joystick getWheel() {
		return wheel;
	}

	public JoystickButton getCVButton() {
		return cvButton;
	}

	public Joystick getThrottle() {
		return throttle;
	}

	public Joystick getButtonPanel() {
		return buttonPanel;
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		
		wheel = new Joystick(JoystickConstants.WHEEL_PORT);
		cvButton = new JoystickButton(wheel, 6);

		throttle = new Joystick(JoystickConstants.THROTTLE_PORT);

		buttonPanel = new Joystick(JoystickConstants.BUTTON_PANEL_PORT);

		scissorDownDial = new JoystickButton(buttonPanel, 1);
		scissorLowDial = new JoystickButton(buttonPanel, 3);
		scissorMediumDial = new JoystickButton(buttonPanel, 5);
		scissorHighDial = new JoystickButton(buttonPanel, 7);

		intakeButton = new JoystickButton(buttonPanel, 2);
		//shooterPistonButton = new JoystickButton(buttonPanel, 4);
		intakeButton2 = new JoystickButton(buttonPanel, 4); 
		scissorPositionButton = new JoystickButton(buttonPanel, 6);
		runWinchButton = new JoystickButton(buttonPanel, 8);

		intakeButton.whenPressed(new InstantCommand(() -> intakeStorageSubsystem.deployIntake(0.7)));
		intakeButton.whenReleased(new InstantCommand(() -> intakeStorageSubsystem.retractIntake()));

		intakeButton2.whenPressed(new InstantCommand(() -> intakeStorageSubsystem.deployIntake(-0.2))); 
		intakeButton2.whenReleased(() -> intakeStorageSubsystem.retractIntake())); 


		//shooterPistonButton.whileHeld(new FireShooterPistonCommand(shooterSubsystem));
		//shooterPistonButton.whenReleased(new InstantCommand(() -> shooterSubsystem.runShooterRPM(0)));

		scissorPositionButton.whenPressed(new LiftRunToDialHeight(liftSubsystem, 0.5));

		runWinchButton.whileHeld(new WinchDriveCommand(liftSubsystem, 0.5));
	}

	public LiftHeights getDialHeight() {
		if (scissorDownDial.get()) return LiftHeights.DOWN;
		if (scissorLowDial.get()) return LiftHeights.LOW;
		if (scissorMediumDial.get()) return LiftHeights.MEDIUM;
		if (scissorHighDial.get()) return LiftHeights.HIGH;
		return LiftHeights.DOWN;
	}

	public void setCVLight(boolean on) {
		if (on) {
			cvLight.set(Value.kForward);
		} else {
			cvLight.set(Value.kOff);
		}
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		driveSubsystem.resetPose();
		return null;
	}
}
