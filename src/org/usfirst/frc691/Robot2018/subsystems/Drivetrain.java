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

import org.usfirst.frc691.Robot2018.OI;
import org.usfirst.frc691.Robot2018.Robot;
import org.usfirst.frc691.Robot2018.RobotMap;
import org.usfirst.frc691.Robot2018.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {
	private static double MOTOR_MAX_DRIVE = 1.0;
	private static double MOTOR_AUTO_DRIVE = 1.0;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX leftTalon = RobotMap.drivetrainLeftTalon;
    private final WPI_TalonSRX rightTalon = RobotMap.drivetrainRightTalon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public double kP = 1.85;
	public double kI = 0;
	public double kD = 0;
	public double kF = 1.023;

	private int pd = 0;

	@Override
	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(Robot.sdrive);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("TL1", leftTalon.getMotorOutputPercent());
		SmartDashboard.putNumber("TR1", rightTalon.getMotorOutputPercent());
		SmartDashboard.putNumber("TLenc", leftTalon.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("TLspd", leftTalon.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("TRenc", rightTalon.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("TRspd", rightTalon.getSelectedSensorVelocity(0));
	}

	public void drive(double lspd, double rspd) {
		leftTalon.set(ControlMode.PercentOutput, RobotMap.limit(lspd));
		rightTalon.set(ControlMode.PercentOutput, RobotMap.limit(rspd));
	}

	public void driveArcade(double xspd, double zspd) {
		double lspd, rspd;
		xspd = RobotMap.applyDeadband(RobotMap.limit(xspd), OI.STICK_DEADBAND);
		zspd = RobotMap.applyDeadband(RobotMap.limit(zspd), OI.STICK_DEADBAND);
		xspd = Math.copySign(xspd * xspd, xspd);
		zspd = Math.copySign(zspd * zspd, zspd);
		lspd = xspd + zspd;
		rspd = xspd - zspd;
		drive(lspd * MOTOR_MAX_DRIVE, rspd * MOTOR_MAX_DRIVE);
	}

	public void driveStop() {
		drive(0, 0);
	}

	public void drivePIDVelocity(double vel) {
		leftTalon.set(ControlMode.Velocity, vel);
		rightTalon.set(ControlMode.Velocity, vel);
		if (++pd == 25) {
			pd = 0;
			System.out.format("L:%d(%d) R:%d(%d)\n", leftTalon.getSelectedSensorVelocity(0), leftTalon.getClosedLoopError(0),
					rightTalon.getSelectedSensorVelocity(0), rightTalon.getClosedLoopError(0));
		}
	}
	
	public void driveAuto(int dir) {
		drive(dir * MOTOR_AUTO_DRIVE, dir * MOTOR_AUTO_DRIVE);
	}
	
	public void driveMotionMagic(int pos) {
		leftTalon.set(ControlMode.MotionMagic, pos);
		rightTalon.set(ControlMode.MotionMagic, pos);
	}
	
	public void drivePIDPosition(int pos) {
		leftTalon.set(ControlMode.Position, pos);
		rightTalon.set(ControlMode.Position, pos);
	}

	public void setPIDF(double p, double i, double d, double f) {
		leftTalon.config_kP(0, p, 0);
		leftTalon.config_kI(0, i, 0);
		leftTalon.config_kD(0, d, 0);
		leftTalon.config_kF(0, f, 0);
		rightTalon.config_kP(0, p, 0);
		rightTalon.config_kI(0, i, 0);
		rightTalon.config_kD(0, d, 0);
		rightTalon.config_kF(0, f, 0);
	}
	
	public void configMotionMagic(int cruise, int accel) {
		leftTalon.configMotionCruiseVelocity(cruise, 0);
		rightTalon.configMotionCruiseVelocity(cruise, 0);
		leftTalon.configMotionAcceleration(accel, 0);
		rightTalon.configMotionAcceleration(accel, 0);
		setPIDF(1, 0, 0, 0.5);
	}
	
	public int getEncoders() {
		return leftTalon.getSelectedSensorPosition(0);
	}

	public void resetEncoders() {
		leftTalon.setSelectedSensorPosition(0, 0, 0);
		rightTalon.setSelectedSensorPosition(0, 0, 0);
	}
}
