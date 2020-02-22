/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LiftMechanism;

public class LiftRunDownCommand extends CommandBase {

    LiftMechanism liftMech;
   double power; 
   

    public LiftRunDownCommand(LiftMechanism liftMech, double power) {
        this.power = power; 
        this.liftMech = liftMech; 
        
        
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (power > 0) {
      liftMech.setPower(power); 
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    System.out.println(liftMech.isRunDown());

  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      liftMech.setPower(0);
      liftMech.getLiftMotor().getEncoder().setPosition(0); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return liftMech.isRunDown(); 
  }
}