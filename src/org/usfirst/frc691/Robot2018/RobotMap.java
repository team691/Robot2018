// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc691.Robot2018;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static WPI_TalonSRX drivetrainLeftTalon;
    public static WPI_TalonSRX drivetrainRightTalon;
    public static SpeedController winchMotor1;
    public static SpeedController winchMotor2;
    public static SpeedControllerGroup winchMotorGroup;
    public static SpeedController intakeMotor1;
    public static SpeedController intakeMotor2;
    public static SpeedControllerGroup intakeMotorGroup;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static WPI_TalonSRX drivetrainLeftSlaveTalon;
    public static WPI_TalonSRX drivetrainRightSlaveTalon;

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrainLeftTalon = new WPI_TalonSRX(1);
        
        
        drivetrainRightTalon = new WPI_TalonSRX(0);
        
        
        winchMotor1 = new Spark(0);
        LiveWindow.addActuator("Winch", "Motor 1", (Spark) winchMotor1);
        winchMotor1.setInverted(false);
        winchMotor2 = new Spark(1);
        LiveWindow.addActuator("Winch", "Motor 2", (Spark) winchMotor2);
        winchMotor2.setInverted(false);
        winchMotorGroup = new SpeedControllerGroup(winchMotor1, winchMotor2  );
        LiveWindow.addActuator("Winch", "Motor Group", winchMotorGroup);
        
        intakeMotor1 = new Spark(2);
        LiveWindow.addActuator("Intake", "Motor 1", (Spark) intakeMotor1);
        intakeMotor1.setInverted(false);
        intakeMotor2 = new Spark(3);
        LiveWindow.addActuator("Intake", "Motor 2", (Spark) intakeMotor2);
        intakeMotor2.setInverted(false);
        intakeMotorGroup = new SpeedControllerGroup(intakeMotor1, intakeMotor2  );
        LiveWindow.addActuator("Intake", "Motor Group", intakeMotorGroup);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrainLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        //drivetrainLeftTalon.configOpenloopRamp(0, 0);
        //drivetrainLeftTalon.configClosedloopRamp(2, 0);
        drivetrainRightTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        //drivetrainRightTalon.configOpenloopRamp(0, 0);
        //drivetrainRightTalon.configClosedloopRamp(2, 0);
        drivetrainRightTalon.setInverted(true);
        
        drivetrainLeftSlaveTalon = new WPI_TalonSRX(3);
        drivetrainLeftSlaveTalon.follow(drivetrainLeftTalon);
        drivetrainRightSlaveTalon = new WPI_TalonSRX(2);
        drivetrainRightSlaveTalon.follow(drivetrainRightTalon);
        drivetrainRightSlaveTalon.setInverted(true);
    }
    
	public static double limit(double value) {
		return Math.copySign(Math.min(Math.abs(value), 1.0), value);
	}
}
