package org.usfirst.frc.team1369.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.RobotMap;

public class DriveSubsystem extends Subsystem {

    //    private RobotDrive driveTrain;
    private ADXRS450_Gyro gyro;
    private Encoder encoder;

    private double DRIVE_SPEED_L = 0.5;
    private double DRIVE_SPEED_R = 0.5;
    private double DRIVE_SPEED_CONSTANT = 1;

   public DriveSubsystem() {
		frontRightTalon = new CANTalon(PortMap.DRIVE_FRONT_RIGHT_TALON);
		frontLeftTalon = new CANTalon(PortMap.DRIVE_FRONT_LEFT_TALON);
		backRightTalon = new CANTalon(PortMap.DRIVE_BACK_RIGHT_TALON);
		backLeftTalon = new CANTalon(PortMap.DRIVE_BACK_LEFT_TALON);
   }

    @Override
    protected void initDefaultCommand() {

    }

    public void driveCustom(double x, double y) {
        frontLeft.set(x);
        backLeft.set(x);
        backRight.set(y);
        frontRight.set(y);
    }
    
    public void stop() {
        driveCustom(0, 0);
    }

    public double getAngle(double angle)
    {
        return ((175/90) * angle) - 175;
    }

    

}
