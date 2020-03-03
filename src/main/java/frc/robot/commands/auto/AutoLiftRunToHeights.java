/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LiftMechanism;
import frc.robot.subsystems.LiftMechanism.LiftHeights;

public class AutoLiftRunToHeights extends CommandBase {
  
  LiftMechanism liftMechanism;

	public double toEncoder;
	public double power;
  public double absolutePower;
  public LiftHeights liftHeight;

  public AutoLiftRunToHeights(LiftMechanism liftMechanism, double power, LiftHeights liftHeight) {
    this.liftMechanism = liftMechanism;
    this.absolutePower = Math.abs(power);
    this.liftHeight = liftHeight;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    toEncoder = liftHeight.getValue();
		if (liftMechanism.getLiftMotor().getEncoder().getPosition() >= toEncoder) { // going up
			this.power = -absolutePower;
		} else { // going down
			this.power = absolutePower;
		}
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    liftMechanism.setPower(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    liftMechanism.setPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (power > 0) {
			return liftMechanism.getLiftMotor().getEncoder().getPosition() >= toEncoder;
		}
		return liftMechanism.getLiftMotor().getEncoder().getPosition() <= toEncoder;
  }
}
