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

public class LiftRunToEncoder extends CommandBase {

    LiftMechanism liftMech;
    /**
     * Creates a new Teleop.
     */
    
    public double toEncoder;
    public double power;

    public LiftRunToEncoder(LiftMechanism liftMech, double toEncoder, double power) {
      addRequirements();
        this.liftMech = liftMech; 
        this.toEncoder = toEncoder; 
        this.power = power; 
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (liftMech.getLiftMotor().getEncoder().getPosition() > toEncoder){ //-108.5
      liftMech.setPower(power);
     } else {
     liftMech.setPower(0);
    }
   
      System.out.println(liftMech.getLiftMotor().getEncoder().getPosition());
      



  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      liftMech.setPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return liftMech.getLiftMotor().getEncoder().getPosition() < toEncoder;
  }
}