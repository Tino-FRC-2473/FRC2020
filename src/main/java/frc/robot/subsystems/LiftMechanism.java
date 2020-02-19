package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;



public class LiftMechanism {

    public CANSparkMax liftMotor;
    public CANSparkMax winchMotor; 
    public double initHeight; 
    public double initEncoderPosition; 
    public JoystickButton winchStop; 

    public LiftMechanism() {
        winchMotor = new CANSparkMax(Constants.WINCH_MOTOR_PORT, MotorType.kBrushless);
        liftMotor = new CANSparkMax(Constants.LIFT_MOTOR_PORT, MotorType.kBrushless); 
        winchStop = new JoystickButton(new Joystick(Constants.LIFT_CONTROL), Constants.WINCH_STOP_BUTTON); 
        initEncoderPosition = liftMotor.getEncoder().getPosition(); 
        initHeight = 0; 
    }


    public void setInitHeight(double height){ 
        this.initHeight = height; 
    }

    //the horizontal displacement from the initial x, around 21.88
    public double getHorizontalPosition() {
       double currentEncoderPos = liftMotor.getEncoder().getPosition() - initEncoderPosition; //(y2-y1) IT WORKS
       return Constants.INITIAL_HORIZONTAL_POS_LIFT + currentEncoderPos/42.0; //(-18), if (-15), divide by ~ 33.5 
    }

    public double getCurrentHeight() {
        double overallHeight = Constants.HOOK_HEIGHT + Constants.INIT_HEIGHT + (3 * Math.sqrt((Math.pow(Constants.DISTANCE_OPP_PIVOT_POINTS, 2) - Math.pow(getHorizontalPosition(), 2)))); 
        return overallHeight;
    }

    public void setPower(double power) {
        System.out.println(liftMotor.getEncoder().getPosition());
        liftMotor.set(power);
    }

    public void runWinch(double power){
        if (power >= 0){
            winchMotor.set(power);
        } else {
            System.out.println("WARNING: MOTOR IS BEING DRIVEN IN REVERSE! POWER: " + power);
            
        }


    }

    public boolean isRunDown(){
        return liftMotor.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen).isLimitSwitchEnabled(); 
        
     
    }

    public boolean isWinchStop(){
        return winchStop.get(); 

    }
}