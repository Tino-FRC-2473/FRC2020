/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.TestMotorCommand;
import frc.robot.cv.CVDriveCommand;
import frc.robot.commands.auto.HorizontalShiftCommand;
import frc.robot.cv.CVTrajectory;
import frc.robot.subsystems.TestMotorSubsystem;
import frc.robot.trajectory.*;
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
	// public final TestMotorSubsystem testMotorSubsystem = new TestMotorSubsystem();
	// public final TestMotorCommand testMotorCommand = new TestMotorCommand(testMotorSubsystem);

	// public final static DriveSubsystem driveSubsystem = new DriveSubsystem();
	
	public final static DriveSubsystem driveSubsystem = null;
	// public final ServoSubsystem servoSubsystem = new ServoSubsystem();

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
		// joystick1 = new Joystick(JoystickConstants.JOYSTICK_1_PORT);
		// joystick2 = new Joystick(JoystickConstants.JOYSTICK_2_PORT);
		// wheel = new Joystick(JoystickConstants.WHEEL_PORT);
		// throttle = new Joystick(JoystickConstants.THROTTLE_PORT);

		// buttonPanel = new Joystick(JoystickConstants.BUTTON_PANEL_PORT);
		// buttonPanel2 = new JoystickButton(buttonPanel, 2);
		// buttonPanel4 = new JoystickButton(buttonPanel, 4);
		// buttonPanel6 = new JoystickButton(buttonPanel, 6);
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// Run path following command, then stop at the end.

		// return new SemicircleTrajectory(TrajectoryBuilder.Position.RELATIVE_TO_ROBOT, 1.5).getCommand()
		// return new TwoWaypointTrajectory(TrajectoryBuilder.Position.RELATIVE_TO_ROBOT, TrajectoryBuilder.Direction.FORWARD, new Waypoint(0, 0, 0), new Waypoint(Units.feetToMeters(8), 0, 0)).getCommand()
		// return new StraightThenArcTrajectory(TrajectoryBuilder.Position.RELATIVE_TO_ROBOT).getCommand()
		// return new HorizontalShiftCommand(-5)
		return new CVDriveCommand(0)
		// return new HorizontalShiftTrajectory(-3, TrajectoryBuilder.Position.RELATIVE_TO_ROBOT).getCommand()
					.andThen(() -> driveSubsystem.tankDriveVolts(0, 0));
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
