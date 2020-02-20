package frc.robot.cv;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
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
	public CVTrajectory(CVData cvData, double thetaRobotToStraight, double straightDriveDistFeet, double y_straight, double x_straight) {

		double straightDriveMeters = Units.feetToMeters(straightDriveDistFeet);
		trajectory = new TrajectoryBuilder(TrajectoryBuilder.Type.QUINTIC, TrajectoryBuilder.Position.ABSOLUTE);

		double x_end = x_straight + straightDriveMeters * Math.cos(Units.degreesToRadians(cvData.getAngle()));
		double y_end = y_straight - straightDriveMeters * Math.sin(Units.degreesToRadians(cvData.getAngle()));


		trajectory.add(new Waypoint(0, 0, thetaRobotToStraight));
		// trajectory.add(new Waypoint(2, convertedShift/2));
		// CVData cvData = Robot.jetson.getCVData();
		trajectory.add(new Waypoint(x_straight, y_straight, -cvData.getAngle()));
		trajectory.add(new Waypoint(x_end, y_end, -cvData.getAngle()));
	}

	@Override
	public RamseteCommand getCommand() {
		return trajectory.getRamseteCommand();
	}

}