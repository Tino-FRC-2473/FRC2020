/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

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

    public static final int SPARK_FRONT_LEFT_ID = 3; 
    public static final int SPARK_BACK_LEFT_ID = 4; 
    public static final int SPARK_FRONT_RIGHT_ID = 1; 
    public static final int SPARK_BACK_RIGHT_ID = 2;  

    public static final double DRIVE_P = 5e-4;
    public static final double DRIVE_I = 0;
    public static final double DRIVE_D = 0;

    public static final double DRIVE_TICKS_PER_INCH = 1;
    // TODO: Change constants
    public static final double DRIVE_METERS_PER_ROTATION = 0;

	public static final int WHEEL_PORT = 0;
    public static final int JOYSTICK_1_PORT = 1;
	public static final int THROTTLE_PORT = 2;
    public static final int BUTTON_PANEL_PORT = 3;
    public static final int JOYSTICK_2_PORT = 4;

    public static final int SERVO_PORT = 0;

    public static final int BUTTON_1_PORT = 1;
    public static final int BUTTON_2_PORT = 2;


    // Trajectory constants. TODO: Change
    public static final double KS_VOLTS = 0.147;
    public static final double KV_VOLT_SECONDS_PER_METER = 1.92;
    public static final double KA_VOLD_SECONDS_SQUARED_PER_METER = 0.255;

    public static final double KP_DRIVE_VEL = 17.2;
    //17.265

    public static final double K_TRACK_WIDTH_METERS = 0.447675;
    public static final DifferentialDriveKinematics K_DRIVE_KINEMATICS = 
        new DifferentialDriveKinematics(K_TRACK_WIDTH_METERS);
    
    public static final double K_MAX_SPEED_METERS_PER_SECOND = 3;
    public static final double K_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 3;

    public static final double K_RAMSETE_B = 2;
    public static final double K_RAMSETE_ZETA = 0.7;
}
