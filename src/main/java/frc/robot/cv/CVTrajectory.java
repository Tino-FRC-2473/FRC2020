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
	public CVTrajectory(CVData cvData, double thetaRobotToStraight, double straightDriveDistFeet, double distFromWallInches) {

		double straightDriveMeters = Units.feetToMeters(straightDriveDistFeet);
		double distFromWallMeters = Units.inchesToMeters(distFromWallInches);
		trajectory = new TrajectoryBuilder(TrajectoryBuilder.Type.QUINTIC, TrajectoryBuilder.Position.ABSOLUTE);

		double d = distFromWallMeters + straightDriveMeters;
		double w = distFromWallMeters;

		double x_d = -d * Math.cos(Units.degreesToRadians(cvData.getAngle())) + cvData.getDX();
		double y_d = d * Math.sin(Units.degreesToRadians(cvData.getAngle())) + cvData.getDY();
		
		double x_w = -w * Math.cos(Units.degreesToRadians(cvData.getAngle())) + cvData.getDX();
		double y_w = w * Math.sin(Units.degreesToRadians(cvData.getAngle())) + cvData.getDY();
		

		trajectory.add(new Waypoint(0, 0, thetaRobotToStraight));
		// trajectory.add(new Waypoint(2, convertedShift/2));
		// CVData cvData = Robot.jetson.getCVData();
		double signOfY = (y_d < 0) ? -1 : 1;
		double offsetX = 0; // Units.inchesToMeters(3.5);
		double offsetY = 0; // Units.inchesToMeters(-signOfY * 3.5);

		trajectory.add(new Waypoint(x_d + offsetX, y_d + offsetY, -cvData.getAngle()));
		trajectory.add(new Waypoint(x_w + offsetX, y_w + offsetY, -cvData.getAngle()));
	}

	@Override
	public RamseteCommand getCommand() {
		return trajectory.getRamseteCommand();
	}

}