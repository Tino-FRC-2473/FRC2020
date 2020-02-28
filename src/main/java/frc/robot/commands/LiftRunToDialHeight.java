/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.LiftMechanism;

public class LiftRunToDialHeight extends CommandBase {

	LiftMechanism liftMech;

	public double toEncoder;
	public double power;
	public double absolutePower;

	public LiftRunToDialHeight(LiftMechanism liftMech, double power) {
		addRequirements();
		this.liftMech = liftMech;
		this.absolutePower = Math.abs(power);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		toEncoder = Robot.robotContainer.getDialHeight().getValue();
		if (liftMech.getLiftMotor().getEncoder().getPosition() >= toEncoder) { // going up
			this.power = -absolutePower;
		} else { // going down
			this.power = absolutePower;
		}

	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		// if (liftMech.getLiftMotor().getEncoder().getPosition() > toEncoder){ //-108.5
		// liftMech.setPower(power);
		// } else {
		// liftMech.setPower(0);
		// }
		System.out.println(liftMech.getLiftMotor().getEncoder().getPosition());
		
		liftMech.setPower(power);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		liftMech.setPower(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (power > 0) {
			return liftMech.getLiftMotor().getEncoder().getPosition() >= toEncoder;

		}
		return liftMech.getLiftMotor().getEncoder().getPosition() <= toEncoder;
	}
}