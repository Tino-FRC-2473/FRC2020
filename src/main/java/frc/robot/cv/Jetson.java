package frc.robot.cv;

import edu.wpi.first.wpilibj.SerialPort;

public class Jetson extends SerialPort {

	private static String START = "S";
	private static String END = "E";

	private double d_x;
	private double d_y;
	private double alpha;

	private boolean first = true;

	private boolean canSeeTarget;

	private String buffer = "";

	public Jetson(int baudRate, Port port) {
		super(baudRate, port);
	}

	// "S +XXXX +XXXX +XXXX E"
	public void updateVisionValues() {
		// get values from serial, store them into the three variables
		try {
			buffer += readString();
			// System.out.println("buffer: " + buffer);
			// System.out.println("buffer: " + buffer);
			if (first) {
				if (buffer.contains(START)) {
					System.out.println("has start");
					buffer = buffer.substring(buffer.indexOf(START));
					first = false;
				}
			} else if (buffer.contains(END)) {
				// System.out.println("has end");
				String rawData = buffer.substring(0, buffer.lastIndexOf(END) + 1).substring(buffer.lastIndexOf(START));
				// System.out.println(rawData+ "/" + rawData.length());
				buffer = buffer.substring(buffer.lastIndexOf(END) + 1);

				if (rawData.length() == 21) {
					// System.out.println(rawData);

					String dxString = rawData.substring(2, 7);
					String dyString = rawData.substring(8, 13);

					String alphaString = rawData.substring(14, 19);

					if (dxString.equals("+9999") || dyString.equals("+9999") || alphaString.equals("+9999")) {
						canSeeTarget = false;
						d_x = 99.99;
						d_y = 99.99;
						alpha = 999.9;
					} else {
						canSeeTarget = true;

						d_x = Integer.parseInt(dxString) / 100.0;
						d_y = Integer.parseInt(dyString) / 100.0;

						alpha = Integer.parseInt(alphaString) / 10.0;
						// alpha *= -1;
					}
					// System.out.printf("dX: %f\ndY: %f\na: %f\n\n", d_x, d_y, alpha);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			buffer = "";
			first = true;
		}
	}

	public boolean canSeeTarget() {
		return canSeeTarget;
	}

	public CVData getCVData() {
		return new CVData(canSeeTarget, d_x, d_y, alpha);
	}

}