package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command{
	
	DriveSubsystem drive;
	XBoxController xbox;
	ADXRS450_Gyro gyro;
	
	public ArcadeDrive(DriveSubsystem drive, XBoxController xbox, ADXRS450_Gyro gyro) {
		this.drive = drive;
		this.xbox = xbox;
		this.gyro = gyro;
		requires(drive);
		System.out.println("Hi!");
	}
	
	double scaler;
	double deadband;
	double polarity;
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		scaler =1.0;
		deadband = 0.1;
		polarity = -1;
		drive.arcadeDrive(xbox.getLeftY()*scaler*polarity, xbox.getRightX()*scaler*polarity, deadband);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
