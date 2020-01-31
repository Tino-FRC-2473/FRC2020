package frc.robot.cv;

public class CVData {
	private double d_x;
	private double d_y;
	private double alpha;

	private boolean canSeeTarget;

	public CVData(boolean canSeeTarget, double dx, double dy, double alpha) {
		this.canSeeTarget = canSeeTarget;
		this.d_x = dx;
		this.d_y = dy;
		this.alpha = alpha;
	}
	
	/**
	 * 
	 * @return the x distance from the center of the robot to the CV target
	 */
	public double getDX() {
		return d_x;
	}

	/**
	 * 
	 * @return the y distance from the center of the robot to the CV target
	 */
	public double getDY() {
		return d_y;
	}

	/**
	 * 
	 * @return the robot's angle to the perpendicular
	 */
	public double getAngle() {
		return alpha;
	}
}