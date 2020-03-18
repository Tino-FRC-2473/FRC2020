package frc.robot.cv;

import java.util.Arrays;

import edu.wpi.first.wpilibj.LinearFilter;
import edu.wpi.first.wpilibj.SerialPort;

public class Jetson extends SerialPort {

	private static String START = "S";
	private static String END = "E";

	private CVData cvData;
	private BallData[] balls = new BallData[5];
	private ObstacleData obstacleData;

	private boolean first = true;

	private String buffer = "";

	private LinearFilter obstacleFilter = LinearFilter.movingAverage(5);

	public Jetson(int baudRate, Port port) {
		super(baudRate, port);
	}

	// "S +XXXX +XXXX +XXXX E"
	public void updateVisionValues() {
		// get values from serial, store them into the three variables
		try {
			buffer += readString();
			// System.out.println(buffer.length());
			if (first) {
				if (buffer.contains(START)) {
					System.out.println("has start");
					buffer = buffer.substring(buffer.indexOf(START));
					first = false;
				}
			} else if (buffer.contains(END) && buffer.contains(START)) {
				String rawData = buffer.substring(0, buffer.lastIndexOf(END) + 1);
				rawData = rawData.substring(rawData.lastIndexOf(START)); // last index of start is sometimes -1
				
				// System.out.println(rawData + "//" + buffer.lastIndexOf(END) + "//" + buffer.lastIndexOf(START));

				// System.out.println(rawData + "/// " + rawData.length() + "///_" + rawData.charAt(18) + "///");
				
				buffer = buffer.substring(buffer.lastIndexOf(END) + 1);

				if (rawData.length() == 87) {
					// System.out.println("right length");
					System.out.println(rawData);

					String[] split = rawData.substring(2, rawData.length()-2).split(" ");

					boolean canSeeTarget;

					if (split[0].equals("+9999")) {
						canSeeTarget = false;
					} else {
						canSeeTarget = true;
					}

					double d_x = Integer.parseInt(split[0]) / 100.0;
					double d_y = Integer.parseInt(split[1]) / 100.0;
					double alpha = Integer.parseInt(split[2]) / 10.0;
					alpha *= -1;

					cvData = new CVData(canSeeTarget, d_x, d_y, alpha);

					balls = new BallData[5];

					for (int i = 0; i < 5; i++) {
						double ball_dist = Integer.parseInt(split[2*i + 3]) / 100.0;
						double ball_angle = Integer.parseInt(split[2*i + 4]) / 10.0;

						balls[i] = new BallData(ball_dist, ball_angle);
					}

					Arrays.sort(balls); // sort the balls with closest distance first, then closest angle to 0

					double obstacle_dist = Integer.parseInt(split[13]) / 100.0;
					obstacleData = new ObstacleData(obstacle_dist);
				} else {
					// System.out.println("not right length///" + rawData + "///" + rawData.length());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			buffer = "";
			first = true;
		}
	}

	public CVData getCVData() {
		return cvData;
	}

	public BallData getClosestBallData() {
		if (balls.length == 0) {
			return null;
		} else {
			return balls[0];
		}
	}

	public ObstacleData getObstacleData() {
		return obstacleData;
	}

}