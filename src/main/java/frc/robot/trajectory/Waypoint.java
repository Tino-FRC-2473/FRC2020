package frc.robot.trajectory;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Units;

public class Waypoint {
	private double x;
	private double y;
	private double rotation;

	/**
	 * Creates a new waypoint. The robot faces the +x axis at 0 degrees.
	 * @param x
	 * @param y
	 * @param rotation in degrees
	 */
	public Waypoint(double x, double y, double rotation) {
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}

	public Waypoint(double x, double y) {
		this(x, y, 0);
	}

	public Waypoint(Pose2d pose) {
		this(pose.getTranslation().getX(), pose.getTranslation().getY(), pose.getRotation().getDegrees());
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getRotationDegrees() {
		return rotation;
	}

	public double getRotationRadians() {
		System.out.println("GETTING DEGREES RADIANS " + Units.degreesToRadians(rotation));
		return Units.degreesToRadians(rotation);
	}

	public Pose2d convertToPose2d() {
		return new Pose2d(this.getX(),
							this.getY(), 
							new Rotation2d(this.getRotationRadians()));
	}

	public Translation2d convertToTranslation2d() {
		return new Translation2d(this.getX(),
							this.getY());
	}
}