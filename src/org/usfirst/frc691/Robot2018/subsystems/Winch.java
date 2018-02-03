// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc691.Robot2018.subsystems;

import org.usfirst.frc691.Robot2018.Robot;
import org.usfirst.frc691.Robot2018.RobotMap;
import org.usfirst.frc691.Robot2018.commands.*;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
	private static double MOTOR_DRIVE = 0.3;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController motor1 = RobotMap.winchMotor1;
    private final SpeedController motor2 = RobotMap.winchMotor2;
    private final SpeedControllerGroup motorGroup = RobotMap.winchMotorGroup;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(Robot.sdrive);
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }
    
    public void drive(double spd) {
    	motorGroup.set(spd);
    }
    
    public void driveDir(int dir) {
    	drive(dir * MOTOR_DRIVE);
    }
    
    public void driveStop() {
    	drive(0);
    }
}
