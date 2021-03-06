package frc.team691.robot2018.subsystems;

import frc.team691.robot2018.OI;
import frc.team691.robot2018.Robot;
import frc.team691.robot2018.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {
	private static final double MOTOR_MAX_DRIVE = 1.0;
	private static final double MOTOR_AUTO_DRIVE = 0.6;
	private static final double MOTOR_TURN_DRIVE = 0.3;
	
	public final AHRS navx = RobotMap.navx;
    private final WPI_TalonSRX leftTalon = RobotMap.drivetrainLeftTalon;
    private final WPI_TalonSRX rightTalon = RobotMap.drivetrainRightTalon;

	public double kP = 1.85;
	public double kI = 0;
	public double kD = 0;
	public double kF = 1.023;

	private int pd = 0;
	private boolean isTurning = false;
	private int turnGoal = 0;

	@Override
	public void initDefaultCommand() {
        setDefaultCommand(Robot.sdrive);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("NAVX", navx.getAngle());
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

	public void driveAuto(int dir) {
		drive(dir * MOTOR_AUTO_DRIVE, dir * MOTOR_AUTO_DRIVE);
	}
	
	public boolean driveMotionMagic(int pos) {
		leftTalon.set(ControlMode.MotionMagic, pos);
		rightTalon.set(ControlMode.MotionMagic, pos);
		return 0 == (int) (20 * leftTalon.getMotorOutputPercent());
	}
	
	public void drivePIDVelocity(double vel) {
		leftTalon.set(ControlMode.Velocity, vel);
		rightTalon.set(ControlMode.Velocity, vel);
		if (++pd == 25) {
			pd = 0;
			System.out.format("L:%d(%d) R:%d(%d)\n", leftTalon.getSelectedSensorVelocity(0),
				leftTalon.getClosedLoopError(0), rightTalon.getSelectedSensorVelocity(0),
				rightTalon.getClosedLoopError(0));
		}
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
		// TODO: Get better encoder readings
		return leftTalon.getSelectedSensorPosition(0);
	}

	public void resetEncoders() {
		leftTalon.setSelectedSensorPosition(0, 0, 0);
		rightTalon.setSelectedSensorPosition(0, 0, 0);
	}
	
	public boolean turnAngle(int angle) {
		int curAngle = (int) navx.getAngle();
		if (!isTurning) {
			isTurning = true;
			turnGoal = angle + curAngle;
		}
		if (Math.abs(turnGoal - curAngle) < 5) {
			driveStop();
			isTurning = false;
			return true;
		}
		int mult = (int) Math.signum(turnGoal - curAngle);
		if (++pd == 25) {
			System.out.println(curAngle + " --> " + turnGoal);
			pd = 0;
		}
		drive(mult * MOTOR_TURN_DRIVE, -mult * MOTOR_TURN_DRIVE);
		return false;
	}
}
