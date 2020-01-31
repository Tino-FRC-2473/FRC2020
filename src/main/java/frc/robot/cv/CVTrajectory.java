package frc.robot.cv;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Robot;
import frc.robot.trajectory.TrajectoryBuilder;
import frc.robot.trajectory.TrajectoryContainer;
import frc.robot.trajectory.Waypoint;

public class CVTrajectory implements TrajectoryContainer {
	TrajectoryBuilder trajectory;

	/**
	 * Constructs a trajectory to align the robot to CV targets using Jetson values.
	 * 
	 * @param distFromWallInches distance of camera from wall in inches
	 */
	public CVTrajectory(double distFromWallInches, CVData cvData, double angleToTargetDegrees) {

		double convertedDist = Units.inchesToMeters(distFromWallInches);
		trajectory = new TrajectoryBuilder(TrajectoryBuilder.Type.QUINTIC, TrajectoryBuilder.Position.ABSOLUTE);

		trajectory.add(new Waypoint(0, 0, angleToTargetDegrees));
		// trajectory.add(new Waypoint(2, convertedShift/2));
		// CVData cvData = Robot.jetson.getCVData();

		trajectory.add(new Waypoint(cvData.getDX() - convertedDist - Units.feetToMeters(2), cvData.getDY(), 0));
		trajectory.add(new Waypoint(cvData.getDX() - convertedDist, cvData.getDY(), 0));
	}

	@Override
	public RamseteCommand getCommand() {
		return trajectory.getRamseteCommand();
	}

}