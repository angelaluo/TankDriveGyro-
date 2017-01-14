package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.OI;
import org.usfirst.frc.team2976.robot.Robot;
import org.usfirst.frc.team2976.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2976.robot.subsystems.GyroPIDSource;
import org.usfirst.frc.team2976.robot.subsystems.PIDMain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *@author NeilHazra
 */
public class DriveStraight extends Command {
	DriveTrain driveTrain = new DriveTrain();

	public GyroPIDSource gyropidsource = new GyroPIDSource(); // Implements
																// PIDSource

	/** Proportional gain */	
	double kp = 0.009;
	/** Integral Gain */
	double ki = 0.00;
	/** Derivative Gain */
	double kd = 0.00;

	int robot_angle_setpoint = 0;

	final int sampleTime = 100;	

	public PIDMain RobotAnglePID = new PIDMain(gyropidsource, robot_angle_setpoint, sampleTime, kp, ki, kd);
	// PID controller for Motor 1

	public DriveStraight() {
		requires(driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.DriveBOT.cancel(); 	   //Interrupt the regular DriveFuntion
		RobotAnglePID.isEnabled(true); //Start the PID
		gyropidsource.reset();

	}
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {	
		if (RobotAnglePID.getOutput() < 0) {
			DriveTrain.leftMotor.set(-OI.driveStick.getY() + Math.abs(RobotAnglePID.getOutput()));
			DriveTrain.rightMotor.set(OI.driveStick.getY());
			DriveTrain.leftMotor2.set(-OI.driveStick.getY() + Math.abs(RobotAnglePID.getOutput()));
			DriveTrain.rightMotor2.set(OI.driveStick.getY());
		} else if (RobotAnglePID.getOutput() > 0) {
			DriveTrain.leftMotor.set(-OI.driveStick.getY());
			DriveTrain.rightMotor.set(OI.driveStick.getY() - Math.abs(RobotAnglePID.getOutput()));
			DriveTrain.leftMotor2.set(-OI.driveStick.getY());
			DriveTrain.rightMotor2.set(OI.driveStick.getY() - Math.abs(RobotAnglePID.getOutput()));
		} else {
			DriveTrain.leftMotor.set(-OI.driveStick.getY());
			DriveTrain.rightMotor.set(OI.driveStick.getY());
			DriveTrain.leftMotor2.set(-OI.driveStick.getY());
			DriveTrain.rightMotor2.set(OI.driveStick.getY());
		}
		
		SmartDashboard.putNumber("DrivePid",RobotAnglePID.getOutput());
		SmartDashboard.putNumber("DrivePidError",RobotAnglePID.getError());
		
	}
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	// Called once after isFinished returns true
	protected void end() {
		RobotAnglePID.isEnabled(false);//Terminate the PID loop
		Robot.DriveBOT.start(); //start the regular drive function
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end(); //Called when button is released
	}
}
