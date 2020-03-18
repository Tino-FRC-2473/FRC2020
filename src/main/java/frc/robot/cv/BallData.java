package frc.robot.cv;

public class BallData implements Comparable<BallData> {
	private double ball_distance;
	private double ball_angle;

	public BallData(double ball_distance, double ball_angle) {
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

	@Override
	public int compareTo(BallData otherBall) {
		if (this.getBallDistance() != otherBall.getBallDistance()) {
			return Double.compare(this.getBallDistance(), otherBall.getBallDistance());
		} else {
			return Double.compare(Math.abs(this.getBallAngle()), Math.abs(otherBall.getBallAngle()));
		}
	}
}