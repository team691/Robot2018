// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc691.Robot2018.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc691.Robot2018.Robot;
import org.usfirst.frc691.Robot2018.subsystems.Drivetrain;

public class MotionMagicTest extends Command {
	private static final int DEFAULT_DIST = 1000;
	private static final int MM_CRUISE_VEL = 1000;
	private static final int MM_ACCEL = 500;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private Drivetrain dt;
	private int distEnc = DEFAULT_DIST;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public MotionMagicTest() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    	dt = Robot.drivetrain;
    	requires(dt);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	dt.resetEncoders();
    	dt.configMotionMagic(MM_CRUISE_VEL, MM_ACCEL);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	dt.driveMotionMagic(distEnc);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	dt.driveStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	end();
    }
    
    public void setDistInches(double in) {
    	distEnc = (int) (in * 1000 / 18);
    }
}