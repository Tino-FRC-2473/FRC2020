package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class LiftMechanism {


    public CANSparkMax liftMotor;
    public double initHeight = 0; 
    public double initEncoderPosition; 

    public LiftMechanism() {
        liftMotor = new CANSparkMax(Constants.LIFT_MOTOR_PORT, MotorType.kBrushless); 
        initEncoderPosition = liftMotor.getEncoder().getPosition(); 
    }

    public void setInitHeight(double height){
        //liftMotor = new CANSparkMax(Constants.LIFT_MOTOR_PORT); 
       
        this.initHeight = height; 
    }

    // public void runToHeight(double height){
    //     double horizontalDistance = 0; 
    //     double overallHeight = initHeight + 3.5*Math.sqrt((22.0*22.0) - (horizontalDistance*horizontalDistance)); 
    // }

    public double getHorizontalPosition(){
       double currentEncoderPos = liftMotor.getEncoder().getPosition()-initEncoderPosition; 
       double revs = currentEncoderPos/42.0; 
       return 21.88 + 24.0*revs; 
    }

    public double getCurrentHeight(){
        double overallHeight = initHeight + 3.5*Math.sqrt((22.0*22.0) - (getHorizontalPosition()*getHorizontalPosition())); 
        return overallHeight; 


    }

    public void setPower(double power){
        liftMotor.set(power);
    }

    
}