package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.FireShooterPistonCommand;
import frc.robot.trajectory.TrajectoryBuilder;
import frc.robot.trajectory.Waypoint;
import frc.robot.trajectory.TrajectoryBuilder.Direction;
import frc.robot.trajectory.TrajectoryBuilder.Position;
import frc.robot.trajectory.TrajectoryBuilder.Type;

public class ShootThreePickUpThreeFromTrenchAutonomous extends SequentialCommandGroup {
	public ShootThreePickUpThreeFromTrenchAutonomous() {
		Waypoint start = new Waypoint(0, 0, 0);
		Waypoint inFrontOfTargetZone = new Waypoint(Units.feetToMeters(9), 0, 0);
		Waypoint onInitiationLineFacingRight = new Waypoint(0, Units.feetToMeters(5), -90);
		Waypoint facingTrenchRunBalls = new Waypoint(Units.feetToMeters(-4), Units.inchesToMeters(-66.91), -180);
		Waypoint collectedTrenchRunBalls = new Waypoint(Units.inchesToMeters(-194.63), Units.inchesToMeters(-66.91), -180);

		addCommands(
			new InstantCommand(() -> Robot.robotContainer.getDriveSubsystem().resetPose()),

			// go forward and shoot three balls
			new ParallelCommandGroup(
				new TrajectoryBuilder(Type.QUINTIC, Position.ABSOLUTE, Direction.FORWARD)
					.add(start)
					.add(inFrontOfTargetZone).getRamseteCommand(),
				new SequentialCommandGroup(
					new WaitCommand(1),
					new FireShooterPistonCommand(Robot.robotContainer.getShooterSubsystem()),
					new FireShooterPistonCommand(Robot.robotContainer.getShooterSubsystem()),
					new FireShooterPistonCommand(Robot.robotContainer.getShooterSubsystem())
				)),
			
			// move back and turn to face right (towards the trench run) on the initiation line
			new TrajectoryBuilder(Type.QUINTIC, Position.ABSOLUTE, Direction.REVERSE)
				.add(inFrontOfTargetZone)
				.add(onInitiationLineFacingRight).getRamseteCommand(),

			// drive to the balls in the trench run and deploy the intake
			new ParallelCommandGroup(
				new TrajectoryBuilder(Type.QUINTIC, Position.ABSOLUTE, Direction.FORWARD)
					.add(onInitiationLineFacingRight)
					.add(facingTrenchRunBalls)
					.add(collectedTrenchRunBalls).getRamseteCommand(),
				new SequentialCommandGroup(
					new WaitCommand(2),
					new InstantCommand(() -> Robot.robotContainer.getIntakeStorageSubsystem().deployIntake(0.7))
				)),
			
			// retract the intake
			new InstantCommand(() -> Robot.robotContainer.getIntakeStorageSubsystem().retractIntake()),

			// drive backwards toward the power port
			new TrajectoryBuilder(Type.QUINTIC, Position.ABSOLUTE, Direction.REVERSE)
				.add(collectedTrenchRunBalls)
				.add(facingTrenchRunBalls)
				.add(onInitiationLineFacingRight).getRamseteCommand(),
			
			// drive from facing right on the initiation line to the power port and shoot three balls
			new ParallelCommandGroup(
				new TrajectoryBuilder(Type.QUINTIC, Position.ABSOLUTE, Direction.FORWARD)
					.add(onInitiationLineFacingRight)
					.add(inFrontOfTargetZone).getRamseteCommand(),
				new SequentialCommandGroup(
					new WaitCommand(1),
					new FireShooterPistonCommand(Robot.robotContainer.getShooterSubsystem()),
					new FireShooterPistonCommand(Robot.robotContainer.getShooterSubsystem()),
					new FireShooterPistonCommand(Robot.robotContainer.getShooterSubsystem())
				)),
			new InstantCommand(() -> Robot.robotContainer.getDriveSubsystem().stop())
		);
	}
}