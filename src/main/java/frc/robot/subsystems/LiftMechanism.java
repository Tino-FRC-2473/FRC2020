package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class LiftMechanism {

    public CANSparkMax liftMotor;
    public double initHeight; 
    public double initEncoderPosition; 

    public LiftMechanism() {
        liftMotor = new CANSparkMax(Constants.LIFT_MOTOR_PORT, MotorType.kBrushless); 
        initEncoderPosition = liftMotor.getEncoder().getPosition(); 
        initHeight = 0; 
    }


    public void setInitHeight(double height){ 
        this.initHeight = height; 
    }

    //the horizontal displacement from the initial x, around 21.88
    public double getHorizontalPosition() {
       double currentEncoderPos = liftMotor.getEncoder().getPosition() - initEncoderPosition; //Check for signs
       double revs = currentEncoderPos/42.0; 
       return Constants.INITIAL_HORIZONTAL_POS_LIFT + revs/24.0; 
    }

    public double getCurrentHeight() {
        double overallHeight = Constants.HOOK_HEIGHT + initHeight + 3.5 * Math.sqrt((Math.pow(Constants.DISTANCE_OPP_PIVOT_POINTS, 2) - Math.pow(getHorizontalPosition(), 2))); 
        return overallHeight;
    }

    public void setPower(double power) {
        liftMotor.set(power);
    }
}