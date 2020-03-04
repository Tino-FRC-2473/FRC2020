package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ClimberConstants;

import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;



public class LiftMechanism extends SubsystemBase {

    private CANSparkMax liftMotor;
    public CANSparkMax winchMotor; 
    public double initHeight; 
    public double initEncoderPosition; 
    public JoystickButton winchStop; 

    public enum LiftHeights {
        DOWN(0), LOW(-108.76), MEDIUM(-229.581146), HIGH(-400); // -533.91

        private final double value;

        private LiftHeights(double height) {
            this.value = height;
        }

        public double getValue() {
            return value;
        }
    }

    public LiftMechanism() {
        winchMotor = new CANSparkMax(ClimberConstants.WINCH_MOTOR_PORT, MotorType.kBrushless);
        liftMotor = new CANSparkMax(ClimberConstants.LIFT_MOTOR_PORT, MotorType.kBrushless); 
        winchStop = new JoystickButton(new Joystick(ClimberConstants.LIFT_CONTROL), ClimberConstants.WINCH_STOP_BUTTON); 
        initEncoderPosition = liftMotor.getEncoder().getPosition(); 
        initHeight = ClimberConstants.INIT_HEIGHT; //0
    }


    public void setInitHeight(double height){ 
        this.initHeight = height; 
    }

    //the horizontal displacement from the initial x, around 21.88
    public double getHorizontalPosition() {
       double currentEncoderPos = liftMotor.getEncoder().getPosition() - initEncoderPosition; //(y2-y1) IT WORKS
       return ClimberConstants.INITIAL_HORIZONTAL_POS_LIFT + currentEncoderPos/42.0; //(-18), if (-15), divide by ~ 33.5 
    }

    public double getCurrentHeight() {
        double overallHeight = ClimberConstants.HOOK_HEIGHT + ClimberConstants.INIT_HEIGHT + (3 * Math.sqrt((Math.pow(ClimberConstants.DISTANCE_OPP_PIVOT_POINTS, 2) - Math.pow(getHorizontalPosition(), 2)))); 
        return overallHeight;
    }

    public void setPower(double power) {
        if (power == 0) {
            liftMotor.set(0);
        } else if (liftMotor.getEncoder().getPosition() >= -10 && power > 0) {
            liftMotor.set(0.1);
        } else {
            liftMotor.set(power);
        }
        
        System.out.println(liftMotor.getEncoder().getPosition());
    }

    public void runWinch(double power){
        if (power >= 0){
            winchMotor.set(power);
        } else {
            System.out.println("WARNING: MOTOR IS BEING DRIVEN IN REVERSE! POWER: " + power);

        }

    }

    public boolean isRunDown(){
        return liftMotor.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyClosed).get(); 
    }

    public CANSparkMax getLiftMotor(){
        return liftMotor; 
    }
}