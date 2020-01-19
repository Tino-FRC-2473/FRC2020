/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ServoSubsystem extends SubsystemBase {
 
  private Servo testServo;
 
  /**
   * Creates a new ServoSubsystem.
   */
  public ServoSubsystem() {
    testServo = new Servo(Constants.SERVO_PORT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getServoAngle() {
    return testServo.getAngle();
  }

  public void setServoAngle(double angle) {
    testServo.setAngle(angle);
  }

  public double getServoSpeed() {
    return testServo.getSpeed();
  }

  public void setServoSpeed(double speed) {
    testServo.setSpeed(speed);
  }

  public void setServo(double target) {
    testServo.set(target);
  }

  public Servo getServo() {
    return testServo;
  }

  public void stop() {
  }
}
