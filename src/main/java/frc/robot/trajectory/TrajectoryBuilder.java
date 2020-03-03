package frc.robot.trajectory;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Robot;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;

public class TrajectoryBuilder {

	public enum Type {
		CLAMPED_CUBIC, QUINTIC;
	}

	public enum Direction {
		FORWARD, REVERSE;
	}

	public enum Position {
		RELATIVE_TO_ROBOT, ABSOLUTE;
	}

	private Direction direction;
	private Type type;
	private Position position;

	private ArrayList<Pose2d> quinticPoseArray;

	private Pose2d startClampedCubicPose;
	private ArrayList<Translation2d> clampedCubicTranslationArray;
	private Pose2d endClampedCubicPose;

	public TrajectoryBuilder(Type type, Position position, Direction direction) {
		this.direction = direction;
		this.position = position;
		this.type = type;

		quinticPoseArray = new ArrayList<>();
		clampedCubicTranslationArray = new ArrayList<>();
	}

	/**
	 * Constructor with Type and Position. Defaults to Position being  relative to robot
	 */
	public TrajectoryBuilder(Type type, Position position) {
		this(type, position, Direction.FORWARD);
	}

	/**
	 * Constructor with Type. Defaults to Position being relative to robot and Direction as forward
	 */
	public TrajectoryBuilder(Type type) {
		this(type, Position.RELATIVE_TO_ROBOT, Direction.FORWARD);
	}

	public TrajectoryBuilder setInitialWaypoint(Waypoint waypoint) {
		if (type == Type.CLAMPED_CUBIC) {
			startClampedCubicPose = waypoint.convertToPose2d();
		} else {
			throw new IllegalArgumentException("Cannot set initial waypoint of quintic trajectory");
		}
		
		return this;
	}

	public TrajectoryBuilder add(Waypoint waypoint) {
		if (type == Type.CLAMPED_CUBIC) {
			clampedCubicTranslationArray.add(waypoint.convertToTranslation2d());
		} else {
			quinticPoseArray.add(waypoint.convertToPose2d());
		}

		return this;
	}

	public TrajectoryBuilder setFinalWaypoint(Waypoint waypoint) {
		if (type == Type.CLAMPED_CUBIC) {
			endClampedCubicPose = waypoint.convertToPose2d();
		} else {
			throw new IllegalArgumentException("Cannot set final waypoint of quintic trajectory");
		}

		return this;
	}

	public RamseteCommand getRamseteCommand() {
		var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(DriveConstants.KS_VOLTS,
										DriveConstants.KV_VOLT_SECONDS_PER_METER,
										DriveConstants.KA_VOLT_SECONDS_SQUARED_PER_METER),
										DriveConstants.K_DRIVE_KINEMATICS,
           								10);

		// Create config for trajectory
		TrajectoryConfig config =
			new TrajectoryConfig(AutoConstants.K_MAX_SPEED_METERS_PER_SECOND,
				AutoConstants.K_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED)
				// Add kinematics to ensure max speed is actually obeyed
				.setKinematics(DriveConstants.K_DRIVE_KINEMATICS)
				// Apply the voltage constraint
				.addConstraint(autoVoltageConstraint)
				.setReversed(direction == Direction.REVERSE);

		Trajectory trajectory;
		
		if (type == Type.CLAMPED_CUBIC) {
			trajectory = TrajectoryGenerator.generateTrajectory(startClampedCubicPose, 
																clampedCubicTranslationArray, 
																endClampedCubicPose, 
																config);
		} else {
			trajectory = TrajectoryGenerator.generateTrajectory(quinticPoseArray, config);
		}

		if (position == Position.RELATIVE_TO_ROBOT) {
			trajectory = trajectory.relativeTo(Robot.robotContainer.getDriveSubsystem().getPose());
		}

		RamseteCommand ramseteCommand = new RamseteCommand(
			trajectory,
			Robot.robotContainer.getDriveSubsystem()::getPose,
			new RamseteController(AutoConstants.K_RAMSETE_B, AutoConstants.K_RAMSETE_ZETA),
			new SimpleMotorFeedforward(DriveConstants.KS_VOLTS,
									DriveConstants.KV_VOLT_SECONDS_PER_METER,
									DriveConstants.KA_VOLT_SECONDS_SQUARED_PER_METER),
									DriveConstants.K_DRIVE_KINEMATICS,
									Robot.robotContainer.getDriveSubsystem()::getWheelSpeeds,
			new PIDController(DriveConstants.KP_DRIVE_VEL, DriveConstants.KI_DRIVE_VEL, DriveConstants.KD_DRIVE_VEL),
			new PIDController(DriveConstants.KP_DRIVE_VEL, DriveConstants.KI_DRIVE_VEL, DriveConstants.KD_DRIVE_VEL),
			// RamseteCommand passes volts to the callback
			Robot.robotContainer.getDriveSubsystem()::tankDriveVolts,
			Robot.robotContainer.getDriveSubsystem()
		);

		System.out.println(quinticPoseArray);

		return ramseteCommand;
	}


}