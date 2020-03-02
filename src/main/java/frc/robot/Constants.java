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


	public static final class IntakeStorageConstants {
		public static final int SPARK_INTAKE = 9;
		public static final int SPARK_STORAGE = 8;

		public static final int INTAKE_PISTON_FORWARD_PORT = 6;
		public static final int INTAKE_PISTON_REVERSE_PORT = 1;
	}

	public static final class ShooterConstants {
		public static final int SPARK_SHOOTER_LEFT = 6;
		public static final int SPARK_SHOOTER_RIGHT = 7;

		public static final int FORWARD_SOLENOID_PORT = 0;
		public static final int REVERSE_SOLENOID_PORT = 7;

		public static final double SHOOTER_RPM = 5975;
  }

	public static final class ClimberConstants {
		public static final int LIFT_MOTOR_PORT = 5;
		public static final int WINCH_MOTOR_PORT = 10;
		public static final double INITIAL_HORIZONTAL_POS_LIFT = 21.62;
		public static final double DISTANCE_OPP_PIVOT_POINTS = 22.0;
		public static final double INIT_HEIGHT = 11.0;
		public static final double HOOK_HEIGHT = 0;
		public static final int LIFT_CONTROL = 3;
		public static final int WINCH_STOP_BUTTON = 3;

	}

	public static final class DriveConstants {

		public static final int SPARK_FRONT_LEFT_ID = 3; 
		public static final int SPARK_BACK_LEFT_ID = 4; 
		public static final int SPARK_FRONT_RIGHT_ID = 1; 
		public static final int SPARK_BACK_RIGHT_ID = 2;  

		public static final double CAMERA_TO_FRONT_DISTANCE_INCHES = 15.75;
	
		public static final double DRIVE_WHEEL_CIRCUMFRENCE_INCHES = 23.8125;
		public static final double GEAR_RATIO = 10.1111;
		public static final double DRIVE_METERS_PER_ROTATION = Units.inchesToMeters(DRIVE_WHEEL_CIRCUMFRENCE_INCHES / GEAR_RATIO);
		
		public static final double KS_VOLTS = 0.173; // 0.139
		public static final double KV_VOLT_SECONDS_PER_METER = 2.04; // 2.02
		public static final double KA_VOLT_SECONDS_SQUARED_PER_METER = 0.467; // 0.321

		public static final double KP_DRIVE_VEL = 0.003861;
		public static final double KI_DRIVE_VEL = 0.000149;
		public static final double KD_DRIVE_VEL = 0.024936;

		public static final double K_TRACK_WIDTH_METERS = 0.447675;
		public static final DifferentialDriveKinematics K_DRIVE_KINEMATICS = new DifferentialDriveKinematics(
				K_TRACK_WIDTH_METERS);
	}

	public static final class JoystickConstants {
		public static final int WHEEL_PORT = 0;
		public static final int THROTTLE_PORT = 2;
		public static final int JOYSTICK_1_PORT = 1;
    
		public static final int BUTTON_PANEL_PORT = 3;
		public static final int JOYSTICK_2_PORT = 4;

		public static final int BUTTON_1_PORT = 1;
		public static final int BUTTON_2_PORT = 2;
	}

	public static final class AutoConstants {
		public static final double K_MAX_SPEED_METERS_PER_SECOND = 2;
		public static final double K_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 3;
	
		public static final double K_RAMSETE_B = 2;
		public static final double K_RAMSETE_ZETA = 0.7;
	}

}
