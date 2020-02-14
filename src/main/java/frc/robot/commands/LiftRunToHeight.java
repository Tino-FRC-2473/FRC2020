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

public class LiftRunToHeight extends CommandBase {

    LiftMechanism liftMech = new LiftMechanism(); 
    /**
     * Creates a new Teleop.
     */
    public double overallHeight; 
    public double goHeight; 
    public double initHeight; 
    public double power; 
    public LiftRunToHeight(double goHeight, double power) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.goHeight = goHeight; 
    this.initHeight = liftMech.getCurrentHeight(); 
    this.power = power; 
    this.overallHeight = initHeight; 
    
  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      liftMech.setInitHeight(initHeight);


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    overallHeight = initHeight + 3.5*Math.sqrt((22.0*22.0) - (liftMech.getHorizontalPosition()*liftMech.getHorizontalPosition())); 
      if (overallHeight < goHeight){
        liftMech.setPower(power); 

      } else {
          end(true);
      }
    // Robot.robotContainer.getButtonPanel2().whenReleased(new RunServo(0, Robot.robotContainer.servoSubsystem));
    // Robot.robotContainer.getButtonPanel4().whenReleased(new RunServo(90, Robot.robotContainer.servoSubsystem));
    // Robot.robotContainer.getButtonPanel6().whenReleased(new RunServo(180, Robot.robotContainer.servoSubsystem));
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      liftMech.setPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}