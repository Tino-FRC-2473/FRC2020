package frc.robot.cv;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
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
		
		double signOfY = (y_d < 0) ? -1 : 1;
		double s_x = -signOfY * Units.inchesToMeters(7.5); // horizontal shift closer to the target
		
		double x_w = -s_x * Math.sin(Units.degreesToRadians(cvData.getAngle())) - w * Math.cos(Units.degreesToRadians(cvData.getAngle())) + cvData.getDX();
		double y_w = s_x * Math.cos(Units.degreesToRadians(cvData.getAngle())) + w * Math.sin(Units.degreesToRadians(cvData.getAngle())) + cvData.getDY();

		trajectory.add(new Waypoint(0, 0, thetaRobotToStraight));

		// trajectory.add(new Waypoint(x_d, y_d, -cvData.getAngle()));
		trajectory.add(new Waypoint(x_w, y_w, -cvData.getAngle()));
	}

	@Override
	public Command getCommand() {
		return trajectory.getRamseteCommand();
	}

}