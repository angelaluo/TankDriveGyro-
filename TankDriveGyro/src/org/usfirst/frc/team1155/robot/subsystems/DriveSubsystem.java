package org.usfirst.frc.team1369.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.RobotMap;

public class DriveTrain extends Subsystem {

    //    private RobotDrive driveTrain;
    private ADXRS450_Gyro gyro;
    private Encoder encoder;

    private double DRIVE_SPEED_L = 0.5;
    private double DRIVE_SPEED_R = 0.5;
    private double DRIVE_SPEED_CONSTANT = 1;

    public DriveTrain() {
        //  this.driveTrain = RobotMap.ROBOT_DRIVE;
        this.leftStick = RobotMap.JOY_LEFT;
        this.rightStick = RobotMap.JOY_RIGHT;

        //MOTORS ARE INVERTERED
        RobotMap.MOTOR_RIGHT_MOTOR_BACK.setInverted(true);
        RobotMap.MOTOR_RIGHT_MOTOR_FRONT.setInverted(true);

        this.frontRight = RobotMap.MOTOR_RIGHT_MOTOR_FRONT;
        this.frontLeft = RobotMap.MOTOR_LEFT_MOTOR_FRONT;
        this.backLeft = RobotMap.MOTOR_LEFT_MOTOR_BACK;
        this.backRight = RobotMap.MOTOR_RIGHT_MOTOR_BACK;

        this.gyro = Robot.gyro;
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
