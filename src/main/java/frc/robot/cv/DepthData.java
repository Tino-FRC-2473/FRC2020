package frc.robot.cv;

public class DepthData {
	private double ball_distance;
	private double ball_angle;

	public DepthData(double ball_distance, double ball_angle) {
		this.ball_distance = ball_distance;
		this.ball_angle = ball_angle;
	}
	
	public double getBallDistance() {
		return ball_distance;
	}

	public double getBallAngle() {
		return ball_angle;
	}

	@Override
	public String toString() {
		return String.format("dist: %.2f   angle: %.2f", ball_distance, ball_angle);
	}
}