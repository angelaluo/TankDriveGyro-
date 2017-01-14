package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.RobotMap;
import org.usfirst.frc.team1155.robot.sensors.GyroAccelWrapper;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveSubsystem extends Subsystem {

	private RobotDrive roboDrive;
	private CANTalon right;
	private CANTalon left;
	private CANTalon leftback;
	private CANTalon rightback;
	private Robot robot;
	private GyroAccelWrapper gyro;

	public DriveSubsystem(Robot robot, GyroAccelWrapper gyro) {
		this.gyro = gyro;
		this.robot = robot;

		rightback = new CANTalon(RobotMap.RIGHT_TANK_BACK);
		rightback.configEncoderCodesPerRev(1440);

		leftback = new CANTalon(RobotMap.LEFT_TANK_BACK);

		left = new CANTalon(RobotMap.LEFT_TANK);
		left.changeControlMode(TalonControlMode.Follower);
		left.set(RobotMap.LEFT_TANK_BACK);

		right = new CANTalon(RobotMap.RIGHT_TANK);
		right.changeControlMode(TalonControlMode.Follower);
		right.set(RobotMap.RIGHT_TANK_BACK);
	}

	@Override
	public void startTeleop() {
		roboDrive = new RobotDrive(leftback, rightback);
		System.out.println(robot.getOI().getXbox());
		rightback.changeControlMode(TalonControlMode.PercentVbus);
		leftback.changeControlMode(TalonControlMode.PercentVbus);
		if (robot.getOI().getXbox() != null) {
			new ArcadeDrive(this, robot.getOI().getXbox(), gyro).start();
		}
	}

	public double findWheelRotations(double distance, double wheelDiameter) {
		return distance/(wheelDiameter * Math.PI);
	}
	
	public double findDistanceFromRotation(double rotations, double wheelDiameter) {
		return rotations * wheelDiameter * Math.PI;
	}
	private static double calcDeadband(double value, double deadband) {
		int sign = (value > 0 ? 1 : -1); // checks the sign of the value
		value *= sign; // changes the value to positive
		if (value <= deadband) {
			return 0.0; // returns 0 if it is less than deadband
		} else {
			return (value - deadband) * sign; // returns value minus deadband
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void resetPosition() {
		rightback.setPosition(0);
	}

	public void move(double polarity) {
		leftback.set(polarity);
		rightback.set(-polarity);
	}

	public void rotate(double angle, double speed) {
		leftback.set(speed * angle / Math.abs(angle) / 2);
		rightback.set(speed * angle / Math.abs(angle) / 2);
	}

	public double getWheelRotations() {
		return rightback.getEncPosition();
	}

	public double getAngleRotated() {
		return (180 / Math.PI) * findDistanceFromRotation(getWheelRotations(), RobotMap.DRIVE_WHEEL_DIAMETER)
				/ (RobotMap.ROBOT_WIDTH / 2);
	}

	public void arcadeDrive(double d, double rightY, double deadband) {
		roboDrive.arcadeDrive(calcDeadband(d, deadband),
				calcDeadband(rightY, deadband));
	}

	public void tankDrive(double left, double right, double deadband) {
		double ban = deadband;
		roboDrive.tankDrive(calcDeadband(left, ban), calcDeadband(right, ban));
	}