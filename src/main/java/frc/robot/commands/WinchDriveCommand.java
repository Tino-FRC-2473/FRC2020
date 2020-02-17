/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;
import frc.robot.subsystems.LiftMechanism;

public class WinchDriveCommand extends CommandBase {

    public LiftMechanism liftMech;
    
    /**
     * Creates a new Teleop.
     */
 
    public double power;

    public WinchDriveCommand(LiftMechanism liftMech, double power) {
    // Use addRequirements() here to declare subsystem dependencies.
        this.liftMech = liftMech; 
        this.power = power; 
        
       
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     liftMech.runWinch(0); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if (liftMech.isWinchStop()){
        liftMech.runWinch(power);

      } else {
          end(true); 
      }
    


  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      liftMech.runWinch(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}