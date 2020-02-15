/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int TEST_PORT = 11;
	public static final double ENCODER_INCHES_TO_TICKS = 1;
	public static final int SERVO_PORT = 0;

	public static final class DriveConstants {

		public static final int SPARK_FRONT_LEFT_ID = 3; 
		public static final int SPARK_BACK_LEFT_ID = 4; 
		public static final int SPARK_FRONT_RIGHT_ID = 1; 
		public static final int SPARK_BACK_RIGHT_ID = 2;  

		public static final double CAMERA_TO_CENTER_DISTANCE_INCHES = 10;
	
		public static final double DRIVE_WHEEL_CIRCUMFRENCE_INCHES = 23.8125;
		public static final double GEAR_RATIO = 10.1111;
		public static final double DRIVE_METERS_PER_ROTATION = Units.inchesToMeters(DRIVE_WHEEL_CIRCUMFRENCE_INCHES / GEAR_RATIO);
		// 0.0631 * 0.93220339;

		// 7.579754294
	
		public static final double KS_VOLTS = 0.132; // 0.147, 0.132
		public static final double KV_VOLT_SECONDS_PER_METER = 2.03; // 1.92, 2.03
		public static final double KA_VOLT_SECONDS_SQUARED_PER_METER = 0.257; // 0.255, 0.257
		
		// old pid constants
		public static final double KP_DRIVE_VEL = 0.003861;
		public static final double KI_DRIVE_VEL = 0.000149;
		public static final double KD_DRIVE_VEL = 0.024936;

		// new pid constants
		// public static final double KP_DRIVE_VEL = 0.008459;
		// public static final double KI_DRIVE_VEL = 0.000326;
		// public static final double KD_DRIVE_VEL = 0.054780;
		//17.265
	
		// P used to be 10.4

		public static final double K_TRACK_WIDTH_METERS = 0.447675; // 0.447675
    	public static final DifferentialDriveKinematics K_DRIVE_KINEMATICS = 
        	new DifferentialDriveKinematics(K_TRACK_WIDTH_METERS);
	}

	public static final class JoystickConstants {
		public static final int WHEEL_PORT = 0;
		public static final int THROTTLE_PORT = 2;
		public static final int BUTTON_PANEL_PORT = 3;
	}

	public static final class AutoConstants {
		public static final double K_MAX_SPEED_METERS_PER_SECOND = 2;
		public static final double K_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 4;
	
		public static final double K_RAMSETE_B = 2;
		public static final double K_RAMSETE_ZETA = 0.7;
	}
    
    
}
