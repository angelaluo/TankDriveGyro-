package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.Hardware;
import org.usfirst.frc.team1155.robot.PortMap;
import org.usfirst.frc.team1155.robot.Robot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	public static Talon rightMotor = new Talon(RobotMap.RightDriveMotor); //Talon Object
	public static Talon leftMotor = new Talon(RobotMap.LeftDriveMotor);	 //Talon Object
	public static Talon rightMotor2 = new Talon(RobotMap.RightDriveMotor2); //Talon Object
	public static Talon leftMotor2 = new Talon(RobotMap.LeftDriveMotor2);	 //Talon Object
	public static RobotDrive m_drive = new RobotDrive(rightMotor, rightMotor2, leftMotor, leftMotor2); //Robot Drive Class
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		// RobotDrive.Method; with Method being Stop
	}
	public static void setZero(){
		rightMotor.set(0);
		leftMotor.set(0);
	}
}
