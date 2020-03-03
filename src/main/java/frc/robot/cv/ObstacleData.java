package frc.robot.cv;

public class ObstacleData {
	private double obstacle_distance;

	public ObstacleData(double obstacle_distance) {
		this.obstacle_distance = obstacle_distance;
	}
	
	public double getObstacleDistance() {
		return obstacle_distance;
	}

	@Override
	public String toString() {
		return String.format("dist: %.2f", obstacle_distance);
	}
}