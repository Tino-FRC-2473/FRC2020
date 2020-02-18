/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.LiftMechanism;

public class LiftRunToHeight extends CommandBase {

    LiftMechanism liftMech; 
    /**
     * Creates a new Teleop.
     */
    public double overallHeight; 
    public double goHeight; 
    public double initHeight; 
    public double power; 
    public LiftRunToHeight(LiftMechanism liftMech, double goHeight, double power) {
    // Use addRequirements() here to declare subsystem dependencies.
        this.liftMech = liftMech; 
        this.goHeight = goHeight; 
        this.initHeight = liftMech.getCurrentHeight(); 
        this.power = power; 
        this.overallHeight = initHeight; 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
       
      
     
      liftMech.setPower(0.1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
    // if (liftMech.liftMotor.getEncoder().getPosition() > -108.5){
    //   liftMech.setPower(-0.1);
    // } else {
    //   liftMech.setPower(0);
    // }
    // overallHeight = liftMech.getCurrentHeight(); 
    // System.out.println("Init Height: " + liftMech.initHeight + "OH:" + overallHeight + " GH: " + goHeight + " Horizontal: " + liftMech.getHorizontalPosition());
    // //19.21 54 54 horizontal 19.94 4096
    // //about 4
       //initHeight + 3.5*Math.sqrt((22.0*22.0) - (liftMech.getHorizontalPosition()*liftMech.getHorizontalPosition())); 
      //  if (overallHeight < goHeight){
      //   System.out.println(liftMech.liftMotor.getEncoder().getPosition());
        
      //   liftMech.setPower(power);
      //   System.out.println("Power being sent");
        
      // } else {
      //   liftMech.setPower(0);
      // }
      System.out.println(liftMech.liftMotor.getEncoder().getPosition());
      



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