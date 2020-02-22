/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;

import frc.robot.commands.LiftCommand;
import frc.robot.commands.LiftRunDownCommand;
import frc.robot.commands.LiftRunToEncoder;
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

	public final static IntakeStorageSubsystem intakeStorageSubsystem = new IntakeStorageSubsystem();
	public final static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public final static LiftMechanism liftMech = new LiftMechanism();
	private final DriveSubsystem driveSubsystem = new DriveSubsystem();

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 * 
	 */

	private Joystick wheel;
	private JoystickButton cvButton;

	private Joystick throttle;
	private JoystickButton runShooterButton;

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

		// joystick1Trigger.whenPressed(new InstantCommand(() -> shooterSubsystem.runShooter(0.6)));
		// joystick1Trigger.whenReleased(new InstantCommand(() -> shooterSubsystem.runShooter(0)));

		// joystick1PrimaryButton.whenPressed(new InstantCommand(() -> intakeStorageSubsystem.runIntakeMotor(0.7)));
		// joystick1PrimaryButton.whenReleased(new InstantCommand(() -> intakeStorageSubsystem.runIntakeMotor(0)));
		
		// joystick1_10.whenPressed(new InstantCommand(() -> intakeStorageSubsystem.runStorageMotor(0)));
		// joystick1_11.whenPressed(new InstantCommand(() -> intakeStorageSubsystem.runStorageMotor(0.5)));
		
		wheel = new Joystick(JoystickConstants.WHEEL_PORT);
		cvButton = new JoystickButton(wheel, 6);

		throttle = new Joystick(JoystickConstants.THROTTLE_PORT);
		runShooterButton = new JoystickButton(throttle, 7);

		buttonPanel = new Joystick(JoystickConstants.BUTTON_PANEL_PORT);

		intakeButton = new JoystickButton(buttonPanel, 2);
		shooterPistonButton = new JoystickButton(buttonPanel, 4);
		scissorPositionButton = new JoystickButton(buttonPanel, 6);
		runWinchButton = new JoystickButton(buttonPanel, 8);


		intakeButton.whenPressed(new InstantCommand(() -> intakeStorageSubsystem.deployIntake(0.7)));
		intakeButton.whenReleased(new InstantCommand(() -> intakeStorageSubsystem.retractIntake()));

		
		scissorPositionButton.whenPressed(new LiftRunToEncoder(liftMech, getDialHeight().getValue(), -0.5));


		scissorDownDial = new JoystickButton(buttonPanel, 1);
		scissorLowDial = new JoystickButton(buttonPanel, 3);
		scissorMediumDial = new JoystickButton(buttonPanel, 5);
		scissorHighDial = new JoystickButton(buttonPanel, 7);

		//-108.76 ticks -> 4ft 3 inches (with -15)
		//-229.581146 ticks -> 5ft 3 inches (with -15)
		//-533.91 ticks -> 6ft 7 inches (with -15)

		// buttonPanel2.whenPressed(new LiftCommand(liftMech,-229.581146));
		// buttonPanel4.whenPressed(new LiftCommand(liftMech, -108.76));//-229.581146
		// buttonPanel5.whenPressed(new LiftCommand(liftMech, -533.91));
		// buttonPanel3.whileHeld(new WinchDriveCommand(liftMech,0.5)); 
		// buttonPanel1.whenPressed(new LiftRunDownCommand(liftMech, 0.1)); //runDown power must be positive
	}

	public LiftHeights getDialHeight() {
		if (scissorDownDial.get()) return LiftHeights.DOWN;
		if (scissorLowDial.get()) return LiftHeights.LOW;
		if (scissorMediumDial.get()) return LiftHeights.MEDIUM;
		if (scissorHighDial.get()) return LiftHeights.HIGH;
		return null;
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
